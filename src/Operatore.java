import Devices.DeviceClasses;

import java.util.List;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Operatore {
    private final Warehouse warehouse;
    private final Scanner sc;
    private ResearchMethods researchMethods;

    public Operatore(Warehouse warehouse, Scanner sc) {
        this.warehouse = warehouse;
        this.sc = sc;
    }

    // menu con tutti i controlli dell'operatore
    public void operatorMenu() {
        this.researchMethods = new ResearchMethods(warehouse, false, true);

        MenuOptionsOperator sceltaUser;
        sc.nextLine();

        do {
            System.out.println("Scegli un'opzione:");
            for (int i = 0; i < MenuOptionsOperator.values().length - 1; i++) {
                System.out.println(MenuOptionsOperator.values()[i].ordinal() + ") " + MenuOptionsOperator.values()[i].getStringa() + ": ");
            }
            String input = sc.nextLine();

            if (input.matches("\\d+")) {
                sceltaUser = GetValidInput.getMenuOptionsByIndex(input);
            } else {
                sceltaUser = MenuOptionsOperator.UNKNOWN;
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
                case RICERCA_PER_PREZZO_DI_ACQUISTO:
                    searchByPurchasePrice();
                    break;
                case RICERCA_PER_RANGE_DI_ACQUISTO:
                    researchMethods.searchByPriceRange();
                    break;
                case RICERCA_SPESA_MEDIA_DISPOSITIVO:
                    searchByAverageDevicePrice();
                    break;
                case AGGIUNGI_DISPOSITIVO_AL_MAGAZZINO:
                    DeviceClasses newDevice = addNewDevice();
                    setIdAddDeviceInWarehouse(newDevice);
                    break;
                case RIMUOVI_DISPOSITIVO_DAL_MAGAZZINO:
                    removeFromWarehouseById();
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
    private void setIdAddDeviceInWarehouse(DeviceClasses device) {
        Random rand = new Random();
        warehouse.addDevice(device);
        device.setId(rand.nextLong(999999999));
    }

    // aggiunge un nuovo device al magazzino
    private DeviceClasses addNewDevice() {

        String device = switchDevice();
        String brand = GetValidInput.getString("Brand:", 15);
        String model = GetValidInput.getString("Modello:", 15);
        String description = GetValidInput.getString("Descrizione:", 20);
        double display = GetValidInput.getDouble("Display:");
        int storage = GetValidInput.getInteger("Memoria di archiviazione:");
        double purchase = GetValidInput.getDouble("Prezzo di acquisto:");
        double sale = GetValidInput.getDouble("Prezzo di vendita:");

        return new DeviceClasses(sale, device, brand, model, description, display, storage, purchase);
    }

    // aggiunge un device scegliendo soltanto tra le opzioni disponibili
    private String switchDevice() {
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
    private void searchByPurchasePrice() {
        int scelta = GetValidInput.getInteger("Inserisci il prezzo:");
        List<DeviceClasses> priceBuyCompatibili = warehouse.getByPurchasePrice(scelta);
        if (priceBuyCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            researchMethods.printDevices(priceBuyCompatibili);
        }
    }

    // ricerca per prezzo medio del device
    private void searchByAverageDevicePrice() {
        String scelta = GetValidInput.getString("Inserisci il tipo di device di cui vuoi sapere il prezzo medio:", 20);
        double averagePrice = warehouse.getAverageDevicePrice(scelta);
        if (!Double.isNaN(averagePrice) && averagePrice != 0) {
            System.out.println("Il prezzo medio per " + scelta + " è: " + averagePrice);
        } else {
            System.out.println("Errore: inserisci un device valido!");
        }
    }

    private void removeFromWarehouseById() {
        try {
            if (warehouse.isEmpty()) {
                System.out.println("Il magazzino e' vuoto!");
                return;
            }
            Long sceltaId2 = GetValidInput.getLong("Digita un id per rimuovere al magazzino:");
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