package Devices;

import java.util.Objects;

public class DeviceClasses {
    // Dichiarazione delle variabili di istanza
    private String device;
    private String brand;
    private String model;
    private String description;
    private double display;
    private double storage;
    private double purchase;
    private double sale;
    private long id;

    // Costruttore della classe DeviceClasses
    public DeviceClasses(double sale,
                         String device,
                         String brand,
                         String model,
                         String description,
                         double display,
                         double storage,
                         double purchase) {
        // Inizializzazione delle variabili di istanza tramite i parametri del costruttore
        this.device = device;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.display = display;
        this.storage = storage;
        this.purchase = purchase;
        this.sale = sale;
    }

    // Metodo per ottenere l'ID del dispositivo
    public long getId() {
        return id;
    }

    // Metodo per impostare l'ID del dispositivo
    public void setId(long id) {
        this.id = id;
    }

    // Metodi setter per impostare i valori delle variabili di istanza
    public void setDevice(String device) {
        this.device = device;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    // Metodi getter per ottenere i valori delle variabili di istanza
    public String getDevice() {
        return device;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    public double getDisplay() {
        return display;
    }

    public double getStorage() {
        return storage;
    }

    public double getPurchase() {
        return purchase;
    }

    public double getSale() {
        return sale;
    }

    // Metodo toString per rappresentare l'oggetto DeviceClasses come stringa
    @Override
    public String toString() {
        return "Device: " + device +
                ", Brand: " + brand +
                ", Model: " + model +
                ", Description: " + description +
                ", Display: " + display +
                ", Storage: " + storage +
                ", Purchase " + purchase +
                ", Sale: " + sale +
                ", Id: " + id + "\n";
    }
}