package WarehouseManagement;

import Devices.DeviceClasses;
import Devices.Notebook;
import Devices.Smartphone;
import Devices.Tablet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warehouse {
    private final List<DeviceClasses> devices = new ArrayList<>();

    public Warehouse() {
    }
    public boolean addDevice(DeviceClasses device) {
       return devices.add(device);
    }

    public List<DeviceClasses> getDevices() {
        return devices;
    }

    // ottenere il dispositivo tramite id
    public DeviceClasses getDeviceById(Long id) {
        for (DeviceClasses device : devices) {
            if (id != null && device.getId().equals(id)) {
                return device;
            }
        }
        return null;
    }

    // rimuovere il dispositivo tramite id
    public boolean removeDeviceById(Long id) {
        for (DeviceClasses device : devices) {
            if (id != null && device.getId().equals(id)) {
                devices.remove(device);
                return true;
            }
        }
        return false;
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

    public List<DeviceClasses> getCompatibles(String input, String researchType) {
        if (input == null) {
            return null;
        }
        String inputLowerCase = input.toLowerCase();
        List<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (DeviceClasses device : devices) {
            String researchLowerCase = "";

            switch (researchType) {
                case "device":
                    researchLowerCase = device.getDevice().toLowerCase();
                    break;
                case "model":
                    researchLowerCase = device.getModel().toLowerCase();
                    break;
                case "brand":
                    researchLowerCase = device.getBrand().toLowerCase();
            }

            if (researchLowerCase.contains(inputLowerCase)) {
                devicesCompatibili.add(device);
            }
        }
        return devicesCompatibili;
    }

    public List<DeviceClasses> getRangePurchase(int valLow, int valHigh) {
        List<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (DeviceClasses device : devices) {
            if (device.getPurchase() <= valHigh && device.getPurchase() >= valLow) {
                devicesCompatibili.add(device);
            }
        }
        return devicesCompatibili;
    }

    public List<DeviceClasses> getRangeSale(Integer valLow, Integer valHigh) {
        if (valLow == null || valHigh == null) {
            return null;
        }
        List<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (DeviceClasses device : devices) {
            if (device.getSale() <= valHigh && device.getSale() >= valLow) {
                devicesCompatibili.add(device);
            }
        }
        return devicesCompatibili;
    }

    public List<DeviceClasses> getBySellPrice(Integer range) {
        if (range == null) {
            return null;
        }
        List<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (DeviceClasses device : devices) {
            if (device.getSale() == range) {
                devicesCompatibili.add(device);
            }
        }
        return devicesCompatibili;
    }

    public List<DeviceClasses> getByPurchasePrice(Integer range) {
        if (range == null) {
            return null;
        }
        List<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (DeviceClasses device : devices) {
            if (device.getPurchase() == range) {
                devicesCompatibili.add(device);
            }
        }
        return devicesCompatibili;
    }

    public Double getAverageDevicePrice(String device) {
        if (device == null) {
            return null;
        }
        for (DeviceClasses newDevice : devices) {
            if (newDevice.getDevice().toLowerCase().equals(device.toLowerCase())) {
                break;
            } else {
                return 0.0;
            }
        }
        double sum = 0;
        int counter = 0;
        String deviceLowerCase = device.trim().toLowerCase();

        for (DeviceClasses deviceClasses : devices) {
            if (deviceClasses.getDevice().toLowerCase().equals(deviceLowerCase)) {
                sum += deviceClasses.getPurchase();
                counter++;
            }
        }
        return sum / counter;
    }

    public boolean isEmpty() {
        return devices.isEmpty();
    }

    public void fillWarehouse() {
        Notebook notebook = new Notebook(1500, "Notebook", "Samsung", "Galaxy Book 3", "Gaming computer", 15.6, 1000, 799);
        addDevice(setId(notebook));
        Smartphone smartphone = new Smartphone(979, "Smartphone", "Apple", "iPhone 15", "Nero", 6.1, 128, 349);
        addDevice(setId(smartphone));
        Tablet tablet = new Tablet(1149, "Tablet", "Apple", "iPad Pro", "Grigio Siderale", 11, 256, 549);
        addDevice(setId(tablet));
        Notebook notebook1 = new Notebook(629, "Notebook", "HP", "15S-FQ5073NL", "Argento", 15.6, 512, 249);
        addDevice(setId(notebook1));
    }
    public boolean setIdAddDeviceInWarehouse(DeviceClasses device) {
        Random rand = new Random();
        device.setId(rand.nextLong(999999999));
        return devices.add(device);
    }

}