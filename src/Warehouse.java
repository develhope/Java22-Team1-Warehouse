import Devices.DeviceClasses;
import Devices.Notebook;
import Devices.Smartphone;
import Devices.Tablet;

import java.util.ArrayList;
import java.util.Random;

//
// NON SI COPIA!
// NON SI COPIA!
// NON SI COPIA!
//
public class Warehouse {
    private ArrayList<DeviceClasses> devices = new ArrayList<>();

    public Warehouse() {}
    public void addDevice(DeviceClasses device) {
        devices.add(device);
    }

    // ottenere il dispositivo tramite id
    public DeviceClasses getDeviceById(long id) {
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getId() == id) {
                return devices.get(i);

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

    public void printAllDevices() {
        for (int i = 0; i < devices.size(); i++) {
            System.out.print(devices.get(i));
        }
    }

    public ArrayList<DeviceClasses> getCompatibles(String input, String researchType) {
        String inputLowerCase = input.toLowerCase();
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (int i = 0; i < devices.size(); i++) {
            String researchLowerCase = "";

            switch (researchType) {
                case "device":
                    researchLowerCase = devices.get(i).getDevice().toLowerCase();
                    break;
                case "model":
                    researchLowerCase = devices.get(i).getModel().toLowerCase();
                    break;
                case "brand":
                    researchLowerCase = devices.get(i).getBrand().toLowerCase();
            }

            if (researchLowerCase.contains(inputLowerCase)) {
                devicesCompatibili.add(devices.get(i));
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
        if (devicesCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo con questo prezzo trovato.");
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
        if (devices.isEmpty()) {
            return true;
        }
        return false;
    }
    public void fillWarehouse() {
        Notebook notebook = new Notebook(1500, "Notabook", "Samsung", "Galaxy Book3", "Gaming computer", 15.6, 1000, 899);
        setIdAddDeviceInWarehouse(notebook);
        Smartphone smartphone = new Smartphone(159, "Smartphone", "Samsung", "A14", "nero", 6.6, 128, 49);
        setIdAddDeviceInWarehouse(smartphone);
        Tablet tablet = new Tablet(549, "Tablet", "Samsung", "Galaxy Tab S8", "grigio siderale", 11, 128, 349);
        setIdAddDeviceInWarehouse(tablet);
        Notebook notebook1 = new Notebook(1449, "Notebook", "Huawei", "Pippo", "molto bello", 25.0, 7000, 5000);
        setIdAddDeviceInWarehouse(notebook1);
    }

    public void setIdAddDeviceInWarehouse(DeviceClasses device) {
        Random rand = new Random();
        devices.add(device);
        device.setId(rand.nextLong(999999999));
    }


}