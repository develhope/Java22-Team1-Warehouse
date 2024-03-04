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
    public void addDevice(DeviceClasses dispositivo) {
        if (dispositivo == null){
            return;
        }
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