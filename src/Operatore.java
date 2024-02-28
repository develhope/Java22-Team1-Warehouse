import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

enum MenuOptionsOperator {
    VISUALIZZA_TUTTI_PRODOTTI,
    RICERCA_PER_TIPO_DISPOSITIVO,
    RICERCA_PER_PRODUTTORE,
    RICERCA_PER_MODELLO,
    RICERCA_PER_PREZZO_DI_VENDITA,
    RICERCA_PER_PREZZO_DI_ACQUISTO,
    RICERCA_PER_RANGE_DI_ACQUISTO,
    RICERCA_SPESA_MEDIA_DISPOSITIVO,
    AGGIUNGI_DISPOSITIVO_AL_MAGAZZINO,
    RIMUOVI_DISPOSITIVO_DAL_MAGAZZINO,
    FINE;
}

public class Operatore extends ResearchMethods {

    // menu con tutti i controlli dell'operatore
    public void operatorMenu(Warehouse warehouse) {
        Scanner sc = new Scanner(System.in);
        MenuOptionsOperator sceltaUser;

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
            switch (sceltaUser) {
                case VISUALIZZA_TUTTI_PRODOTTI:
                    if (warehouse.isEmpty()) {
                        System.out.println("Il magazzino e' vuoto!");
                        continue;
                    }
                    warehouse.printAllDevices(false, true);
                    break;
                case RICERCA_PER_TIPO_DISPOSITIVO:
                    searchByType(warehouse, sc, false);
                    break;
                case RICERCA_PER_PRODUTTORE:
                    searchByBrand(warehouse, sc, false);
                    break;
                case RICERCA_PER_MODELLO:
                    searchByModel(warehouse, sc, false);
                    break;
                case RICERCA_PER_PREZZO_DI_VENDITA:
                    searchBySellPrice(warehouse, sc, false);
                    break;
                case RICERCA_PER_PREZZO_DI_ACQUISTO:
                    searchByPurchasePrice(warehouse, sc);
                    break;
                case RICERCA_PER_RANGE_DI_ACQUISTO:
                    searchByPriceRange(warehouse, sc, false);
                    break;
                case RICERCA_SPESA_MEDIA_DISPOSITIVO:
                    searchByAverageDevicePrice(warehouse, sc);
                    break;
                case AGGIUNGI_DISPOSITIVO_AL_MAGAZZINO:
                    DeviceClasses newDevice = addNewDevice(sc);
                    setIdAddDeviceInWarehouse(warehouse, newDevice);
                    break;
                case RIMUOVI_DISPOSITIVO_DAL_MAGAZZINO:
                    removeFromWarehouseById(warehouse, sc);
                    break;
                case FINE:
                    System.out.println("Arrivederci!");
                    break;
                case null:
                    break;
                default:
                    System.out.println("Scelta non valida");
            }

        } while (sceltaUser != MenuOptionsOperator.FINE);
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
        String scelta = getValidInput("Inserisci il tipo di device di cui vuoi sapere il prezzo medio:", 20, sc);
        double averagePrice = warehouse.getAverageDevicePrice(scelta);
        if (!Double.isNaN(averagePrice) && averagePrice != 0) {
            System.out.println("Il prezzo medio per " + scelta + " è: " + averagePrice);
        } else {
            System.out.println("Errore: inserisci un device valido!");
        }
    }



    private void removeFromWarehouseById(Warehouse warehouse, Scanner sc) {
        try {
            if (warehouse.isEmpty()) {
                System.out.println("Il magazzino e' vuoto!");
                return;
            }
            Long sceltaId2 = getValidLongInput("Digita un id per rimuovere al magazzino:", sc);
            if(sceltaId2 == null) {
                return;
            }

            if (!warehouse.containsDeviceById(sceltaId2)) {
                System.out.println("Non è stato trovato alcun dispositivo con questo ID");
                return;
            }
            warehouse.removeDeviceById(sceltaId2);
        } catch (InputMismatchException e) {
            System.out.println("Input non valido, assicurati di inserire un ID corretto");
        }
    }
}