import Devices.Smartphone;
import Devices.Notebook;
import Devices.Tablet;
import java.util.ArrayList;
import java.util.Scanner;
import Devices.DeviceClasses;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        Scanner sc = new Scanner(System.in);

        Notebook notebook = new Notebook(1449, "Notebook", "Samsung", "Galaxy Book3", "Gaming computer", 15.6, 1000, 899);

        Smartphone smartphone = new Smartphone(159, "Smartphone", "Samsung", "A14", "nero", 6.6, 128, 49);

        Tablet tablet = new Tablet(549, "Tablet", "Samsung", "Galaxy Tab S8", "grigio siderale", 11, 128, 349);

        Notebook notebook1 = new Notebook(1449, "Notebook", "Samsung", "Galaxy Book3", "Gaming computer", 15.6, 1000, 5000);

        warehouse.addDevice(notebook1);
        warehouse.addDevice(notebook);
        warehouse.addDevice(tablet);
        warehouse.addDevice(smartphone);

    }

}
