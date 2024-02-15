import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Operatore {
    public void operatorMenu(Warehouse warehouse) {
        Scanner sc = new Scanner(System.in);
        String sceltaUser;
        do {
            System.out.println("Scegli l operazione da effettuare:");
            System.out.println("1) Visualizza tutti prodotti");
            System.out.println("2) Ricerca per tipo dispositivo:");
            System.out.println("3) Ricerca per produttore:");
            System.out.println("4) Ricerca per modello:");
            System.out.println("5) Ricerca per prezzo di vendita:");
            System.out.println("6) Ricerca per range di prezzo:");
            System.out.println("7) Aggiungi al magazzino:");
            System.out.println("8) Rimuovi dal magazzino:");
            System.out.println("0) Fine:");
            sceltaUser = sc.next();
            switch (sceltaUser) {
                case "1":
                    warehouse.printAllDevices();
                    break;
                case "2":
                    System.out.println("Inserisci il nome del tipo di dispositivo:");
                    String sceltaDisp = sc.next();
                    ArrayList<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaDisp, "device");
                    if (devicesCompatibili.isEmpty()) {
                        System.out.println("Nessun dispositivo compatibile trovato.");
                    } else {
                        System.out.println(devicesCompatibili);
                    }
                    break;
                case "3":
                    System.out.println("Inserisci il nome del brand del dispositivo:");
                    String sceltaBrand = sc.next();
                    ArrayList<DeviceClasses> brandCompatibili = warehouse.getCompatibles(sceltaBrand, "brand");
                    if (brandCompatibili.isEmpty()) {
                        System.out.println("Nessun dispositivo compatibile trovato.");
                    } else {
                        System.out.println(brandCompatibili);
                    }
                    break;
                case "4":
                    System.out.println("Inserisci il nome del modello del dispositivo:");
                    String sceltaModel = sc.next();
                    ArrayList<DeviceClasses> modelCompatibili = warehouse.getCompatibles(sceltaModel, "model");
                    if (modelCompatibili.isEmpty()) {
                        System.out.println("Nessun dispositivo compatibile trovato.");
                    } else {
                        System.out.println(modelCompatibili);
                    }
                    break;
                case "5":
                    System.out.println("Inserisci il prezzo:");
                    int sceltaForPrice = sc.nextInt();
                    ArrayList<DeviceClasses> priceCompatibili = warehouse.getBySellPrice(sceltaForPrice);
                    if (priceCompatibili.isEmpty()) {
                        System.out.println("Nessun dispositivo compatibile trovato.");
                    } else {
                        System.out.println(priceCompatibili);
                    }
                    break;
                case "6":
                    System.out.println("Inserisci il prezzo minimo:");
                    int sceltaForPriceRange = sc.nextInt();
                    System.out.println("Inserisci il prezzo massimo:");
                    int sceltaForPriceRange2 = sc.nextInt();

                    ArrayList<DeviceClasses> rangeCompatibili = warehouse.getRangeSale(sceltaForPriceRange, sceltaForPriceRange2);
                    if (rangeCompatibili.isEmpty()) {
                        System.out.println("Nessun dispositivo in range trovato");
                    } else {
                        System.out.println(rangeCompatibili);
                    }
                    break;
                case "7":
                    System.out.println("Digita un id per aggiungere al magazzino:");
                    DeviceClasses newDevice = addNewDevice();
                    setIdAddDeviceInWarehouse(warehouse,newDevice);
                    break;
                case "8":
                    System.out.println("Digita un id per rimuovere al magazzino:");
                    if (sc.hasNextLong()) {
                        long sceltaId2 = sc.nextLong();
                        if (!warehouse.containsDeviceById(sceltaId2)) {
                            System.out.println("Non è stato trovato alcun dispositivo con questo ID");
                            break;
                        }

                    } else {
                        System.out.println("Input non valido. Devi digitare un numero intero per l'ID.");
                        sc.next();
                    }
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Scelta non valida");
            }

        } while (!sceltaUser.equals("0"));
    }
    public static void setIdAddDeviceInWarehouse(Warehouse warehouse, DeviceClasses device) {
        Random rand = new Random();
        warehouse.addDevice(device);
        device.setId(rand.nextLong(999999999));
    }
    public static DeviceClasses addNewDevice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci i dati del dispositivo:");
        System.out.println("Device:");
        String device = sc.next();
        System.out.println("Brand:");
        String brand = sc.next();
        System.out.println("Model:");
        String model = sc.next();
        System.out.println("Description:");
        String description = sc.next();
        System.out.println("Display:");
        double display = sc.nextDouble();
        System.out.println("Storage:");
        int storage = sc.nextInt();
        System.out.println("Purchase:");
        double purchase = sc.nextDouble();
        System.out.println("Sale:");
        double sale = sc.nextDouble();
        DeviceClasses deviceF = new DeviceClasses(sale,device,brand,model,description,display,storage,purchase);
        return deviceF;
    }

}


