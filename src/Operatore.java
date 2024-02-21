import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Operatore extends ResearchMethods {

    // menu con tutti i controlli dell'operatore
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
                    searchByType(warehouse, sc, false);
                    break;
                case "3":
                    searchByBrand(warehouse, sc, false);
                    break;
                case "4":
                    searchByModel(warehouse, sc, false);
                    break;
                case "5":
                    searchBySellPrice(warehouse, sc, false);
                    break;
                case "6":
                    searchByPurchasePrice(warehouse, sc);
                    break;
                case "7":
                    searchByPriceRange(warehouse, sc, false);
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

    // set ID del device utilizzando un Random e aggiunge il device al magazzino
    private static void setIdAddDeviceInWarehouse(Warehouse warehouse, DeviceClasses device) {
        Random rand = new Random();
        warehouse.addDevice(device);
        device.setId(rand.nextLong(999999999));
    }

    // aggiunge un nuovo device al magazzino
    private static DeviceClasses addNewDevice(Scanner sc) {

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

    // aggiunge un device scegliendo soltanto tra le opzioni disponibili
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

    // ricerca per prezzo di acquisto
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
            printDevices(priceBuyCompatibili, false);
        }
    }


    // ricerca per prezzo medio del device
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
}