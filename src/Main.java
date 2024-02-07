import Devices.DeviceClasses;
import Devices.Smartphone;
import Devices.Notebook;
import Devices.Tablet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        Scanner sc = new Scanner(System.in);


        Notebook notebook = new Notebook(1500, "Notabook", "Samsung", "Galaxy Book3", "Gaming computer", 15.6, 1000, 899);
        setIdAddDeviceInWarehouse(warehouse, notebook);
        Smartphone smartphone = new Smartphone(159, "Smartphone", "Samsung", "A14", "nero", 6.6, 128, 49);
        setIdAddDeviceInWarehouse(warehouse, smartphone);
        Tablet tablet = new Tablet(549, "Tablet", "Samsung", "Galaxy Tab S8", "grigio siderale", 11, 128, 349);
        setIdAddDeviceInWarehouse(warehouse, tablet);
        Notebook notebook1 = new Notebook(1449, "Notebook", "Huawei", "Pippo", "molto bello", 25.0, 7000, 5000);
        setIdAddDeviceInWarehouse(warehouse, notebook1);

        boolean operator = false;
        boolean user = false;
        System.out.println(notebook.getId());
        System.out.println("1) Digitare 1 per profilo utente:");
        System.out.println("2) Digitare 2 per profilo operatore:");
        int scelta = sc.nextInt();

        while(scelta!=1 && scelta!=2) {

            if (scelta != 1 && scelta != 2) {
                System.out.println("Scelta non disponiblie");
                System.out.println("1) Digitare 1 per profilo utente:");
                System.out.println("2) Digitare 2 per profilo operatore:");
            }
            scelta = sc.nextInt();
        }
        switch (scelta) {
            case 1:
                userMenu(cart, warehouse);
                break;
            case 2:

                break;
        }
    }
    public static void userMenu(Cart cart, Warehouse warehouse) throws Exception {
        Scanner sc = new Scanner(System.in);
        int sceltaUser;
        do {
            System.out.println("Scegli l operazione da effettuare:");
            System.out.println("1) Visualizza tutti prodotti");
            System.out.println("2) Ricerca per tipo dispositivo:");
            System.out.println("3) Ricerca per produttore:");
            System.out.println("4) Ricerca per modello:");
            System.out.println("5) Ricerca per prezzo di vendita:");
            System.out.println("6) Ricerca per range di prezzo:");
            System.out.println("7) Aggiungi al carrello:");
            System.out.println("8) Rimuovi dal carrello:");
            System.out.println("9) Calcolare il totale:");
            System.out.println("10) Visualizza carrello:");
            System.out.println("11) Acquista:");
            System.out.println("0) Fine:");
            sceltaUser = sc.nextInt();
            switch (sceltaUser) {
                case 1:
                    warehouse.printAllDevices();
                    break;
                case 2:
                    System.out.println("Inserisci il nome del tipo di dispositivo:");
                    String sceltaDisp = sc.next();
                    System.out.println(warehouse.getCompatibles(sceltaDisp, "device"));

                    break;
                case 3:
                    System.out.println("Inserisci il nome del brand del dispositivo:");
                    String sceltaBrand = sc.next();
                    System.out.println(warehouse.getCompatibles(sceltaBrand, "brand"));
                    break;
                case 4:
                    System.out.println("Inserisci il nome del modello del dispositivo:");
                    String sceltaModel = sc.next();
                    System.out.println(warehouse.getCompatibles(sceltaModel, "model"));
                    break;
                case 5:
                    System.out.println("Inserisci il prezzo:");
                    int sceltaForPrice = sc.nextInt();
                    System.out.println(warehouse.getBySellPrice(sceltaForPrice));
                    break;
                case 6:
                    System.out.println("Inserisci il prezzo minimo:");
                    int sceltaForPriceRange = sc.nextInt();
                    System.out.println("Inserisci il prezzo massimo:");
                    int sceltaForPriceRange2 = sc.nextInt();

                    warehouse.getRangeSale(sceltaForPriceRange, sceltaForPriceRange2);
                    break;
                case 7:
                    System.out.println("Digita un id per aggiungere al carrello:");
                    long sceltaId = sc.nextLong();
                    fromWarehouseToCart(warehouse, cart, sceltaId);
                    break;
                case 8:
                    System.out.println("Digita un id per rimuovere dal carrello :");
                    long sceltaId2 = sc.nextLong();
                    fromCartToWarehouse(warehouse, cart, sceltaId2);
                    break;
                case 9:
                    System.out.println("Il prezzo finale del carrello è:");
                    System.out.println(cart.getFinalPrice());
                    break;
                case 10:
                    System.out.println("Questo è il carrello: ");
                    cart.printAllDevices();
                    break;
                case 11:
                    System.out.println("1) Sei un privato ");
                    System.out.println("2) Hai una partita iva");
                    boolean partitaIva = false;
                    int sceltaUtente = sc.nextInt();
                    if (sceltaUtente == 1) {
                        partitaIva = true;
                    }
                    finalizzaVendita(cart, partitaIva);
                    break;
                case 0:
                    break;

            }

        } while (sceltaUser != 0);
    }

    public static void setIdAddDeviceInWarehouse(Warehouse warehouse, DeviceClasses device) {
        Random rand = new Random();
        warehouse.addDevice(device);
        device.setId(rand.nextLong(999999999));
    }

    public static void fromWarehouseToCart(Warehouse warehouse, Cart cart, long id) {
        cart.addDevice(warehouse.getDeviceById(id));
        warehouse.removeDeviceById(id);
        cart.printAllDevices();
    }

    public static void fromCartToWarehouse(Warehouse warehouse, Cart cart, long id) {
        warehouse.addDevice(cart.getDeviceById(id));
        cart.removeDeviceById(id);
        cart.printAllDevices();
    }

    public static String finalizzaVendita(Cart cart, boolean iva) {
        double finalPrice;
        if (iva) {
            finalPrice = cart.getFinalPrice() * 1.22;
        } else {
            finalPrice = cart.getFinalPrice();
        }
        cart.emptyList();
        return "Questo è il tuo prezzo finale: " + finalPrice;
    }
}