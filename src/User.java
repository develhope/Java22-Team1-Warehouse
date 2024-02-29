import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class User  {
    private Warehouse warehouse;
    private Cart cart;
    public void userMenu(Cart cart, Warehouse warehouse) {
        Scanner sc = new Scanner(System.in);
        this.warehouse = warehouse;
        this.cart = cart;
        ResearchMethods researchMethods = new ResearchMethods(warehouse, sc);

        MenuOptionsUser sceltaUser;

        boolean partitaIva = getIvaUser(new Scanner(System.in));
        //Menu per scelta Utente
        do {
            String input = sc.nextLine();

                sceltaUser = researchMethods.getMenuOptionsByIndexUser(input, MenuOptionsUser.class);


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
                        addToCartById(sc, partitaIva);
                        break;
                    case RIMUOVI_DAL_CARRELLO:
                        removeFromCartById(sc, partitaIva);
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
                            System.out.println(finalizeSale(cart, partitaIva));
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
    public static String finalizeSale(Cart cart, boolean iva) {
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
    public static boolean getIvaUser(Scanner sc) {
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
    private void addToCartById(Scanner sc, boolean iva) {
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
    private void removeFromCartById(Scanner sc, boolean iva) {
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