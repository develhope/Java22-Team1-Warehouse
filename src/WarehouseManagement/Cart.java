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
            if (device == null || devices.contains(device)) {
                return false;
            }

        devices.add(device);
        return true;
    }

    // Rimuove dal carrello tramite ID
     public boolean removeDeviceById(Long id) {
        DeviceClasses deviceToRemove = devices
                .stream()
                .filter(device-> device.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(deviceToRemove != null) return devices.remove(deviceToRemove); else return false;
    }

    //Ottieni prodotto da ID
    public DeviceClasses getDeviceById(Long id) {
        return devices.stream()
                .filter(device -> id != null && device.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Controlla se l'id esiste
    public boolean containsDeviceById(Long id) {
        return devices.stream()
                .anyMatch(device -> id != null && device.getId().equals(id));
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
       return devices.stream()
               .mapToDouble(DeviceClasses::getSale)
               .sum();

//        double prezzoFinale = 0;
//        for (DeviceClasses dispositivo : devices) {
//            prezzoFinale += dispositivo.getSale();
//        }
//        return prezzoFinale;
    }

    // Svuota cart
    public void emptyList() {
        devices.clear();
    }
}