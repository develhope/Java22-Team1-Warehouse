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
        Random rand = new Random();

        Notebook notebook = new Notebook(1449, "Notebook", "Samsung", "Galaxy Book3", "Gaming computer", 15.6, 1000, 899);
        notebook.setId(rand.nextLong(1000000000));
        Smartphone smartphone = new Smartphone(159, "Smartphone", "Samsung", "A14", "nero", 6.6, 128, 49);
        smartphone.setId(rand.nextLong(1000000000));
        Tablet tablet = new Tablet(549, "Tablet", "Samsung", "Galaxy Tab S8", "grigio siderale", 11, 128, 349);
        tablet.setId(rand.nextInt(1000000000));
        Notebook notebook1 = new Notebook(1449, "Notebook", "Huawei", "Pippo", "molto bello", 25.0, 7000, 5000);
        notebook1.setId(rand.nextLong(1000000000));


        warehouse.addDevice(notebook1);
        warehouse.addDevice(notebook);
        warehouse.addDevice(tablet);
        warehouse.addDevice(smartphone);


        System.out.println("magazzino prima: ");
        warehouse.printAllDevices();

        addToCartRemoveFromWarehouse(warehouse, cart, notebook.getId());

        System.out.println("carrello aggiornato: ");
        cart.printAllDevices();

        System.out.println("magazzino dopo: ");
        warehouse.printAllDevices();

        addToWarehouseRemoveFromCart(warehouse, cart, notebook.getId());
        System.out.println("magazzino dopo carrello aggiornato: ");
        warehouse.printAllDevices();
        cart.printAllDevices();

    }

    public static void addToCartRemoveFromWarehouse(Warehouse warehouse, Cart cart, long id) {
        DeviceClasses device = warehouse.getDeviceById(id);
        cart.updatedCart(device);
        warehouse.removeDeviceById(id);
    }
    public static void addToWarehouseRemoveFromCart(Warehouse warehouse, Cart cart, long id) {
        DeviceClasses device = cart.getDeviceById(id);
        cart.removeDeviceById(id);
        warehouse.updatedToWarehouse(device);
    }
}