import Devices.DeviceClasses;
import Devices.Smartphone;
import Devices.Notebook;
import Devices.Tablet;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        Cart cart = new Cart();
        Utente utente = new Utente();
        Operatore operator = new Operatore();
        Scanner sc = new Scanner(System.in);

        Notebook notebook = new Notebook(1500, "Notabook", "Samsung", "Galaxy Book3", "Gaming computer", 15.6, 1000, 899);
        setIdAddDeviceInWarehouse(warehouse, notebook);
        Smartphone smartphone = new Smartphone(159, "Smartphone", "Samsung", "A14", "nero", 6.6, 128, 49);
        setIdAddDeviceInWarehouse(warehouse, smartphone);
        Tablet tablet = new Tablet(549, "Tablet", "Samsung", "Galaxy Tab S8", "grigio siderale", 11, 128, 349);
        setIdAddDeviceInWarehouse(warehouse, tablet);
        Notebook notebook1 = new Notebook(1449, "Notebook", "Huawei", "Pippo", "molto bello", 25.0, 7000, 5000);
        setIdAddDeviceInWarehouse(warehouse, notebook1);


        System.out.println("1) Digitare 1 per profilo utente:");
        System.out.println("2) Digitare 2 per profilo operatore:");
        String scelta = sc.next();

        while (!Objects.equals(scelta, "1") && !Objects.equals(scelta, "2")) {

            if (!scelta.equals("1") && !scelta.equals("2")) {
                System.out.println("Scelta non disponiblie");
                System.out.println("1) Digitare 1 per profilo utente:");
                System.out.println("2) Digitare 2 per profilo operatore:");
            }
            scelta = sc.next();
        }
        switch (scelta) {
            case "1":
                utente.userMenu(cart, warehouse);
                break;
            case "2":
                operator.operatorMenu(warehouse);
                break;
        }
    }
    public static void setIdAddDeviceInWarehouse(Warehouse warehouse, DeviceClasses device) {
        Random rand = new Random();
        warehouse.addDevice(device);
        device.setId(rand.nextLong(999999999));
    }
}