import java.text.DecimalFormat;
import java.util.ArrayList;

import Devices.DeviceClasses;

public class Cart {

    private ArrayList<DeviceClasses> devices = new ArrayList<>();

    public Cart() {
        this.devices = new ArrayList<>();
    }

    // Aggiungere al cart
    public void addDevice(DeviceClasses dispositivo) {
        devices.add(dispositivo);
    }

    // Rimuove dal carrello tramite ID
    public void removeDeviceById(long id) {
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getId() == id) {
                devices.remove(i);
                break;
            }
        }
    }

    //Ottieni prodotto da ID
    public DeviceClasses getDeviceById(long id) {
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getId() == id) {
                return devices.get(i);
            }
        }
        return null;
    }

    // Controlla se l'id esiste
    public boolean containsDeviceById(long id) {
        for (DeviceClasses device : devices) {
            if (device.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // Controlla se cart e' vuoto
    public boolean isEmpty() {
        if (devices.isEmpty()) {
            return true;
        }
        return false;
    }

    // Stampare elementi nel cart
    public void printAllDevices(boolean iva) {
        if (devices.isEmpty()) {
            System.out.println("Il carrelo è vuoto.");
            return;
        }

        DecimalFormat df = new DecimalFormat("#.##");

        for (DeviceClasses device : devices) {
            double priceWithIVA = !iva ? device.getSale() : device.getPriceWithIVA();
            String formattedPrice = df.format(priceWithIVA);


            System.out.println("Id: " + device.getId() +
                    ", Dispositivo: " + device.getDevice() +
                    ", Brand: " + device.getBrand() +
                    ", Modello: " + device.getModel() +
                    ", Descrizione: " + device.getDescription() +
                    ", Display: " + device.getDisplay() +
                    ", Archiviazione: " + device.getStorage() +
                    ", Prezzo: " + formattedPrice);

        }
    }

    // Calcolare prezzo finale
    public double getFinalPrice() {
        double prezzoFinale = 0;
        for (DeviceClasses dispositivo : devices) {
            prezzoFinale += dispositivo.getSale();
        }
        return prezzoFinale;
    }

    // Svuota cart
    public void emptyList() {
        devices.clear();
    }
}