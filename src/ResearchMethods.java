import Devices.DeviceClasses;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ResearchMethods {
    private Warehouse warehouse;
    private Scanner sc;

    public ResearchMethods(Warehouse warehouse, Scanner sc) {
        this.warehouse = warehouse;
        this.sc = sc;
    }

    // Stampa i device passati, cambia prezzi con iva se viene passata
    void printDevices(ArrayList<DeviceClasses> devices, boolean iva) {
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
    String getValidInput(String prompt, int maxLength) {
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


    // Prende un input double e lo valida prima di ritornarlo

    double getValidDoubleInput(String prompt) {
        do {
            System.out.println(prompt);
            String input = sc.nextLine().trim();
            if (input.matches(("[0-9]+(.[0-9]+)?"))) {
                try {
                    return Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero valido.");
                }
            } else {
                System.out.println("Input non valido. Inserisci un numero valido.");
            }
        } while (true);
    }

    Long getValidLongInput(String prompt) {
        do {
            System.out.println(prompt);
            String input = sc.nextLine().trim();
            if (input.matches(("[0-9]+(.[0-9]+)?"))) {
                try {
                    return Long.parseLong(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero valido.");
                }
            } else {
                System.out.println("Input non valido. Inserisci un numero valido.");
                break;
            }
        } while (true);
        return null;
    }

    // Prende un input integer e lo valida prima di ritornarlo
    int getValidIntegerInput(String prompt) {
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

    // Cerca per tipo, ritorna i dispositivi trovati con prezzi dipendenti da iva

    void searchByType(boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        String sceltaDisp = getValidInput("Inserisci il nome del tipo di dispositivo", 15);
        ArrayList<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaDisp, "device");
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
        String sceltaBrand = getValidInput("Inserisci il nome del brand del dispositivo:", 15);
        ArrayList<DeviceClasses> brandCompatibili = warehouse.getCompatibles(sceltaBrand, "brand");
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
        String scelta = getValidInput("Inserisci il nome del modello del dispositivo:", 15);
        ArrayList<DeviceClasses> modelCompatibili = warehouse.getCompatibles(scelta, "model");
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
        int price = getValidIntegerInput("Inserisci il prezzo:");
        int searchedPrice = iva ? (int) (price / 1.22) : price;
        ArrayList<DeviceClasses> priceCompatibili = warehouse.getBySellPrice(searchedPrice);
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
        int minPrice = getValidIntegerInput("Inserisci il prezzo minimo:");
        int maxPrice = getValidIntegerInput("Inserisci il prezzo massimo:");

        int minSearchedPrice = iva ? (int) (minPrice / 1.22) : minPrice;
        int maxSearchedPrice = iva ? (int) (maxPrice / 1.22) : maxPrice;

        if (minSearchedPrice > maxSearchedPrice) {
            System.out.println("Il prezzo minimo non può essere maggiore del prezzo massimo.");
            return;
        }

        ArrayList<DeviceClasses> rangeCompatibili = warehouse.getRangeSale(minSearchedPrice, maxSearchedPrice);
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