import java.util.ArrayList;
import java.util.Arrays;

import Dispositi.ClasseDispositivi;

public class Magazzino {
    private ArrayList<ClasseDispositivi> dispositivi = new ArrayList<>();

    public Magazzino() {
        this.dispositivi = new ArrayList<>();
    }

    ;

    public void aggiungi(ClasseDispositivi dispositivo) {
        dispositivi.add(dispositivo);
    }

    public void rimuovi(int index) {
        if (index >= 0 && index < dispositivi.size()) {
            dispositivi.remove(index);
        } else {
            System.out.println("Indice non valido");
        }
    }

    public void stampa() {
        for (int i = 0; i < dispositivi.size(); i++) {
            System.out.println(i + " : " + dispositivi.get(i));
        }
    }

    public ArrayList<ClasseDispositivi> getPerDispositivo(String input) {
        String inputLowerCase = input.toLowerCase();
        ArrayList<ClasseDispositivi> dispositiviCompatibili = new ArrayList<>();

        for (int i = 0; i < dispositivi.size(); i++) {
            String deviceLowerCase = dispositivi.get(i).getDevice().toLowerCase();

            if (inputLowerCase.equals(deviceLowerCase)) {
                dispositiviCompatibili.add(dispositivi.get(i));
            }
        }
        return dispositiviCompatibili;
    }

    public ArrayList<ClasseDispositivi> getPerModello(String input) {
        String inputLowerCase = input.toLowerCase();
        ArrayList<ClasseDispositivi> dispositiviCompatibili = new ArrayList<>();

        for (int i = 0; i < dispositivi.size(); i++) {
            String deviceLowerCase = dispositivi.get(i).getModel().toLowerCase();

            if (inputLowerCase.equals(deviceLowerCase)) {
                dispositiviCompatibili.add(dispositivi.get(i));
            }
        }
        return dispositiviCompatibili;
    }

    public ArrayList<ClasseDispositivi> getPerBrand(String input) {
        String inputLowerCase = input.toLowerCase();
        ArrayList<ClasseDispositivi> dispositiviCompatibili = new ArrayList<>();

        for (int i = 0; i < dispositivi.size(); i++) {
            String deviceLowerCase = dispositivi.get(i).getBrand().toLowerCase();

            if (inputLowerCase.equals(deviceLowerCase)) {
                dispositiviCompatibili.add(dispositivi.get(i));
            }
        }
        return dispositiviCompatibili;
    }

    public ArrayList<ClasseDispositivi> getRangeSell(int valLow, int valHigh) {

        ArrayList<ClasseDispositivi> dispositiviCompatibili = new ArrayList<>();

        for (int i = 0; i < dispositivi.size(); i++) {

            if (dispositivi.get(i).getSale() <= valHigh && dispositivi.get(i).getSale() >= valLow) {
                dispositiviCompatibili.add(dispositivi.get(i));
            }
        }
        return dispositiviCompatibili;
    }

    public ClasseDispositivi getIndiceDispositivo(int indice) {
        if (indice >= 0 && indice < dispositivi.size()) {
            return dispositivi.get(indice);
        } else {
            System.out.println("Indice non valido");
            return null;
        }
    }

    public ArrayList<ClasseDispositivi> getRangeBuy(int valLow, int valHigh) {

        ArrayList<ClasseDispositivi> dispositiviCompatibili = new ArrayList<>();

        for (int i = 0; i < dispositivi.size(); i++) {

            if (dispositivi.get(i).getPurchase() <= valHigh && dispositivi.get(i).getPurchase() >= valLow) {
                dispositiviCompatibili.add(dispositivi.get(i));
            }
        }
        return dispositiviCompatibili;
    }

    public double getAverageDevicePrice(String device) {
        ArrayList<ClasseDispositivi> devices = new ArrayList<>();
        double average = 0;

        for (int i = 0; i < dispositivi.size(); i++) {
            if (dispositivi.get(i).getDevice() == device) {
                devices.add(dispositivi.get(i));
            }
        }
        for (int i = 0; i < devices.size(); i++) {
            average += devices.get(i).getPurchase();
        }
        return average / devices.size();
    }
}

