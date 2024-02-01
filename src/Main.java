import Dispositi.Smartphone;
import Dispositi.Notebook;
import Dispositi.Tablet;
import java.util.ArrayList;
import java.util.Scanner;
import Dispositi.ClasseDispositivi;

public class Main {
    public static void main(String[] args) {
        Magazzino magazzino = new Magazzino();
        Carrello carrello = new Carrello();
        Scanner sc = new Scanner(System.in);

        Notebook notebook = new Notebook(1449, "Notebook","Samsung","Galaxy Book3","Gaming computer",15.6,1000,899);

        Smartphone smartphone = new Smartphone(159,"Smartphone","Samsung","A14","nero",6.6,128,49);

        Tablet tablet = new Tablet(549,"Tablet","Samsung","Galaxy Tab S8","grigio siderale",11,128,349);

        Notebook notebook1 = new Notebook(1449, "Notebook","Samsung","Galaxy Book3","Gaming computer",15.6,1000,5000);

        magazzino.aggiungi(notebook1);
        magazzino.aggiungi(notebook);
        magazzino.aggiungi(tablet);
        magazzino.aggiungi(smartphone);

//        System.out.println("Cerca per:");
//        System.out.println("1: Modello");
//        System.out.println("2: Tipo dispositivo");
//        System.out.println("3: Descrizione");
//        System.out.println("4: Prezzo vendita");
//        System.out.println("5: Prezzo acquisto ");
//        System.out.println("Cerca per:");
//
//
        String inputString = sc.nextLine();
        System.out.println(inputString);
//
        ArrayList<ClasseDispositivi> chosen1 = magazzino.getCompatibili(inputString,"modello" );
//        ArrayList<ClasseDispositivi> chosen2 = magazzino.getPerModello(inputString);
//        ArrayList<ClasseDispositivi> chosen3 =  magazzino.getPerBrand(inputString);
//        ArrayList<ClasseDispositivi> range =  magazzino.getRangeSell(0,500);
//        ArrayList<ClasseDispositivi> range2 =  magazzino.getRangeBuy(0,500);


        magazzino.stampa();
        System.out.println(magazzino.getAverageDevicePrice("smartphone"));


        if (chosen1.isEmpty()) {
            System.out.println("Nessun dispositivo trovato.");
        } else {
            System.out.println("Dispositivi trovati:");
            for (ClasseDispositivi dispositivo : chosen1) {
                System.out.println(dispositivo);
            }
        }
//        if (chosen2.isEmpty()) {
//            System.out.println("Nessun dispositivo trovato.");
//        } else {
//            System.out.println("Dispositivi trovati:");
//            for (ClasseDispositivi dispositivo : chosen2) {
//                System.out.println(dispositivo);
//            }
//        }
//        if (chosen3.isEmpty()) {
//            System.out.println("Nessun dispositivo trovato.");
//        } else {
//            System.out.println("Dispositivi trovati:");
//            for (ClasseDispositivi dispositivo : chosen3) {
//                System.out.println(dispositivo);
//            }
//        }
//        if (range.isEmpty()) {
//            System.out.println("Nessun dispositivo trovato.");
//        } else {
//            System.out.println("Dispositivi trovati:");
//            for (ClasseDispositivi dispositivo : range) {
//                System.out.println(dispositivo);
//            }
//        }
//        if (range2.isEmpty()) {
//            System.out.println("Nessun dispositivo trovato.");
//        } else {
//            System.out.println("Dispositivi trovati:");
//            for (ClasseDispositivi dispositivo : range2) {
//                System.out.println(dispositivo);
//            }
//        }
    }

}
