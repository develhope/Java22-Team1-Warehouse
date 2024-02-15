import Devices.DeviceClasses;
import Devices.Notebook;
import Devices.Smartphone;
import Devices.Tablet;

import java.util.*;

//
// NON SI COPIA!
// NON SI COPIA!
// NON SI COPIA!
//
public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(fillWarehouse());
        Cart cart = new Cart();
        Utente utente = new Utente();
        Operatore operator = new Operatore();
        Scanner sc = new Scanner(System.in);


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


    public static ArrayList<DeviceClasses> fillWarehouse() {
        Random rand = new Random();
        ArrayList<DeviceClasses> devices = new ArrayList<>(Arrays.asList(
                new Notebook(1500, "Notabook", "Samsung", "Galaxy Book3", "Gaming computer", 15.6, 1000, 899),
                new Smartphone(159, "Smartphone", "Samsung", "A14", "nero", 6.6, 128, 49)));

        for (DeviceClasses device : devices) {
            device.setId(rand.nextLong(999999999));
        }
        return devices;
    }
}