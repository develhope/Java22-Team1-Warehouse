import java.util.ArrayList;

import Devices.DeviceClasses;

public class Warehouse {
    private ArrayList<DeviceClasses> devices = new ArrayList<>();

    public Warehouse() {
        this.devices = new ArrayList<>();
    }

    public void addDevice(DeviceClasses device) {
        devices.add(device);
    }

    public void removeDevice(int index) {
        if (index >= 0 && index < devices.size()) {
            devices.remove(index);
        } else {
            System.out.println("Indice non valido");
        }
    }

    public void printAllDevices() {
        for (int i = 0; i < devices.size(); i++) {
            System.out.println(i + " : " + devices.get(i));
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

            if (inputLowerCase.equals(researchLowerCase)) {
                devicesCompatibili.add(devices.get(i));
            }
        }
        return devicesCompatibili;
    }


    public DeviceClasses getDeviceByIndex(int i) {
        if (i >= 0 && i < devices.size()) {
            return devices.get(i);
        } else {
            System.out.println("Indice non valido");
            return null;
        }
    }

    public ArrayList<DeviceClasses> getRangeBuy(int valLow, int valHigh) {
        ArrayList<DeviceClasses> devicesCompatibili = new ArrayList<>();

        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getPurchase() <= valHigh && devices.get(i).getPurchase() >= valLow) {
                devicesCompatibili.add(devices.get(i));
            }
        }
        return devicesCompatibili;
    }

    public double getAverageDevicePrice(String device) {
        double average = 0;
        int counter = 0;
        String deviceLowerCase = device.trim().toLowerCase();

        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getDevice().toLowerCase().equals(deviceLowerCase)) {
                average += devices.get(i).getPurchase();
                counter++;
            }
        }
        return average / counter;
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
    //Aggiorna il magazzino
    public ArrayList<DeviceClasses> updatedToWarehouse(DeviceClasses classe) {
        addDevice(classe);
        return devices;
    }

}