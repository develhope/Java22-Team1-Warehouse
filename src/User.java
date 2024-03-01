import UserAndOperatorEnums.MenuOptionsUser;

import java.util.Scanner;


public class User {
    private final Warehouse warehouse;
    private final Cart cart;
    private final Scanner sc;
    private boolean iva;
    private ResearchMethods researchMethods;

    public User(Warehouse warehouse, Cart cart, Scanner sc) {
        this.warehouse = warehouse;
        this.cart = cart;
        this.sc = sc;
    }


    public void userMenu() {
        MenuOptionsUser sceltaUser;

        this.iva = getIvaUser();
        this.researchMethods = new ResearchMethods(warehouse, iva, false);

        //Menu per scelta Utente
        sc.nextLine();
        do {
            System.out.println("Scegli un'opzione:");
            for (int i = 0; i < MenuOptionsUser.values().length - 1; i++) {
                System.out.println(MenuOptionsUser.values()[i].ordinal() + ") " + MenuOptionsUser.values()[i].getStringa() + ": ");
            }

            String input = sc.nextLine();

            if (input.matches("\\d+")) {
                sceltaUser = GetValidInput.getMenuOptionsByIndexUser(input);
            } else {
                sceltaUser = MenuOptionsUser.UNKNOWN;
            }


            switch (sceltaUser) {

                case VISUALIZZA_TUTTI_PRODOTTI:
                    researchMethods.printDevices(warehouse.getDevices());
                    break;
                case RICERCA_PER_TIPO_DISPOSITIVO:
                    researchMethods.searchByType();
                    break;
                case RICERCA_PER_PRODUTTORE:
                    researchMethods.searchByBrand();
                    break;
                case RICERCA_PER_MODELLO:
                    researchMethods.searchByModel();
                    break;
                case RICERCA_PER_PREZZO_DI_VENDITA:
                    researchMethods.searchBySellPrice();
                    break;
                case RICERCA_PER_RANGE_DI_VENDITA:
                    researchMethods.searchByPriceRange();
                    break;
                case AGGIUNGI_AL_CARRELLO:
                    addToCartById();
                    break;
                case RIMUOVI_DAL_CARRELLO:
                    removeFromCartById();
                    break;
                case CALCOLARE_IL_TOTALE:
                    calcAndPrintTotal();
                    break;
                case VISUALIZZA_IL_CARRELLO:
                    researchMethods.printDevices(cart.getDevices());
                    break;
                case ACQUISTA:
                    finalizeSaleMenu();
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
    private void fromWarehouseToCart(long id) {
        cart.addDevice(warehouse.getDeviceById(id));
        warehouse.removeDeviceById(id);
        researchMethods.printDevices(cart.getDevices());
    }

    //Metodo per aggiungere prodotti dal carrello al magazzino
    private void fromCartToWarehouse(long id) {
        warehouse.addDevice(cart.getDeviceById(id));
        cart.removeDeviceById(id);
        researchMethods.printDevices(cart.getDevices());
    }

    //Metodo che finalizza l' acquisto
    private String finalizeSale() {
        double finalPrice;
        if (iva) {
            finalPrice = cart.getFinalPrice() * 1.22;
        } else {
            finalPrice = cart.getFinalPrice();
        }
        cart.emptyList();
        return "Questo è il tuo prezzo finale: " + finalPrice;
    }

    private void finalizeSaleMenu() {
        if (cart.isEmpty()) {
            System.out.println("Il carrello è vuoto.");
            return;
        }
        System.out.println("1) Per procedere all'acquisto.");
        System.out.println("2) Per tornare al menu principale.");
        String sceltaFinale = sc.next();

        if (sceltaFinale.equals("1")) {
            System.out.println(finalizeSale());
            System.out.println("Grazie per l'acquisto, speriamo di rivederti presto.");
            sc.nextLine();
        } else if (sceltaFinale.equals("2")) {
            sc.nextLine();
        } else {
            System.out.println("Scelta non consentita.");
            sc.nextLine();
        }
    }

    private void calcAndPrintTotal() {
        if (cart.isEmpty()) {
            System.out.println("Il carrello è vuoto.");
            return;
        }
        System.out.println("Il prezzo finale del carrello è:");
        if (!iva) {
            System.out.println(cart.getFinalPrice());
        } else {
            System.out.println(cart.getFinalPrice() * 1.22);
        }
    }

    //Metodo che gestisce se l' utente e' un privato o possiede una partita iva
    private boolean getIvaUser() {
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
    private void addToCartById() {
        System.out.println("Digita un id per aggiungere al carrello:");
        try {
            long sceltaId = Long.parseLong(sc.next());
            if (!warehouse.containsDeviceById(sceltaId)) {
                System.out.println("Non è stato trovato alcun dispositivo con questo ID");
                return;
            }
            System.out.println("Questo è il carrello: ");
            fromWarehouseToCart(sceltaId);
            sc.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un formato ID corretto.");
            sc.nextLine();
        }
    }

    //Metodo per rimuovere i prodotti dal carrello tramite ID
    private void removeFromCartById() {
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
            fromCartToWarehouse(sceltaId2);
            sc.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un formato ID corretto.");
            sc.nextLine();
        }
    }
}