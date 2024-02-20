import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//
// NON SI COPIA!
// NON SI COPIA!
// NON SI COPIA!
//

public class Operatore {
    public void operatorMenu(Warehouse warehouse) {
        Scanner sc = new Scanner(System.in);
        String sceltaUser;
        do {
            System.out.println("Scegli l operazione da effettuare:");
            System.out.println("1) Visualizza tutti prodotti");
            System.out.println("2) Ricerca per tipo dispositivo:");
            System.out.println("3) Ricerca per produttore:");
            System.out.println("4) Ricerca per modello:");
            System.out.println("5) Ricerca per prezzo di vendita:");
            System.out.println("6) Ricerca per prezzo di acquisto:");
            System.out.println("7) Ricerca per range di prezzo di acquisto:");
            System.out.println("8) Ricerca spesa media dispositivo:");
            System.out.println("9) Aggiungi al magazzino:");
            System.out.println("10) Rimuovi dal magazzino:");
            System.out.println("0) Fine:");
            sceltaUser = sc.next();

            switch (sceltaUser) {
                case "1":
                    if (warehouse.isEmpty()) {
                        System.out.println("Il magazzino e' vuoto!");
                        continue;
                    }
                    warehouse.printAllDevices(false, true);
                    break;
                case "2":
                    searchByType(warehouse, sc);
                    break;
                case "3":
                    searchByBrand(warehouse, sc);
                    break;
                case "4":
                    searchByModel(warehouse, sc);
                    break;
                case "5":
                    searchBySellPrice(warehouse, sc);
                    break;
                case "6":
                    searchByPurchasePrice(warehouse, sc);
                    break;
                case "7":
                    searchByPriceRange(warehouse, sc);
                    break;
                case "8":
                    searchByAverageDevicePrice(warehouse, sc);
                    break;
                case "9":
                    DeviceClasses newDevice = addNewDevice(sc);
                    setIdAddDeviceInWarehouse(warehouse, newDevice);
                    break;
                case "10":
                    try {
                        if (warehouse.isEmpty()) {
                            System.out.println("Il magazzino e' vuoto!");
                            continue;
                        }
                        System.out.println("Digita un id per rimuovere al magazzino:");
                        long sceltaId2 = sc.nextLong();
                        if (!warehouse.containsDeviceById(sceltaId2)) {
                            System.out.println("Non è stato trovato alcun dispositivo con questo ID");
                            break;
                        }
                        warehouse.removeDeviceById(sceltaId2);
                    } catch (InputMismatchException e) {
                        System.out.println("Input non valido, assicurati di inserire un ID corretto");
                        sc.nextLine();
                    }
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Scelta non valida");
            }

        } while (!sceltaUser.equals("0"));
    }

    public static void setIdAddDeviceInWarehouse(Warehouse warehouse, DeviceClasses device) {
        Random rand = new Random();
        warehouse.addDevice(device);
        device.setId(rand.nextLong(999999999));
    }

    public static DeviceClasses addNewDevice(Scanner sc) {

        String device = switchDevice(sc);
        String brand = getValidInput("Brand:", 15, sc);
        String model = getValidInput("Modello:", 15, sc);
        String description = getValidInput("Descrizione:", 20, sc);
        double display = getValidDoubleInput("Display:", sc);
        int storage = getValidIntegerInput("Memoria di archiviazione:", sc);
        double purchase = getValidDoubleInput("Prezzo di acquisto:", sc);
        double sale = getValidDoubleInput("Prezzo di vendita:", sc);

        return new DeviceClasses(sale, device, brand, model, description, display, storage, purchase);
    }

    private static String switchDevice(Scanner sc) {
        sc.nextLine();
        String scelta;
        do {
            System.out.println("Scegli che tipo di dispositivo aggiungere");
            System.out.println("1) Smartphone");
            System.out.println("2) Tablet");
            System.out.println("3) Notebook");
            scelta = sc.nextLine();

            switch (scelta) {
                case "1":
                    return "Smartphone";
                case "2":
                    return "Tablet";
                case "3":
                    return "Notebook";
                default:
                    System.out.println("Scelta non valida");
            }
        } while (true);
    }


