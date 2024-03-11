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
    private boolean fromWarehouseToCart(Long id) {
        if (warehouse.containsDeviceById(id) && id != null) {
            cart.addDevice(warehouse.getDeviceById(id));
            warehouse.removeDeviceById(id);
            return true;
        } else {
            return false;
        }
    }

    //Metodo per aggiungere prodotti dal carrello al magazzino
    private boolean fromCartToWarehouse(Long id) {
        if (cart.containsDeviceById(id) && id != null) {
            warehouse.addDevice(cart.getDeviceById(id));
            cart.removeDeviceById(id);
            return true;
        } else {
            return false;
        }
    }

    //Metodo che finalizza l' acquisto
    private double finalizeSale() {
        double finalPrice;
        if (iva) {
            finalPrice = cart.getFinalPrice() * 1.22;
        } else {
            finalPrice = cart.getFinalPrice();
        }
        cart.emptyList();
        return finalPrice;
    }

    public String finalizeSaleMenu() {
        while (true) {
            int sceltaFinale = getValidInput.getInteger("1) Per procedere all'acquisto.\n" +
                    "2) Per tornare al menu principale.");
            switch (sceltaFinale) {
                case 1:
                    return "Questo è il tuo prezzo finale:\"" + finalizeSale() + "\n Grazie per l'acquisto, speriamo di rivederti presto.";
                case 2:
                    return "";
                default:
                    System.out.println("Scelta non consentita.");
            }
        }
    }

    public double calcAndPrintTotal() {
        if (cart.isEmpty()) {
            return 0.0;
        } else {
            if (!iva) {
                return cart.getFinalPrice();
            } else {
                return cart.getFinalPrice() * 1.22;
            }
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
    public List<DeviceClasses> addToCartById() {

        long sceltaId = getValidInput.getLong("Digita un id per aggiungere al carrello:");
        if (!fromWarehouseToCart(sceltaId)) {
            System.out.println("Non è stato trovato alcun dispositivo con questo ID");
            return null;
        } else {
            System.out.println("Questo è il carrello: ");
            return cart.getDevices();
        }

    }

    //Metodo per rimuovere i prodotti dal carrello tramite ID
    public List<DeviceClasses> removeFromCartById() {
        if (cart.isEmpty()) {
            System.out.println("Il carrello è vuoto.");
            return null;
        }

        long sceltaId2 = getValidInput.getLong("Digita un id per rimuovere al carrello:");
        if (!fromCartToWarehouse(sceltaId2)) {
            System.out.println("Non è stato trovato alcun dispositivo con questo ID.");
            return null;
        } else {
            List<DeviceClasses> updatedCartDevices = cart.getDevices();
            if (updatedCartDevices.isEmpty()) {
                System.out.println("Il carrello è vuoto.");
                return null;
            } else {
                return updatedCartDevices;
            }
        }
    }

    public void printDevices(List<DeviceClasses> devices) {
        if (devices.isEmpty() || devices == null) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
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
                        ", Prezzo di vendità: " + df.format(price) + "€");
            }
        }
    }
}