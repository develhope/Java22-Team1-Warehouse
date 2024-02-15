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
        if(devices.isEmpty()) {
            return true;
        }
            return false;
    }
    // Stampare elementi nel cart
    public void printAllDevices() {
        if (devices.isEmpty()) {
            System.out.println("Il carrello è vuoto!");
        } else {
            System.out.println("Questo è il carrello:");
            for (int i = 0; i < devices.size(); i++) {
                System.out.print(devices.get(i));
            }
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