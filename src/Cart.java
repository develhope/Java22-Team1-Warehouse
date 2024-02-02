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

    // Rimuovere dispositivo per indice
    public void removeDevice(int index) {
        if (index >= 0 && index < devices.size()) {
            devices.remove(index);
        } else {
            System.out.println("Indice non valido");
        }
    }

    // Stampare elementi nel cart
    public void printAllDevices() {
        for (int i = 0; i < devices.size(); i++) {
            System.out.println(i + " : " + devices.get(i));
        }
    }

    // Calcolare prezzo finale
    public double getFinalPrice() {
        double prezzoFinale = 0;
        for (DeviceClasses dispositivo : devices) {
            prezzoFinale += dispositivo.getPurchase();
        }
        return prezzoFinale;
    }

    // Selezionare specifico elemento per indice
    public DeviceClasses getIndexDevice(int indice) {
        if (indice >= 0 && indice < devices.size()) {
            return devices.get(indice);
        } else {
            System.out.println("Indice non valido");
            return null;
        }
    }

    // Svuota cart
    public void emptyList() {
        devices.clear();
    }

    // aggiornare e aggiungere al carrello
    public ArrayList<DeviceClasses> updatedCart(DeviceClasses classe) {
        addDevice(classe);
        return devices;
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
}