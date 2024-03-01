package WarehouseManagement;

import Devices.DeviceClasses;
import Utils.GetValidInput;
import WarehouseManagement.Warehouse;

import java.text.DecimalFormat;
import java.util.List;

public class ResearchMethods {
    private final Warehouse warehouse;
    private boolean iva;
    private boolean includePurchasePrice;

    public ResearchMethods(Warehouse warehouse, boolean iva, boolean includePurchasePrice) {
        this.warehouse = warehouse;
        this.iva = iva;
        this.includePurchasePrice = includePurchasePrice;
    }

    // Stampa i device passati, cambia prezzi con iva se viene passata
    public void printDevices(List<DeviceClasses> devices) {
        if (devices.isEmpty()) {
            String message = !includePurchasePrice ? "Il carrello è vuoto." : "Il magazzino è vuoto.";
            System.out.println(message);
            return;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        for (DeviceClasses device : devices) {
            String purchasePrice = includePurchasePrice ? ", Prezzo di acquisto: " + df.format(device.getPurchase()) : "";
            double price = iva ? device.getPriceWithIVA() : device.getSale();
            System.out.println("Id: " + device.getId() +
                    ", Dispositivo: " + device.getDevice() +
                    ", Brand: " + device.getBrand() +
                    ", Modello: " + device.getModel() +
                    ", Descrizione: " + device.getDescription() +
                    ", Display: " + df.format(device.getDisplay()) +
                    ", Archiviazione: " + df.format(device.getStorage()) +
                    ", Prezzo di vendità: " + df.format(price)+ "€" + purchasePrice + "€");
        }
    }

    public void searchByType() {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        String sceltaDisp = GetValidInput.getString("Inserisci il nome del tipo di dispositivo", 15);
        List<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaDisp, "device");
        if (devicesCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(devicesCompatibili);
        }
    }

    // Cerca per brand, ritorna i dispositivi trovati con prezzi dipendenti da iva

    public void searchByBrand() {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        String sceltaBrand = GetValidInput.getString("Inserisci il nome del brand del dispositivo:", 15);
        List<DeviceClasses> brandCompatibili = warehouse.getCompatibles(sceltaBrand, "brand");
        if (brandCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(brandCompatibili);

        }
    }

    // Cerca per modello, ritorna i dispositivi trovati con prezzi dipendenti da iva

    public void searchByModel() {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        String scelta = GetValidInput.getString("Inserisci il nome del modello del dispositivo:", 15);
        List<DeviceClasses> modelCompatibili = warehouse.getCompatibles(scelta, "model");
        if (modelCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(modelCompatibili);
        }
    }

    // Cerca per prezzo di vendità, ritorna i dispositivi trovati con prezzi dipendenti da iva

    public void searchBySellPrice() {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        int price = GetValidInput.getInteger("Inserisci il prezzo:");
        int searchedPrice = iva ? (int) (price / 1.22) : price;
        List<DeviceClasses> priceCompatibili = warehouse.getBySellPrice(searchedPrice);
        if (priceCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(priceCompatibili);
        }
    }

    // ricerca per range del prezzo di vendita
    public void searchByPriceRange() {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        int minPrice = GetValidInput.getInteger("Inserisci il prezzo minimo:");
        int maxPrice = GetValidInput.getInteger("Inserisci il prezzo massimo:");

        int minSearchedPrice = iva ? (int) (minPrice / 1.22) : minPrice;
        int maxSearchedPrice = iva ? (int) (maxPrice / 1.22) : maxPrice;

        if (minSearchedPrice > maxSearchedPrice) {
            System.out.println("Il prezzo minimo non può essere maggiore del prezzo massimo.");
            return;
        }

        List<DeviceClasses> rangeCompatibili = warehouse.getRangeSale(minSearchedPrice, maxSearchedPrice);
        if (rangeCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo in range trovato");
        } else {
            printDevices(rangeCompatibili);
        }
    }
}