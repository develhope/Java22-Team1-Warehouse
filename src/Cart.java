import java.util.ArrayList;

import Devices.DeviceClasses;

//IL CODICE CREATO NON VA RICOPIATO

// Classe per gestire il carrello
public class Cart {

    // Lista dei dispositivi nel carrello
    private ArrayList<DeviceClasses> devices = new ArrayList<>();

    // Costruttore della classe Cart
    public Cart() {
        this.devices = new ArrayList<>();
    }

    // Metodo per aggiungere un dispositivo al carrello
    public void addDevice(DeviceClasses dispositivo) {
        devices.add(dispositivo);
    }

    // Metodo per rimuovere un dispositivo dal carrello utilizzando l'ID
    public void removeDeviceById(long id) {
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getId() == id) {
                devices.remove(i);
                break;
            }
        }
    }

    // Metodo per ottenere un dispositivo dal carrello utilizzando l'ID
    public DeviceClasses getDeviceById(long id) {
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getId() == id) {
                return devices.get(i);
            }
        }
        return null;
    }

    // Metodo per verificare se un dispositivo è presente nel carrello utilizzando l'ID
    public boolean containsDeviceById(long id) {
        for (DeviceClasses device : devices) {
            if (device.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // Metodo per verificare se il carrello è vuoto

    public boolean isEmpty() {
        if (devices.isEmpty()) {
            return true;
        }
        return false;
    }

    // Metodo per stampare tutti i dispositivi presenti nel carrello
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

    // Metodo per calcolare il prezzo finale dei dispositivi nel carrello
    public double getFinalPrice() {
        double prezzoFinale = 0;
        for (DeviceClasses dispositivo : devices) {
            prezzoFinale += dispositivo.getSale();
        }
        return prezzoFinale;
    }

    // Metodo per svuotare il carrello
    public void emptyList() {
        devices.clear();
    }
}