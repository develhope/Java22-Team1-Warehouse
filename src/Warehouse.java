import Devices.DeviceClasses;
import Devices.Notebook;
import Devices.Smartphone;
import Devices.Tablet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Warehouse {
    private ArrayList<DeviceClasses> devices = new ArrayList<>();

    public Warehouse() {
        fillWarehouse();
    }

    public void addDevice(DeviceClasses device) {
        devices.add(device);
    }

    // ottenere il dispositivo tramite id
    public DeviceClasses getDeviceById(long id) {
        for (DeviceClasses device : devices) {
            if (device.getId() == id) {
                return device;

            }
        }
        return null;
    }

    // rimuovere il dispositivo tramite id
    public void removeDeviceById(long id) {
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getId() == id) {
                devices.remove(i);
                break;
            }
        }
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

    public void printAllDevices(boolean iva, boolean includePurchasePrice) {
        DecimalFormat df = new DecimalFormat("#.##");

        for (DeviceClasses device : devices) {
            double priceWithIVA = !iva ? device.getSale() : device.getPriceWithIVA();
            String purchasePrice = includePurchasePrice ? ", Prezzo di acquisto: " + df.format(device.getPurchase()) : "";

            System.out.println("Id: " + device.getId() +
                    ", Dispositivo: " + device.getDevice() +
                    ", Brand: " + device.getBrand() +
                    ", Modello: " + device.getModel() +
                    ", Descrizione: " + device.getDescription() +
                    ", Display: " + df.format(device.getDisplay()) +
                    ", Archiviazione: " + df.format(device.getStorage()) +
                    ", Prezzo di vendità: " + df.format(priceWithIVA) + purchasePrice);
        }
    }

    public ArrayList<DeviceClasses> getCompatibles(String input, String researchType) {
        String inputLowerCase = input.toLowerCase();
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

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

    public ArrayList<DeviceClasses> getRangePurchase(int valLow, int valHigh) {
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (DeviceClasses device : devices) {
            if (device.getPurchase() <= valHigh && device.getPurchase() >= valLow) {
                devicesCompatibili.add(device);
            }
        }
        return devicesCompatibili;
    }

    public ArrayList<DeviceClasses> getRangeSale(int valLow, int valHigh) {
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (DeviceClasses device : devices) {
            if (device.getSale() <= valHigh && device.getSale() >= valLow) {
                devicesCompatibili.add(device);
            }
        }
        return devicesCompatibili;
    }

    public ArrayList<DeviceClasses> getBySellPrice(int range) {
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (DeviceClasses device : devices) {
            if (device.getSale() == range) {
                devicesCompatibili.add(device);
            }
        }
        return devicesCompatibili;
    }

    public ArrayList<DeviceClasses> getByPurchasePrice(int range) {
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (DeviceClasses device : devices) {
            if (device.getPurchase() == range) {
                devicesCompatibili.add(device);
            }
        }
        return devicesCompatibili;
    }

    public double getAverageDevicePrice(String device) {
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
        setIdAddDeviceInWarehouse(notebook);
        Smartphone smartphone = new Smartphone(979, "Smartphone", "Apple", "iPhone 15", "Nero", 6.1, 128, 349);
        setIdAddDeviceInWarehouse(smartphone);
        Tablet tablet = new Tablet(1149, "Tablet", "Apple", "iPad Pro", "Grigio Siderale", 11, 256, 549);
        setIdAddDeviceInWarehouse(tablet);
        Notebook notebook1 = new Notebook(629, "Notebook", "HP", "15S-FQ5073NL", "Argento", 15.6, 512, 249);
        setIdAddDeviceInWarehouse(notebook1);
    }

    public void setIdAddDeviceInWarehouse(DeviceClasses device) {
        Random rand = new Random();
        devices.add(device);
        device.setId(rand.nextLong(999999999));
    }
}