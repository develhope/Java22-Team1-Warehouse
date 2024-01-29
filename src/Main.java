import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Magazzino magazzino = new Magazzino();
        Carrello carrello = new Carrello();
        Scanner sc = new Scanner(System.in);

        Notebook notebook = new Notebook(magazzino, "Notebook","Samsung","Galaxy book3","Tua zia",15.6,512,1999);

        Smartphone smartphone = new Smartphone(magazzino,"Tablet","Samsung","Patata","Cocco bello cocco fresco",14.7,700,499);

        Tablet tablet = new Tablet(magazzino,"Tablet","Samsung","Patata","Bello acquisto",12.9,400,299);

        magazzino.stampa();

        System.out.println("Cerca per:");
        System.out.println("1: Modello");
        System.out.println("2: Dispositivo");
        System.out.println("3:");
        System.out.println("Cerca per:");
        System.out.println("Cerca per:");
        System.out.println("Cerca per:");


        String inputString = sc.nextLine().toLowerCase();
        System.out.println(inputString);

        ArrayList<ClasseDispositivi> chosen1 = magazzino.getPerDispositivo(inputString);

        if (chosen1.isEmpty()) {
            System.out.println("Nessun dispositivo trovato.");
        } else {
            System.out.println("Dispositivi trovati:");
            for (ClasseDispositivi dispositivo : chosen1) {
                System.out.println(dispositivo);
            }
        }
    }

}