    private void searchByType(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        sc.nextLine();
        String sceltaDisp = getValidInput("Inserisci il nome del tipo di dispositivo", 15, sc);
        ArrayList<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaDisp, "device");
        if (devicesCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(devicesCompatibili);

        }
    }

    private void searchByBrand(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        sc.nextLine();
        String sceltaBrand = getValidInput("Inserisci il nome del brand del dispositivo:", 15, sc);
        ArrayList<DeviceClasses> brandCompatibili = warehouse.getCompatibles(sceltaBrand, "brand");
        if (brandCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(brandCompatibili);

        }
    }

    private void searchByModel(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        sc.nextLine();
        String scelta = getValidInput("Inserisci il nome del modello del dispositivo:", 15, sc);
        ArrayList<DeviceClasses> modelCompatibili = warehouse.getCompatibles(scelta, "model");
        if (modelCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(modelCompatibili);
        }
    }

    private void searchBySellPrice(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        sc.nextLine();
        int scelta = getValidIntegerInput("Inserisci il prezzo:", sc);
        ArrayList<DeviceClasses> priceCompatibili = warehouse.getBySellPrice(scelta);
        if (priceCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(priceCompatibili);
        }
    }

    private void searchByPurchasePrice(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }

        sc.nextLine();
        int scelta = getValidIntegerInput("Inserisci il prezzo:", sc);
        ArrayList<DeviceClasses> priceBuyCompatibili = warehouse.getByPurchasePrice(scelta);
        if (priceBuyCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(priceBuyCompatibili);
        }
    }

    private void searchByPriceRange(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        sc.nextLine();
        int scelta1 = getValidIntegerInput("Inserisci il prezzo minimo:", sc);
        int scelta2 = getValidIntegerInput("Inserisci il prezzo massimo:", sc);

        if (scelta1 > scelta2) {
            System.out.println("Il prezzo minimo non può essere maggiore del prezzo massimo.");
            return;
        }

        ArrayList<DeviceClasses> rangeCompatibili = warehouse.getRangeSale(scelta1, scelta2);
        if (rangeCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo in range trovato");
        } else {
            printDevices(rangeCompatibili);
        }

    }

    private void searchByAverageDevicePrice(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        sc.nextLine();
        String scelta = getValidInput("Inserisci il tipo di device di cui vuoi sapere il prezzo medio:", 20, sc);
        double averagePrice = warehouse.getAverageDevicePrice(scelta);
        if (!Double.isNaN(averagePrice) && averagePrice != 0) {
            System.out.println("il prezzo medio è: " + averagePrice);
        } else {
            System.out.println("Errore: inserisci un device valido!");
        }
    }

    private static String getValidInput(String prompt, int maxLength, Scanner sc) {
        String input;
        do {
            System.out.println(prompt);
            input = sc.nextLine().trim();
            if (input.length() > maxLength) {
                System.out.println("La lunghezza massima consentita è " + maxLength + " caratteri.");
            } else {
                return input;
            }
        } while (true);
    }

    private static double getValidDoubleInput(String prompt, Scanner sc) {
        do {
            System.out.println(prompt);
            String input = sc.nextLine().trim();
            if (input.matches("[0-9]+")) {
                try {
                    return Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero intero valido.");
                }
            } else {
                System.out.println("Input non valido. Inserisci un numero intero valido.");
            }
        } while (true);
    }

    private static int getValidIntegerInput(String prompt, Scanner sc) {
        do {
            System.out.println(prompt);
            String input = sc.nextLine().trim();
            if (input.matches("[0-9]+")) {
                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero intero valido.");
                }
            } else {
                System.out.println("Input non valido. Inserisci un numero intero valido.");
            }
        } while (true);
    }

    private void printDevices(ArrayList<DeviceClasses> devices) {
        for (DeviceClasses device : devices) {
            System.out.println("Id: " + device.getId() +
                    ", Dispositivo: " + device.getDevice() +
                    ", Brand: " + device.getBrand() +
                    ", Modello: " + device.getModel() +
                    ", Descrizione: " + device.getDescription() +
                    ", Display: " + device.getDisplay() +
                    ", Archiviazione: " + device.getStorage() +
                    ", Prezzo di vendità: " + device.getSale() +
                    ", Prezzo di acquisto: " + device.getPurchase());
        }
    }
}