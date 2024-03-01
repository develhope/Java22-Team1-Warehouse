package MenusMethods;

import Devices.DeviceClasses;
import WarehouseManagement.Cart;
import WarehouseManagement.Warehouse;
import static Utils.GetValidInput.sc;

import java.text.DecimalFormat;
import java.util.List;

public class UserMethods {
    private final Warehouse warehouse;
    private final Cart cart;
    private final boolean iva;



    public UserMethods(Warehouse warehouse, Cart cart, boolean iva) {
        this.warehouse = warehouse;
        this.cart = cart;
        this.iva = iva;
    }

    //Metodo per aggiungere prodotti dal magazzino al carrello
    private  void fromWarehouseToCart(long id) {
        cart.addDevice(warehouse.getDeviceById(id));
        warehouse.removeDeviceById(id);
        printDevices(cart.getDevices());
    }

    //Metodo per aggiungere prodotti dal carrello al magazzino
    private  void fromCartToWarehouse(long id) {
        warehouse.addDevice(cart.getDeviceById(id));
        cart.removeDeviceById(id);
        printDevices(cart.getDevices());
    }

    //Metodo che finalizza l' acquisto
    private  String finalizeSale() {
        double finalPrice;
        if (iva) {
            finalPrice = cart.getFinalPrice() * 1.22;
        } else {
            finalPrice = cart.getFinalPrice();
        }
        cart.emptyList();
        return "Questo è il tuo prezzo finale: " + finalPrice;
    }

     public void finalizeSaleMenu() {
        if (cart.isEmpty()) {
            System.out.println("Il carrello è vuoto.");
            return;
        }
        System.out.println("1) Per procedere all'acquisto.");
        System.out.println("2) Per tornare al menu principale.");
        String sceltaFinale = sc.next();

        if (sceltaFinale.equals("1")) {
            System.out.println(finalizeSale());
            System.out.println("Grazie per l'acquisto, speriamo di rivederti presto.");
            sc.nextLine();
        } else if (sceltaFinale.equals("2")) {
            sc.nextLine();
        } else {
            System.out.println("Scelta non consentita.");
            sc.nextLine();
        }
    }

     public void calcAndPrintTotal() {
        if (cart.isEmpty()) {
            System.out.println("Il carrello è vuoto.");
            return;
        }
        System.out.println("Il prezzo finale del carrello è:");
        if (!iva) {
            System.out.println(cart.getFinalPrice());
        } else {
            System.out.println(cart.getFinalPrice() * 1.22);
        }
    }

    //Metodo che gestisce se l' utente e' un privato o possiede una partita iva
    public static boolean getIvaUser() {
        while (true) {
            System.out.println("Seleziona il tipo di utente:");
            System.out.println("1) Utente SENZA partita IVA.");
            System.out.println("2) Utente CON partita IVA.");
            if (sc.hasNextInt()) {
                int sceltaUser = sc.nextInt();
                switch (sceltaUser) {
                    case 1:
                        return true;
                    case 2:
                        return false;
                    default:
                        System.out.println("Opzione non valida, riprova.");
                        break;
                }
            } else {
                System.out.println("Input non valido, riprova.");
                sc.next();
            }
        }
    }

    //Metodo per aggiungere i prodotti al carrello tramite un ID
    public void addToCartById() {
        System.out.println("Digita un id per aggiungere al carrello:");
        try {
            long sceltaId = Long.parseLong(sc.next());
            if (!warehouse.containsDeviceById(sceltaId)) {
                System.out.println("Non è stato trovato alcun dispositivo con questo ID");
                return;
            }
            System.out.println("Questo è il carrello: ");
            fromWarehouseToCart(sceltaId);
            sc.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un formato ID corretto.");
            sc.nextLine();
        }
    }

    //Metodo per rimuovere i prodotti dal carrello tramite ID
    public void removeFromCartById() {
        if (cart.isEmpty()) {
            System.out.println("Il carrello è vuoto.");
            return;
        }
        System.out.println("Digita un id per rimuovere al carrello:");
        try {
            long sceltaId2 = Long.parseLong(sc.next());
            if (!cart.containsDeviceById(sceltaId2)) {
                System.out.println("Non è stato trovato alcun dispositivo con questo ID.");
                return;
            }
            fromCartToWarehouse(sceltaId2);
            sc.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un formato ID corretto.");
            sc.nextLine();
        }
    }

    public  void printDevices(List<DeviceClasses> devices) {
        if(devices.isEmpty()) {
            System.out.println("Il carrello e' vuoto.");
            return;
        }
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
                    ", Prezzo di vendità: " + df.format(price)+ "€");
        }
    }
}