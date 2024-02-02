import Devices.DeviceClasses;
import Devices.Smartphone;
import Devices.Notebook;
import Devices.Tablet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        Scanner sc = new Scanner(System.in);

        Notebook notebook = new Notebook(1500, "Notebook", "Samsung", "Galaxy Book3", "Gaming computer", 15.6, 1000, 899);
        setIdAddDeviceInWarehouse(warehouse, notebook);
        Smartphone smartphone = new Smartphone(159, "Smartphone", "Samsung", "A14", "nero", 6.6, 128, 49);
        setIdAddDeviceInWarehouse(warehouse, smartphone);
        Tablet tablet = new Tablet(549, "Tablet", "Samsung", "Galaxy Tab S8", "grigio siderale", 11, 128, 349);
        setIdAddDeviceInWarehouse(warehouse, tablet);
        Notebook notebook1 = new Notebook(1449, "Notebook", "Huawei", "Pippo", "molto bello", 25.0, 7000, 5000);
        setIdAddDeviceInWarehouse(warehouse, notebook1);


            System.out.println("magazzino");
            warehouse.printAllDevices();
            System.out.println("primo aggiornamento carrello");
            fromWarehouseToCart(warehouse, cart, notebook.getId());

            System.out.println("Magazzino");
            warehouse.printAllDevices();

            System.out.println("Secondo aggiornamento carrello");

            fromWarehouseToCart(warehouse, cart, smartphone.getId());

            System.out.println("togliamo smartphone da carrello");
            fromCartToWarehouse(warehouse, cart, smartphone.getId());






    }

    public static void setIdAddDeviceInWarehouse(Warehouse warehouse, DeviceClasses device) {
        Random rand = new Random();
        device.setId(rand.nextLong(999999999));
        warehouse.addDevice(device);
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

    public static String finalizzaVendita(Cart cart , boolean iva) {
        double finalPrice;
        if(iva) {
            finalPrice = cart.getFinalPrice() * 1.22;
        } else {
            finalPrice = cart.getFinalPrice();
        }
        cart.emptyList();
        return "Questo è il tuo prezzo finale: " + finalPrice;
    }
}