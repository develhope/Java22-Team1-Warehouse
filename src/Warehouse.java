import Devices.DeviceClasses;
import Devices.Notebook;
import Devices.Smartphone;
import Devices.Tablet;

import java.util.ArrayList;
import java.util.Random;

//IL CODICE CREATO NON VA RICOPIATO

public class Warehouse {
    // ArrayList contenente tutti i dispositivi nel magazzino
    private ArrayList<DeviceClasses> devices = new ArrayList<>();

    // Costruttore vuoto della classe Warehouse
    public Warehouse() {
    }

    // Aggiunge un dispositivo al magazzino
    public void addDevice(DeviceClasses device) {
        devices.add(device);
    }

    // Ottiene un dispositivo tramite il suo ID
    public DeviceClasses getDeviceById(long id) {
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getId() == id) {
                return devices.get(i);
            }
        }
        return null;
    }

    // Rimuove un dispositivo dal magazzino tramite il suo ID
    public void removeDeviceById(long id) {
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getId() == id) {
                devices.remove(i);
                break;
            }
        }
    }

    // Verifica se un dispositivo con l'ID specificato esiste nel magazzino
    public boolean containsDeviceById(long id) {
        for (DeviceClasses device : devices) {
            if (device.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // Stampa tutti i dispositivi presenti nel magazzino
    public void printAllDevices() {
        for (int i = 0; i < devices.size(); i++) {
            System.out.print(devices.get(i));
        }
    }

    // Ottiene i dispositivi compatibili con la ricerca per tipo, modello o brand
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

    // Ottiene i dispositivi nel range di prezzo di acquisto
    public ArrayList<DeviceClasses> getRangePurchase(int valLow, int valHigh) {
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getPurchase() <= valHigh && devices.get(i).getPurchase() >= valLow) {
                devicesCompatibili.add(devices.get(i));
            }
        }
        return devicesCompatibili;
    }

    // Ottiene i dispositivi nel range di prezzo di vendita
    public ArrayList<DeviceClasses> getRangeSale(int valLow, int valHigh) {
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getSale() <= valHigh && devices.get(i).getSale() >= valLow) {
                devicesCompatibili.add(devices.get(i));
            }
        }
        return devicesCompatibili;
    }

    // Ottiene i dispositivi con un prezzo di vendita specifico
    public ArrayList<DeviceClasses> getBySellPrice(int range) {
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getSale() == range) {
                devicesCompatibili.add(devices.get(i));
            }
        }
        return devicesCompatibili;
    }

    // Ottiene i dispositivi con un prezzo di acquisto specifico
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

    // Calcola il prezzo medio dei dispositivi di un tipo specifico
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

    // Verifica se il magazzino è vuoto
    public boolean isEmpty() {
        if (devices.isEmpty()) {
            return true;
        }
        return false;
    }

    // Popola il magazzino con alcuni dispositivi predefiniti
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

    // Imposta un ID casuale per un dispositivo e lo aggiunge al magazzino
    public void setIdAddDeviceInWarehouse(DeviceClasses device) {
        Random rand = new Random();
        devices.add(device);
        device.setId(rand.nextLong(999999999));
    }
}