package MenusMethods;

import Devices.DeviceClasses;
import Utils.GetValidInput;
import WarehouseManagement.Warehouse;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class OperatorMethods {
    private final Warehouse warehouse;
    private final Scanner sc;

    public OperatorMethods(Warehouse warehouse, Scanner sc) {
        this.sc = sc;
        this.warehouse = warehouse;
    }

    // set ID del device utilizzando un Random e aggiunge il device al magazzino
    public void setIdAddDeviceInWarehouse(DeviceClasses device) {
        Random rand = new Random();
        warehouse.addDevice(device);
        device.setId(rand.nextLong(999999999));
    }

    // aggiunge un nuovo device al magazzino
    public DeviceClasses addNewDevice() {

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
    public String switchDevice() {
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
    public void searchByPurchasePrice() {
        int scelta = GetValidInput.getInteger("Inserisci il prezzo:");
        List<DeviceClasses> priceBuyCompatibili = warehouse.getByPurchasePrice(scelta);
        if (priceBuyCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(priceBuyCompatibili);
        }
    }

    // ricerca per prezzo medio del device
    public void searchByAverageDevicePrice() {
        String scelta = GetValidInput.getString("Inserisci il tipo di device di cui vuoi sapere il prezzo medio:", 20);
        double averagePrice = warehouse.getAverageDevicePrice(scelta);
        if (!Double.isNaN(averagePrice) && averagePrice != 0) {
            System.out.println("Il prezzo medio per " + scelta + " è: " + averagePrice);
        } else {
            System.out.println("Errore: inserisci un device valido!");
        }
    }

    public void removeFromWarehouseById() {
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

    public void printDevices(List<DeviceClasses> devices) {
        DecimalFormat df = new DecimalFormat("#.##");
        for (DeviceClasses device : devices) {
            System.out.println("Id: " + device.getId() +
                    ", Dispositivo: " + device.getDevice() +
                    ", Brand: " + device.getBrand() +
                    ", Modello: " + device.getModel() +
                    ", Descrizione: " + device.getDescription() +
                    ", Display: " + df.format(device.getDisplay()) +
                    ", Archiviazione: " + df.format(device.getStorage()) +
                    ", Prezzo di vendità: " + df.format(device.getSale()) + "€" + df.format(device.getPurchase()) + "€");
        }
    }
}