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

        Notebook notebook = new Notebook(1449, "Notebook", "Samsung", "Galaxy Book3", "Gaming computer", 15.6, 1000, 899);
        setIdAddDeviceInWarehouse(warehouse, notebook);
        Smartphone smartphone = new Smartphone(159, "Smartphone", "Samsung", "A14", "nero", 6.6, 128, 49);
        setIdAddDeviceInWarehouse(warehouse, smartphone);
        Tablet tablet = new Tablet(549, "Tablet", "Samsung", "Galaxy Tab S8", "grigio siderale", 11, 128, 349);
        setIdAddDeviceInWarehouse(warehouse, tablet);
        Notebook notebook1 = new Notebook(1449, "Notebook", "Huawei", "Pippo", "molto bello", 25.0, 7000, 5000);
        setIdAddDeviceInWarehouse(warehouse, notebook1);


        System.out.println("Magazzino prima mettere in cart: ");
        warehouse.printAllDevices();

        switchFromCartToWarehouse(warehouse, cart, notebook.getId());
        switchFromCartToWarehouse(warehouse, cart, smartphone.getId());

        System.out.println("carrello aggiornato: ");
        cart.printAllDevices();

        System.out.println("magazzino dopo averli messi in cart: ");
        warehouse.printAllDevices();

        switchFromWarehouseToCart(warehouse, cart, notebook.getId());

        System.out.println("magazzino dopo aver rimosso 1 dal carrello: ");
        warehouse.printAllDevices();

        System.out.println("Carrello dopo aver rimosso 1");
        cart.printAllDevices();

    }

    public static void setIdAddDeviceInWarehouse(Warehouse warehouse, DeviceClasses device) {
        Random rand = new Random();
        device.setId(rand.nextLong(999999999));
        warehouse.addDevice(device);
    }

    public static void switchFromCartToWarehouse(Warehouse warehouse, Cart cart, long id) {
        cart.addDevice(warehouse.getDeviceById(id));
        warehouse.removeDeviceById(id);
    }

    public static void switchFromWarehouseToCart(Warehouse warehouse, Cart cart, long id) {
        warehouse.addDevice(cart.getDeviceById(id));
        cart.removeDeviceById(id);
    }
}