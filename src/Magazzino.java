import java.util.ArrayList;
import java.util.Arrays;

import Dispositi.ClasseDispositivi;

public class Magazzino {
    private ArrayList<ClasseDispositivi> dispositivi = new ArrayList<>();

    public Magazzino() {
        this.dispositivi = new ArrayList<>();
    }

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

    public ArrayList<ClasseDispositivi> getCompatibili(String input, String tipoRicerca) {
        String inputLowerCase = input.toLowerCase();
        ArrayList<ClasseDispositivi> dispositiviCompatibili = new ArrayList<>();

        for (int i = 0; i < dispositivi.size(); i++) {
            String researchLoweCase = "";

            switch (tipoRicerca) {
                case "device":
                    researchLoweCase = dispositivi.get(i).getDevice().toLowerCase();
                    break;
                case "modello":
                    researchLoweCase = dispositivi.get(i).getModel().toLowerCase();
                    break;
                case "brand":
                    researchLoweCase = dispositivi.get(i).getBrand().toLowerCase();
            }

            if (inputLowerCase.equals(researchLoweCase)) {
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
        double average = 0;
        int counter = 0;
        String deviceLowerCase = device.trim().toLowerCase();

        for (int i = 0; i < dispositivi.size(); i++) {
            if (dispositivi.get(i).getDevice().toLowerCase().equals(deviceLowerCase)) {
                average += dispositivi.get(i).getPurchase();
                counter++;
            }
        }
        return average / counter;
    }
}

