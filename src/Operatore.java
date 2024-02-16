import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//IL CODICE CREATO NON VA RICOPIATO

// Classe per gestire le azioni dell'operatore
public class Operatore {

    // Metodo per visualizzare il menu dell'operatore e gestire le operazioni
    public void operatorMenu(Warehouse warehouse) {
        Scanner sc = new Scanner(System.in);
        String sceltaUser;
        do {
            // Mostra le opzioni disponibili per l'operatore
            System.out.println("Scegli l operazione da effettuare:");
            System.out.println("1) Visualizza tutti prodotti");
            System.out.println("2) Ricerca per tipo dispositivo:");
            System.out.println("3) Ricerca per produttore:");
            System.out.println("4) Ricerca per modello:");
            System.out.println("5) Ricerca per prezzo di vendita:");
            System.out.println("6) Ricerca per prezzo di acquisto:");
            System.out.println("7) Ricerca per range di prezzo:");
            System.out.println("8) Ricerca spesa media dispositivo");
            System.out.println("9) Aggiungi al magazzino:");
            System.out.println("10) Rimuovi dal magazzino:");
            System.out.println("0) Fine:");
            sceltaUser = sc.next();

            switch (sceltaUser) {
                case "1":
                    if (warehouse.isEmpty()) {
                        System.out.println("Il magazzino e' vuoto!");
                        continue;
                    }
                    warehouse.printAllDevices();
                    break;
                case "2":
                    searchByType(warehouse, sc);
                    break;
                case "3":
                    searchByBrand(warehouse, sc);
                    break;
                case "4":
                    searchByModel(warehouse, sc);
                    break;
                case "5":
                    searchBySellPrice(warehouse, sc);
                    break;
                case "6":
                    searchByPurchasePrice(warehouse, sc);
                    break;
                case "7":
                    searchByPriceRange(warehouse, sc);
                    break;
                case "8":
                    searchByAverageDevicePrice(warehouse, sc);
                    break;
                case "9":
                    // Aggiungi un nuovo dispositivo al magazzino
                    DeviceClasses newDevice = addNewDevice();
                    setIdAddDeviceInWarehouse(warehouse, newDevice);
                    break;
                case "10":
                    // Rimuovi un dispositivo dal magazzino
                    try {
                        if (warehouse.isEmpty()) {
                            System.out.println("Il magazzino e' vuoto!");
                            continue;
                        }
                        System.out.println("Digita un id per rimuovere al magazzino:");
                        long sceltaId2 = sc.nextLong();
                        if (!warehouse.containsDeviceById(sceltaId2)) {
                            System.out.println("Non è stato trovato alcun dispositivo con questo ID");
                            break;
                        }
                        warehouse.removeDeviceById(sceltaId2);
                    } catch (InputMismatchException e) {
                        System.out.println("Input non valido, assicurati di inserire un ID corretto");
                        sc.nextLine();
                    }
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Scelta non valida");
            }

        } while (!sceltaUser.equals("0"));
    }

    // Metodo per impostare un ID e aggiungere un dispositivo al magazzino
    public static void setIdAddDeviceInWarehouse(Warehouse warehouse, DeviceClasses device) {
        Random rand = new Random();
        warehouse.addDevice(device);
        device.setId(rand.nextLong(999999999));
    }

    // Metodo per aggiungere un nuovo dispositivo
    public static DeviceClasses addNewDevice() {
        Scanner sc = new Scanner(System.in);
        String device = null, brand = null, model = null, description = null;
        double display = 0, purchase = 0, sale = 0;
        int storage = 0;

        while (true) {
            try {
                System.out.println("Inserisci i dati del dispositivo:");
                if (device == null) {
                    System.out.println("Dispositivo:");
                    device = sc.next();
                }
                if (brand == null) {
                    System.out.println("Brand:");
                    brand = sc.next();
                }
                if (model == null) {
                    System.out.println("Modello:");
                    model = sc.next();
                }
                if (description == null) {
                    System.out.println("Descrizione:");
                    description = sc.next();
                }
                if (display == 0) {
                    System.out.println("Display:");
                    display = sc.nextDouble();
                }
                if (storage == 0) {
                    System.out.println("Memoria di archiviazione:");
                    storage = sc.nextInt();
                }
                if (purchase == 0) {
                    System.out.println("Prezzo di acquisto:");
                    purchase = sc.nextDouble();
                }
                if (sale == 0) {
                    System.out.println("Prezzo di vendita:");
                    sale = sc.nextDouble();
                }
                if (device != null && brand != null && model != null && description != null && display != 0 && storage != 0 && purchase != 0 && sale != 0) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Assicurati di inserire un formato corretto.");
                sc.nextLine();
            }
        }
        return new DeviceClasses(sale, device, brand, model, description, display, storage, purchase);
    }

