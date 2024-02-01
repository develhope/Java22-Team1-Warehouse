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
            System.out.println("Index not valid");
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
            String researchLoweCase = "";

            switch (researchType) {
                case "device":
                    researchLoweCase = devices.get(i).getDevice().toLowerCase();
                    break;
                case "modello":
                    researchLoweCase = devices.get(i).getModel().toLowerCase();
                    break;
                case "brand":
                    researchLoweCase = devices.get(i).getBrand().toLowerCase();
            }

            if (inputLowerCase.equals(researchLoweCase)) {
                devicesCompatibili.add(devices.get(i));
            }
        }
        return devicesCompatibili;
    }


    public DeviceClasses getDeviceIndex(int indice) {
        if (indice >= 0 && indice < devices.size()) {
            return devices.get(indice);
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
}

