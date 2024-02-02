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
            prezzoFinale += dispositivo.getSale();
        }
        return prezzoFinale;
    }

    // Svuota cart
    public void emptyList() {
        devices.clear();
    }


}