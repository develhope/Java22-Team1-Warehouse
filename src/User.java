import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

enum MenuOptionsUser {
    VISUALIZZA_TUTTI_PRODOTTI,
    RICERCA_PER_TIPO_DISPOSITIVO,
    RICERCA_PER_PRODUTTORE,
    RICERCA_PER_MODELLO,
    RICERCA_PER_PREZZO_DI_VENDITA,
    RICERCA_PER_RANGE_DI_VENDITA,
    AGGIUNGI_AL_CARRELLO,
    RIMUOVI_DAL_CARRELLO,
    CALCOLARE_IL_TOTALE,
    VISUALIZZA_IL_CARRELLO,
    ACQUISTA,
    FINE;

}
public class User extends ResearchMethods {
    public void userMenu(Cart cart, Warehouse warehouse) {
        Scanner sc = new Scanner(System.in);

        MenuOptionsUser sceltaUser;

        boolean partitaIva = getIvaUser(new Scanner(System.in));
        //Menu per scelta Utente
        do {
            System.out.println("Scegli un'opzione:");
            for (MenuOptionsOperator option : MenuOptionsOperator.values()) {
                String optionName = option.name().replace("_", " ").toLowerCase();
                optionName = optionName.substring(0, 1).toUpperCase() + optionName.substring(1);
                System.out.println(option.ordinal() + ") " + optionName);
            }

            String input = sc.nextLine();

            if (input.matches("\\d+")) {
                sceltaUser = getMenuOptionsByIndex(input);
            } else {
                sceltaUser = getMenuOptionsByString(input);
            }

            try {
                switch (sceltaUser) {

                    case VISUALIZZA_TUTTI_PRODOTTI:
                        if (warehouse.isEmpty()) {
                            System.out.println("Il magazzino è vuoto.");
                            continue;
                        }
                        warehouse.printAllDevices(partitaIva, false);
                        break;
                    case RICERCA_PER_TIPO_DISPOSITIVO:
                        searchByType(warehouse, sc, partitaIva);
                        break;
                    case RICERCA_PER_PRODUTTORE:
                        searchByBrand(warehouse, sc, partitaIva);
                        break;
                    case RICERCA_PER_MODELLO:
                        searchByModel(warehouse, sc, partitaIva);
                        break;
                    case RICERCA_PER_PREZZO_DI_VENDITA:
                        searchBySellPrice(warehouse, sc, partitaIva);
                        break;
                    case RICERCA_PER_RANGE_DI_VENDITA:
                        searchByPriceRange(warehouse, sc, partitaIva);
                        break;
                    case AGGIUNGI_AL_CARRELLO:
                        addToCartById(warehouse, cart, sc, partitaIva);
                        break;
                    case RIMUOVI_DAL_CARRELLO:
                        removeFromCartById(warehouse, cart, sc, partitaIva);
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
                            break;
                        } else if (sceltaFinale.equals("2")) {
                            break;
                        } else {
                            System.out.println("Scelta non consentita.");
                        }
                        break;
                    default:
                        System.out.println("Scelta non valida.");
                }
            } catch (InputMismatchException e) {
            }
        } while (sceltaUser != MenuOptionsUser.FINE);
    }

    //Metodo per aggiungere prodotti dal magazzino al carrello
    public static void fromWarehouseToCart(Warehouse warehouse, Cart cart, long id, Boolean iva) {
        cart.addDevice(warehouse.getDeviceById(id));
        warehouse.removeDeviceById(id);
        cart.printAllDevices(iva);
    }

    //Metodo per aggiungere prodotti dal carrello al magazzino
    public static void fromCartToWarehouse(Warehouse warehouse, Cart cart, long id, Boolean iva) {
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
            System.out.println("Seleziona il tipo di user:");
            System.out.println("1) Privato");
            System.out.println("2) Azienda con Partita IVA");
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
    private void addToCartById(Warehouse warehouse, Cart cart, Scanner sc, boolean iva) {
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
            fromWarehouseToCart(warehouse, cart, sceltaId, iva);
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un formato ID corretto.");
            sc.nextLine();
        }
    }

    //Metodo per rimuovere i prodotti dal carrello tramite ID
    private void removeFromCartById(Warehouse warehouse, Cart cart, Scanner sc, boolean iva) {
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
            fromCartToWarehouse(warehouse, cart, sceltaId2, iva);
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un formato ID corretto.");
            sc.nextLine();
        }
    }
}