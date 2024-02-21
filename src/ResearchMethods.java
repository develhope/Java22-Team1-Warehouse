import Devices.DeviceClasses;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class ResearchMethods {
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

    static String getValidInput(String prompt, int maxLength, Scanner sc) {
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

    static double getValidDoubleInput(String prompt, Scanner sc) {
        do {
            System.out.println(prompt);
            String input = sc.nextLine().trim();
            if (input.matches(("[0-9]+(.[0-9]+)?"))) {
                try {
                    return Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero intero valido.");
                }
            } else {
                System.out.println("Input non valido. Inserisci un numero intero valido.");
            }
        } while (true);
    }

    // Prende un input integer e lo valida prima di ritornarlo
    static int getValidIntegerInput(String prompt, Scanner sc) {
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

    void searchByType(Warehouse warehouse, Scanner sc, boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        sc.nextLine();
        String sceltaDisp = getValidInput("Inserisci il nome del tipo di dispositivo", 15, sc);
        ArrayList<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaDisp, "device");
        if (devicesCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(devicesCompatibili, iva);

        }
    }

    // Cerca per brand, ritorna i dispositivi trovati con prezzi dipendenti da iva

    void searchByBrand(Warehouse warehouse, Scanner sc, boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        sc.nextLine();
        String sceltaBrand = getValidInput("Inserisci il nome del brand del dispositivo:", 15, sc);
        ArrayList<DeviceClasses> brandCompatibili = warehouse.getCompatibles(sceltaBrand, "brand");
        if (brandCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(brandCompatibili, iva);

        }
    }

    // Cerca per modello, ritorna i dispositivi trovati con prezzi dipendenti da iva

    void searchByModel(Warehouse warehouse, Scanner sc, boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        sc.nextLine();
        String scelta = getValidInput("Inserisci il nome del modello del dispositivo:", 15, sc);
        ArrayList<DeviceClasses> modelCompatibili = warehouse.getCompatibles(scelta, "model");
        if (modelCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(modelCompatibili, iva);
        }
    }

    // Cerca per prezzo di vendità, ritorna i dispositivi trovati con prezzi dipendenti da iva

    void searchBySellPrice(Warehouse warehouse, Scanner sc, boolean iva) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        sc.nextLine();
        int price = getValidIntegerInput("Inserisci il prezzo:", sc);
        int searchedPrice = iva ? (int) (price / 1.22) : price;
        ArrayList<DeviceClasses> priceCompatibili = warehouse.getBySellPrice(searchedPrice);
        if (priceCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            printDevices(priceCompatibili, iva);
        }
    }

}