package WarehouseManagement;

import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<DeviceClasses> devices;

    public Cart() {
        this.devices = new ArrayList<>();
    }

    // Aggiungere al cart
    public boolean addDevice(DeviceClasses device) {
        for (DeviceClasses dev : devices) {
            if (dev.getId() == device.getId()) {
                return false;
            }
        }
        if (device == null) {
            return false;
        }
        devices.add(device);
        return true;
    }

    // Rimuove dal carrello tramite ID
    public boolean removeDeviceById(Long id) {
        if (id == null) {
            return false;
        }
        for (DeviceClasses device : devices) {
            if (device.getId() == id) {
                 devices.remove(device);
                 return true;

            }
        }
        return false;
    }

    //Ottieni prodotto da ID
    public DeviceClasses getDeviceById(Long id) {
        if(id == null) {
            return null;
        }
        for (DeviceClasses device : devices) {
            if (device.getId() == id) {
                return device;
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
        return devices.isEmpty();
    }

    public List<DeviceClasses> getDevices() {
        return devices;
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