    // Metodo per la ricerca per tipo dispositivo
    private void searchByType(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        System.out.println("Inserisci il nome del tipo di dispositivo:");
        String sceltaDisp = sc.next();
        ArrayList<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaDisp, "device");
        if (devicesCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            System.out.println(devicesCompatibili);
        }
    }

    // Metodo per la ricerca per produttore
    private void searchByBrand(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        System.out.println("Inserisci il nome del brand del dispositivo:");
        String sceltaBrand = sc.next();
        ArrayList<DeviceClasses> brandCompatibili = warehouse.getCompatibles(sceltaBrand, "brand");
        if (brandCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            System.out.println(brandCompatibili);
        }
    }

    // Metodo per la ricerca per modello
    private void searchByModel(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        System.out.println("Inserisci il nome del modello del dispositivo:");
        String scelta = sc.next();
        ArrayList<DeviceClasses> modelCompatibili = warehouse.getCompatibles(scelta, "model");
        if (modelCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            System.out.println(modelCompatibili);
        }
    }

    // Metodo per la ricerca per prezzo di vendita
    private void searchBySellPrice(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        System.out.println("Inserisci il prezzo:");
        try {
            int scelta = Integer.parseInt(sc.next());
            ArrayList<DeviceClasses> priceCompatibili = warehouse.getBySellPrice(scelta);
            if (priceCompatibili.isEmpty()) {
                System.out.println("Nessun dispositivo compatibile trovato.");
            } else {
                System.out.println(priceCompatibili);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, inserisci un numero valido per il prezzo!");
            sc.nextLine();
        }
    }

    // Metodo per la ricerca per prezzo di acquisto
    private void searchByPurchasePrice(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        System.out.println("Inserisci il prezzo:");
        try {
            int scelta = Integer.parseInt(sc.next());
            ArrayList<DeviceClasses> priceBuyCompatibili = warehouse.getByPurchasePrice(scelta);
            if (priceBuyCompatibili.isEmpty()) {
                System.out.println("Nessun dispositivo compatibile trovato.");
            } else {
                System.out.println(priceBuyCompatibili);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, inserisci un numero valido per il prezzo!");
            sc.nextLine();
        }
    }

    // Metodo per la ricerca per range di prezzo
    private void searchByPriceRange(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        try {
            System.out.println("Inserisci il prezzo minimo:");
            int scelta1 = Integer.parseInt(sc.next());
            System.out.println("Inserisci il prezzo massimo:");
            int scelta2 = Integer.parseInt(sc.next());

            ArrayList<DeviceClasses> rangeCompatibili = warehouse.getRangeSale(scelta1, scelta2);
            if (rangeCompatibili.isEmpty()) {
                System.out.println("Nessun dispositivo in range trovato");
            } else {
                System.out.println(rangeCompatibili);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un numero!");
            sc.nextLine();
        }
    }

    // Metodo per la ricerca per spesa media dispositivo
    private void searchByAverageDevicePrice(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return;
        }
        System.out.println("Inserisci il tipo di device di cui vuoi sapere il prezzo medio:");
        String scelta = sc.next();
        double averagePrice = warehouse.getAverageDevicePrice(scelta);
        if (!Double.isNaN(averagePrice) && averagePrice != 0) {
            System.out.println("il prezzo medio è: " + averagePrice);
        } else {
            System.out.println("Errore: inserisci un device valido!");
        }
    }
}