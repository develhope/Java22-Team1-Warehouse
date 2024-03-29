package WarehouseManagement;

import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
        boolean removed = false;
        Iterator<DeviceClasses> iterator = devices.iterator();

        while(iterator.hasNext()) {
            DeviceClasses device = iterator.next();

            if(device.getId().equals(id) && id != null) {
                iterator.remove();
                removed = true;
                break;
            }
        }



        return removed;
    }

    public boolean removeDeviceById2(Long id) {
        DeviceClasses deviceToRemove = devices
                .stream()
                .filter(device-> device.getId().equals(id))
                .collect(Collectors.toList())
                .removeFirst();

        if(deviceToRemove != null) return devices.remove(deviceToRemove); else return false;
    }

    //Ottieni prodotto da ID
    public DeviceClasses getDeviceById(Long id) {
        for (DeviceClasses device : devices) {
            if (id != null && device.getId().equals(id)) {
                return device;
            }
        }

        return null;
    }

    // Controlla se l'id esiste
    public boolean containsDeviceById(Long id) {
        for (DeviceClasses device : devices) {
            if (id != null && device.getId().equals(id)) {
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