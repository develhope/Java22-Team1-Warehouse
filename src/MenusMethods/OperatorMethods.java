package MenusMethods;

import Devices.DeviceClasses;
import Utils.GetValidInput;
import WarehouseManagement.Warehouse;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;


public class OperatorMethods {
    private final GetValidInput getValidInput = new GetValidInput();
    private final Warehouse warehouse;

    public OperatorMethods(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    // set ID del device utilizzando un Random e aggiunge il device al magazzino
    public boolean setIdAddDeviceInWarehouse(DeviceClasses device) {
        Random rand = new Random();
        device.setId(rand.nextLong(999999999));
        return warehouse.addDevice(device);
    }

    // aggiunge un nuovo device al magazzino
    public DeviceClasses addNewDevice() {

        String device = switchDevice();
        String brand = getValidInput.getString("Brand:", 15);
        String model = getValidInput.getString("Modello:", 15);
        String description = getValidInput.getString("Descrizione:", 20);
        double display = getValidInput.getDouble("Display:");
        int storage = getValidInput.getInteger("Memoria di archiviazione:");
        double purchase = getValidInput.getDouble("Prezzo di acquisto:");
        double sale = getValidInput.getDouble("Prezzo di vendita:");

        return new DeviceClasses(sale, device, brand, model, description, display, storage, purchase);
    }

    // aggiunge un device scegliendo soltanto tra le opzioni disponibili
    public String switchDevice() {
        int scelta;
        String sceltaString = null;
        do {
            scelta = getValidInput.getInteger("Scegli che tipo di dispositivo aggiungere: \n" +
                    "1) Smartphone\n" +
                    "2) Tablet\n" +
                    "3) Notebook");

            switch (scelta) {
                case 1:
                    sceltaString = "Smartphone";
                    break;
                case 2:
                    sceltaString = "Tablet";
                    break;
                case 3:
                    sceltaString = "Notebook";
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        } while (scelta != 1 && scelta != 2 && scelta != 3);
        return sceltaString;
    }

    // ricerca per prezzo di acquisto
    public List<DeviceClasses> searchByPurchasePrice() {
        int scelta = getValidInput.getInteger("Inserisci il prezzo:");
        return warehouse.getByPurchasePrice(scelta);
    }

    // ricerca per prezzo medio del device
    public String searchByAverageDevicePrice() {
        String scelta = getValidInput.getString("Inserisci il tipo di device di cui vuoi sapere il prezzo medio:", 20);
        double averagePrice = warehouse.getAverageDevicePrice(scelta);
        if (!Double.isNaN(averagePrice) && averagePrice != 0) {
            return "Il prezzo medio per " + scelta + " è: " + averagePrice;
        } else {
            return "Errore: inserisci un device valido!";
        }
    }

    public void removeFromWarehouseById() {
        try {
            if (warehouse.isEmpty()) {
                System.out.println("Il magazzino e' vuoto!");
            } else {
                long sceltaId2 = getValidInput.getLong("Digita un id per rimuovere al magazzino:");

                if (!warehouse.containsDeviceById(sceltaId2)) {
                    System.out.println("Non è stato trovato alcun dispositivo con questo ID");
                } else {
                    warehouse.removeDeviceById(sceltaId2);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input non valido, assicurati di inserire un ID corretto");
        }
    }


    public void printDevices(List<DeviceClasses> devices) {
        if (devices == null) {
            return;
        }
        if (devices.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            for (DeviceClasses device : devices) {
                System.out.println("Id: " + device.getId() +
                        ", Dispositivo: " + device.getDevice() +
                        ", Brand: " + device.getBrand() +
                        ", Modello: " + device.getModel() +
                        ", Descrizione: " + device.getDescription() +
                        ", Display: " + df.format(device.getDisplay()) +
                        ", Archiviazione: " + df.format(device.getStorage()) +
                        ", Prezzo di vendità: " + df.format(device.getSale()) + "€" + ", Prezzo di acquisto: " + df.format(device.getPurchase()) + "€");
            }
        }
    }
}