import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//
// NON SI COPIA!
// NON SI COPIA!
// NON SI COPIA!
//

public class User {
    public void userMenu(Cart cart, Warehouse warehouse) {
        Scanner sc = new Scanner(System.in);
        String sceltaUser;

        boolean partitaIva = getIvaUser(new Scanner(System.in));

        do {
            System.out.println("Scegli l operazione da effettuare:");
            System.out.println("1) Visualizza tutti prodotti");
            System.out.println("2) Ricerca per tipo dispositivo:");
            System.out.println("3) Ricerca per produttore:");
            System.out.println("4) Ricerca per modello:");
            System.out.println("5) Ricerca per prezzo di vendita:");
            System.out.println("6) Ricerca per range di prezzo:");
            System.out.println("7) Aggiungi al carrello:");
            System.out.println("8) Rimuovi dal carrello:");
            System.out.println("9) Calcolare il totale:");
            System.out.println("10) Visualizza carrello:");
            System.out.println("11) Acquista:");
            System.out.println("0) Fine:");
            sceltaUser = sc.next();

            try {
                switch (sceltaUser) {

                    case "1":
                        if (warehouse.isEmpty()) {
                            System.out.println("Il magazzino è vuoto.");
                            continue;
                        }
                        warehouse.printAllDevices(partitaIva, false);
                        break;
                    case "2":
                        searchByType(warehouse, sc, partitaIva);
                        break;
                    case "3":
                        searchByBrand(warehouse, sc, partitaIva);
                        break;
                    case "4":
                        searchByModel(warehouse, sc, partitaIva);
                        break;
                    case "5":
                        searchBySellPrice(warehouse, sc, partitaIva);
                        break;
                    case "6":
                        searchByPriceRange(warehouse, sc, partitaIva);
                        break;
                    case "7":
                        addToCartById(warehouse, cart, sc, partitaIva);
                        break;
                    case "8":
                        removeFromCartById(warehouse, cart, sc, partitaIva);
                        break;
                    case "9":
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
                    case "10":
                        cart.printAllDevices(partitaIva);
                        break;
                    case "11":
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
                    case "0":
                        break;
                    default:
                        System.out.println("Scelta non valida.");
                }
            } catch (InputMismatchException e) {
            }
        } while (!sceltaUser.equals("0"));
    }

    public static void fromWarehouseToCart(Warehouse warehouse, Cart cart, long id, Boolean iva) {
        cart.addDevice(warehouse.getDeviceById(id));
        warehouse.removeDeviceById(id);
        cart.printAllDevices(iva);
    }

    public static void fromCartToWarehouse(Warehouse warehouse, Cart cart, long id, Boolean iva) {
        warehouse.addDevice(cart.getDeviceById(id));
        cart.removeDeviceById(id);
        cart.printAllDevices(iva);
    }

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

    private void searchByType(Warehouse warehouse, Scanner sc, boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        System.out.println("Inserisci il nome del tipo di dispositivo:");
        String sceltaDisp = sc.next();
        ArrayList<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaDisp, "device");
        if (devicesCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(devicesCompatibili, iva);
        }
    }

    private void searchByBrand(Warehouse warehouse, Scanner sc, boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        System.out.println("Inserisci il nome del brand del dispositivo:");
        String sceltaBrand = sc.next();
        ArrayList<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaBrand, "brand");
        if (devicesCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(devicesCompatibili, iva);
        }
    }


    private void searchByModel(Warehouse warehouse, Scanner sc, boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        System.out.println("Inserisci il nome del modello del dispositivo:");
        String sceltaModel = sc.next();
        ArrayList<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaModel, "model");
        if (devicesCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(devicesCompatibili, iva);
        }
    }


    private void searchBySellPrice(Warehouse warehouse, Scanner sc, boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        System.out.println("Inserisci il prezzo:");
        try {
            int price = Integer.parseInt(sc.next());

            int searchedPrice = iva ? (int) (price / 1.22) : price;

            ArrayList<DeviceClasses> priceCompatibili = warehouse.getBySellPrice(searchedPrice);
            if (priceCompatibili.isEmpty()) {
                System.out.println("Nessun dispositivo compatibile trovato.");
            } else {
                printDevices(priceCompatibili, iva);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, inserisci un numero valido per il prezzo.");
            sc.nextLine();
        }
    }


    private void searchByPriceRange(Warehouse warehouse, Scanner sc, boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        try {
            System.out.println("Inserisci il prezzo minimo:");
            int minPrice = Integer.parseInt(sc.next());
            System.out.println("Inserisci il prezzo massimo:");
            int maxPrice = Integer.parseInt(sc.next());

            int minSearchedPrice = iva ? (int) (minPrice / 1.22) : minPrice;
            int maxSearchedPrice = iva ? (int) (maxPrice / 1.22) : maxPrice;

            ArrayList<DeviceClasses> rangeCompatibili = warehouse.getRangeSale(minSearchedPrice, maxSearchedPrice);
            if (rangeCompatibili.isEmpty()) {
                System.out.println("Nessun dispositivo in range trovato.");
            } else {
                printDevices(rangeCompatibili, iva);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un numero.");
            sc.nextLine();
        }
    }


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

    private void printDevices(ArrayList<DeviceClasses> devices, boolean iva) {
        for (DeviceClasses device : devices) {
            double price = iva ? device.getPriceWithIVA() : device.getSale();
            System.out.println("Id: " + device.getId() +
                    ", Dispositivo: " + device.getDevice() +
                    ", Brand: " + device.getBrand() +
                    ", Modello: " + device.getModel() +
                    ", Descrizione: " + device.getDescription() +
                    ", Display: " + device.getDisplay() +
                    ", Archiviazione: " + device.getStorage() +
                    ", Prezzo: " + price);
        }
    }
}