package MenusMethods;

import Devices.DeviceClasses;
import Utils.GetValidInput;
import WarehouseManagement.Cart;
import WarehouseManagement.Warehouse;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class UserMethods {
    private final Warehouse warehouse;
    private final Cart cart;
    private final boolean iva;

    private final Scanner sc;
    private static final GetValidInput getValidInput = new GetValidInput();

    public UserMethods(Warehouse warehouse, Cart cart, boolean iva, Scanner sc) {
        this.warehouse = warehouse;
        this.cart = cart;
        this.iva = iva;
        this.sc = sc;
    }

    //Metodo per aggiungere prodotti dal magazzino al carrello
    private boolean fromWarehouseToCart(long id) {
        if (warehouse.removeDeviceById(id) == null) {
            return false;
        } else {
            cart.addDevice(warehouse.getDeviceById(id));
            warehouse.removeDeviceById(id);
            return true;
        }

    }

    //Metodo per aggiungere prodotti dal carrello al magazzino
    private void fromCartToWarehouse(long id) {
        warehouse.addDevice(cart.getDeviceById(id));
        cart.removeDeviceById(id);
    }

    //Metodo che finalizza l' acquisto
    private String finalizeSale() {
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
        } else {
            while (true) {
                int sceltaFinale = getValidInput.getInteger("1) Per procedere all'acquisto.\n" +
                        "2) Per tornare al menu principale.");
                switch (sceltaFinale) {
                    case 1:
                        System.out.println(finalizeSale());
                        System.out.println("Grazie per l'acquisto, speriamo di rivederti presto.");
                        return;
                    case 2:
                        return;
                    default:
                        System.out.println("Scelta non consentita.");
                        break;
                }
            }
        }
    }

    public double calcAndPrintTotal() {
        if (cart.isEmpty()) {
            return 0.0;
        }
        if (!iva) {
            return cart.getFinalPrice();
        } else {
            return cart.getFinalPrice() * 1.22;
        }
    }

    //Metodo che gestisce se l' utente e' un privato o possiede una partita iva
    public static boolean getIvaUser() {
        while (true) {
            int sceltaUser = getValidInput.getInteger("Seleziona il tipo di utente:\n" +
                    "1) Utente SENZA partita IVA.\n" +
                    "2) Utente CON partita IVA.");
            switch (sceltaUser) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("Opzione non valida, riprova.");
            }
        }
    }


    //Metodo per aggiungere i prodotti al carrello tramite un ID
    public void addToCartById() {
        try {
            long sceltaId = getValidInput.getLong("Digita un id per aggiungere al carrello:");
            if (!warehouse.containsDeviceById(sceltaId)) {
                System.out.println("Non è stato trovato alcun dispositivo con questo ID");
            } else {
                fromWarehouseToCart(sceltaId);
                printDevices(cart.getDevices());
            }
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
        try {
            long sceltaId2 = getValidInput.getLong("Digita un id per rimuovere al carrello:");
            if (!cart.containsDeviceById(sceltaId2)) {
                System.out.println("Non è stato trovato alcun dispositivo con questo ID.");
            } else {
                fromCartToWarehouse(sceltaId2);
                printDevices(cart.getDevices());
            }
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un formato ID corretto.");
            sc.nextLine();
        }
    }

    public void printDevices(List<DeviceClasses> devices) {
        if (devices.isEmpty()) {
            System.out.println("Il carrello e' vuoto.");
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println("Questo è il carrello: ");
            for (DeviceClasses device : devices) {
                double price = iva ? device.getPriceWithIVA() : device.getSale();
                System.out.println("Id: " + device.getId() +
                        ", Dispositivo: " + device.getDevice() +
                        ", Brand: " + device.getBrand() +
                        ", Modello: " + device.getModel() +
                        ", Descrizione: " + device.getDescription() +
                        ", Display: " + df.format(device.getDisplay()) +
                        ", Archiviazione: " + df.format(device.getStorage()) +
                        ", Prezzo di vendità: " + df.format(price) + "€");
            }
        }
    }
}