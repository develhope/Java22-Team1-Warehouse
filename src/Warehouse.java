import Devices.DeviceClasses;
import Devices.Notebook;
import Devices.Smartphone;
import Devices.Tablet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

//
// NON SI COPIA!
// NON SI COPIA!
// NON SI COPIA!
//
public class Warehouse {
    private ArrayList<DeviceClasses> devices = new ArrayList<>();

    public Warehouse() {
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
            String formattedPrice = df.format(priceWithIVA);
            String purchasePrice = includePurchasePrice ? ", Prezzo di acquisto: " + device.getPurchase() : "";

            System.out.println("Id: " + device.getId() +
                    ", Dispositivo: " + device.getDevice() +
                    ", Brand: " + device.getBrand() +
                    ", Modello: " + device.getModel() +
                    ", Descrizione: " + device.getDescription() +
                    ", Display: " + device.getDisplay() +
                    ", Archiviazione: " + device.getStorage() +
                    ", Prezzo di vendità: " + formattedPrice + purchasePrice);
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

        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getPurchase() <= valHigh && devices.get(i).getPurchase() >= valLow) {
                devicesCompatibili.add(devices.get(i));
            }
        }
        return devicesCompatibili;
    }

    public ArrayList<DeviceClasses> getRangeSale(int valLow, int valHigh) {
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getSale() <= valHigh && devices.get(i).getSale() >= valLow) {
                devicesCompatibili.add(devices.get(i));
            }
        }
        return devicesCompatibili;
    }

    public ArrayList<DeviceClasses> getBySellPrice(int range) {
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getSale() == range) {
                devicesCompatibili.add(devices.get(i));
            }
        }
        return devicesCompatibili;
    }

    public ArrayList<DeviceClasses> getByPurchasePrice(int range) {
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getPurchase() == range) {
                devicesCompatibili.add(devices.get(i));
            }
        }
        return devicesCompatibili;
    }

    public double getAverageDevicePrice(String device) {
        double sum = 0;
        int counter = 0;
        String deviceLowerCase = device.trim().toLowerCase();

        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getDevice().toLowerCase().equals(deviceLowerCase)) {
                sum += devices.get(i).getPurchase();
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