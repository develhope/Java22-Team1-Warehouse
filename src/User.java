import Devices.DeviceClasses;

import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;


public class User {
    private Warehouse warehouse;
    private Cart cart;
    private Scanner sc;

    public User(Warehouse warehouse, Cart cart, Scanner sc) {
        this.warehouse = warehouse;
        this.cart = cart;
        this.sc = sc;
    }

    public void userMenu() {
        ResearchMethods researchMethods = new ResearchMethods(warehouse, sc);
        MenuOptionsUser sceltaUser;

        boolean partitaIva = getIvaUser();

        //Menu per scelta Utente
        sc.nextLine();
        do {
            System.out.println("Scegli un'opzione:");
            for (int i = 0; i < MenuOptionsUser.values().length - 1; i++) {
                String optionName = MenuOptionsUser.values()[i].getStringa();
                System.out.println(MenuOptionsUser.values()[i].ordinal() + ") " + optionName + ": ");
            }

            String input = sc.nextLine();

            if (input.matches("\\d+")) {
                sceltaUser = researchMethods.getMenuOptionsByIndexUser(input, MenuOptionsUser.class);
            } else {
                sceltaUser = MenuOptionsUser.UNKNOWN;
            }


            switch (sceltaUser) {

                case VISUALIZZA_TUTTI_PRODOTTI:
                    if (warehouse.isEmpty()) {
                        System.out.println("Il magazzino è vuoto.");
                        continue;
                    }
                    warehouse.printAllDevices(partitaIva, false);
                    break;
                case RICERCA_PER_TIPO_DISPOSITIVO:
                    researchMethods.searchByType(partitaIva);
                    break;
                case RICERCA_PER_PRODUTTORE:
                    researchMethods.searchByBrand(partitaIva);
                    break;
                case RICERCA_PER_MODELLO:
                    researchMethods.searchByModel(partitaIva);
                    break;
                case RICERCA_PER_PREZZO_DI_VENDITA:
                    researchMethods.searchBySellPrice(partitaIva);
                    break;
                case RICERCA_PER_RANGE_DI_VENDITA:
                    researchMethods.searchByPriceRange(partitaIva);
                    break;
                case AGGIUNGI_AL_CARRELLO:
                    addToCartById(partitaIva);
                    break;
                case RIMUOVI_DAL_CARRELLO:
                    removeFromCartById(partitaIva);
                    break;
                case CALCOLARE_IL_TOTALE:
                    if (cart.isEmpty()) {
                        System.out.println("Il carrello è vuoto.");
                        break;
                    }
                    System.out.println("Il prezzo finale del carrello è:");
                    if (!partitaIva) {
                        System.out.println(cart.getFinalPrice());
                    } else {
                        System.out.println(cart.getFinalPrice() * 1.22);
                    }
                    break;
                case VISUALIZZA_IL_CARRELLO:
                    cart.printAllDevices(partitaIva);
                    break;
                case ACQUISTA:
                    if (cart.isEmpty()) {
                        System.out.println("Il carrello è vuoto.");
                        break;
                    }
                    System.out.println("1) Per procedere all'acquisto.");
                    System.out.println("2) Per tornare al menu principale.");
                    String sceltaFinale = sc.next();

                    if (sceltaFinale.equals("1")) {
                        System.out.println(finalizeSale(partitaIva));
                        System.out.println("Grazie per l'acquisto, speriamo di rivederti presto.");
                        sc.nextLine();
                        break;
                    } else if (sceltaFinale.equals("2")) {
                        sc.nextLine();
                        break;
                    } else {
                        System.out.println("Scelta non consentita.");
                        sc.nextLine();
                    }
                    break;
                case FINE:
                    System.out.println("Arrivederci!");
                    break;
                case UNKNOWN:
                    System.out.println("Opzione non valida. Riprova.");
                    break;

            }

        } while (sceltaUser != MenuOptionsUser.FINE);
    }

    //Metodo per aggiungere prodotti dal magazzino al carrello
    public void fromWarehouseToCart(long id, Boolean iva) {
        cart.addDevice(warehouse.getDeviceById(id));
        warehouse.removeDeviceById(id);
        cart.printAllDevices(iva);
    }

    //Metodo per aggiungere prodotti dal carrello al magazzino
    public void fromCartToWarehouse(long id, Boolean iva) {
        warehouse.addDevice(cart.getDeviceById(id));
        cart.removeDeviceById(id);
        cart.printAllDevices(iva);
    }

    //Metodo che finalizza l' acquisto
    public String finalizeSale(boolean iva) {
        double finalPrice;
        if (iva) {
            finalPrice = cart.getFinalPrice() * 1.22;
        } else {
            finalPrice = cart.getFinalPrice();
        }
        cart.emptyList();
        return "Questo è il tuo prezzo finale: " + finalPrice;
    }

    //Metodo che gestisce se l' utente e' un privato o possiede una partita iva
    public boolean getIvaUser() {
        while (true) {
            System.out.println("Seleziona il tipo di utente:");
            System.out.println("1) Utente SENZA partita IVA.");
            System.out.println("2) Utente CON partita IVA.");
            if (sc.hasNextInt()) {
                int sceltaUser = sc.nextInt();
                switch (sceltaUser) {
                    case 1:
                        return true;
                    case 2:
                        return false;
                    default:
                        System.out.println("Opzione non valida, riprova.");
                        break;
                }
            } else {
                System.out.println("Input non valido, riprova.");
                sc.next();
            }
        }
    }

    //Metodo per aggiungere i prodotti al carrello tramite un ID
    private void addToCartById(boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        System.out.println("Digita un id per aggiungere al carrello:");
        try {
            long sceltaId = Long.parseLong(sc.next());
            if (!warehouse.containsDeviceById(sceltaId)) {
                System.out.println("Non è stato trovato alcun dispositivo con questo ID");
                return;
            }
            System.out.println("Questo è il carrello: ");
            fromWarehouseToCart(sceltaId, iva);
            sc.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un formato ID corretto.");
            sc.nextLine();
        }
    }

    //Metodo per rimuovere i prodotti dal carrello tramite ID
    private void removeFromCartById(boolean iva) {
        if (cart.isEmpty()) {
            System.out.println("Il carrello è vuoto.");
            return;
        }
        System.out.println("Digita un id per rimuovere al carrello:");
        try {
            long sceltaId2 = Long.parseLong(sc.next());
            if (!cart.containsDeviceById(sceltaId2)) {
                System.out.println("Non è stato trovato alcun dispositivo con questo ID.");
                return;
            }
            fromCartToWarehouse(sceltaId2, iva);
            sc.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un formato ID corretto.");
            sc.nextLine();
        }
    }
}