import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Operatore {
    private Warehouse warehouse;
    private Scanner sc;

    public Operatore(Warehouse warehouse, Scanner sc) {
        this.warehouse = warehouse;
        this.sc = sc;
    }

    // menu con tutti i controlli dell'operatore
    public void operatorMenu() {
        MenuOptionsOperator sceltaUser;
        ResearchMethods researchMethods = new ResearchMethods(warehouse, sc);
        sc.nextLine();
        do {
            System.out.println("Scegli un'opzione:");
            for (int i = 0; i < MenuOptionsOperator.values().length - 1; i++) {
                String optionName = MenuOptionsOperator.values()[i].name().replace("_", " ").toLowerCase();
                optionName = optionName.substring(0, 1).toUpperCase() + optionName.substring(1);
                System.out.println(MenuOptionsOperator.values()[i].ordinal() + ") " + optionName + ": ");
            }
            String input = sc.nextLine();

            if (input.matches("\\d+")) {
                sceltaUser = researchMethods.getMenuOptionsByIndex(input, MenuOptionsOperator.class);
            } else {
                sceltaUser = MenuOptionsOperator.UNKNOWN;
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
                    researchMethods.searchByType(false);
                    break;
                case RICERCA_PER_PRODUTTORE:
                    researchMethods.searchByBrand(false);
                    break;
                case RICERCA_PER_MODELLO:
                    researchMethods.searchByModel(false);
                    break;
                case RICERCA_PER_PREZZO_DI_VENDITA:
                    researchMethods.searchBySellPrice(false);
                    break;
                case RICERCA_PER_PREZZO_DI_ACQUISTO:
                    searchByPurchasePrice(researchMethods, sc);
                    break;
                case RICERCA_PER_RANGE_DI_ACQUISTO:
                    researchMethods.searchByPriceRange(false);
                    break;
                case RICERCA_SPESA_MEDIA_DISPOSITIVO:
                    searchByAverageDevicePrice(researchMethods, sc);
                    break;
                case AGGIUNGI_DISPOSITIVO_AL_MAGAZZINO:
                    DeviceClasses newDevice = addNewDevice(sc, researchMethods);
                    setIdAddDeviceInWarehouse(warehouse, newDevice);
                    break;
                case RIMUOVI_DISPOSITIVO_DAL_MAGAZZINO:
                    removeFromWarehouseById(researchMethods, sc);
                    break;
                case FINE:
                    System.out.println("Arrivederci!");
                    break;
                case UNKNOWN:
                    System.out.println("Opzione non valida. Riprova.");
                    break;

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
    private static DeviceClasses addNewDevice(Scanner sc, ResearchMethods researchMethods) {

        String device = switchDevice(sc);
        String brand = researchMethods.getValidInput("Brand:", 15);
        String model = researchMethods.getValidInput("Modello:", 15);
        String description = researchMethods.getValidInput("Descrizione:", 20);
        double display = researchMethods.getValidDoubleInput("Display:");
        int storage = researchMethods.getValidIntegerInput("Memoria di archiviazione:");
        double purchase = researchMethods.getValidDoubleInput("Prezzo di acquisto:");
        double sale = researchMethods.getValidDoubleInput("Prezzo di vendita:");

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
    private void searchByPurchasePrice(ResearchMethods researchMethods, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        int scelta = researchMethods.getValidIntegerInput("Inserisci il prezzo:");
        ArrayList<DeviceClasses> priceBuyCompatibili = warehouse.getByPurchasePrice(scelta);
        if (priceBuyCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            researchMethods.printDevices(priceBuyCompatibili, false);
        }
    }


    // ricerca per prezzo medio del device
    private void searchByAverageDevicePrice(ResearchMethods researchMethods, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        String scelta = researchMethods.getValidInput("Inserisci il tipo di device di cui vuoi sapere il prezzo medio:", 20);
        double averagePrice = warehouse.getAverageDevicePrice(scelta);
        if (!Double.isNaN(averagePrice) && averagePrice != 0) {
            System.out.println("Il prezzo medio per " + scelta + " è: " + averagePrice);
        } else {
            System.out.println("Errore: inserisci un device valido!");
        }
    }


    private void removeFromWarehouseById(ResearchMethods researchMethods, Scanner sc) {
        try {
            if (warehouse.isEmpty()) {
                System.out.println("Il magazzino e' vuoto!");
                return;
            }
            Long sceltaId2 = researchMethods.getValidLongInput("Digita un id per rimuovere al magazzino:");
            if (sceltaId2 == null) {
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