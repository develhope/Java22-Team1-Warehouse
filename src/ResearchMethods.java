import Devices.DeviceClasses;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class ResearchMethods {
    private Warehouse warehouse;
    private Scanner sc;

    public ResearchMethods(Warehouse warehouse, Scanner sc) {
        this.warehouse = warehouse;
        this.sc = sc;
    }

    // Stampa i device passati, cambia prezzi con iva se viene passata
    void printDevices(List<DeviceClasses> devices, boolean iva) {
        DecimalFormat df = new DecimalFormat("#.##");
        for (DeviceClasses device : devices) {
            double price = iva ? device.getPriceWithIVA() : device.getSale();
            System.out.println("Id: " + device.getId() +
                    ", Dispositivo: " + device.getDevice() +
                    ", Brand: " + device.getBrand() +
                    ", Modello: " + device.getModel() +
                    ", Descrizione: " + device.getDescription() +
                    ", Display: " + df.format(device.getDisplay()) +
                    ", Archiviazione: " + df.format(device.getStorage()) +
                    ", Prezzo: " + df.format(price));
        }
    }

    // Prende un input string e lo valida prima di ritornarlo
    
    // Cerca per tipo, ritorna i dispositivi trovati con prezzi dipendenti da iva

    void searchByType(boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        String sceltaDisp = GetValidInput.getString("Inserisci il nome del tipo di dispositivo", 15);
        List<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaDisp, "device");
        if (devicesCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(devicesCompatibili, iva);

        }
    }

    // Cerca per brand, ritorna i dispositivi trovati con prezzi dipendenti da iva

    void searchByBrand(boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        String sceltaBrand = GetValidInput.getString("Inserisci il nome del brand del dispositivo:", 15);
        List<DeviceClasses> brandCompatibili = warehouse.getCompatibles(sceltaBrand, "brand");
        if (brandCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(brandCompatibili, iva);

        }
    }

    // Cerca per modello, ritorna i dispositivi trovati con prezzi dipendenti da iva

    void searchByModel(boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        String scelta = GetValidInput.getString("Inserisci il nome del modello del dispositivo:", 15);
        List<DeviceClasses> modelCompatibili = warehouse.getCompatibles(scelta, "model");
        if (modelCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(modelCompatibili, iva);
        }
    }

    // Cerca per prezzo di vendità, ritorna i dispositivi trovati con prezzi dipendenti da iva

    void searchBySellPrice(boolean iva) {
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
            printDevices(priceCompatibili, iva);
        }
    }

    // ricerca per range del prezzo di vendita
    void searchByPriceRange(boolean iva) {
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
            printDevices(rangeCompatibili, iva);
        }
    }

    <T extends Enum<T>> MenuOptionsOperator getMenuOptionsByIndex(String input, Class<MenuOptionsOperator> enumClass) {
        int index = Integer.parseInt(input);
        if (index >= 0 && index < enumClass.getEnumConstants().length - 1) {
            return enumClass.getEnumConstants()[index];
        } else {
            return MenuOptionsOperator.UNKNOWN;
        }
    }

    <T extends Enum<T>> MenuOptionsUser getMenuOptionsByIndexUser(String input, Class<MenuOptionsUser> enumClass) {
        int index = Integer.parseInt(input);
        if (index >= 0 && index < enumClass.getEnumConstants().length - 1) {
            return enumClass.getEnumConstants()[index];
        } else {
            return MenuOptionsUser.UNKNOWN;
        }
    }
}