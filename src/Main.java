import java.util.Scanner;

//IL CODICE CREATO NON VA RICOPIATO

public class Main {
    public static void main(String[] args) {
        // Crea un nuovo magazzino
        Warehouse warehouse = new Warehouse();
        // Riempie il magazzino con dispositivi
        warehouse.fillWarehouse();
        // Crea un nuovo carrello
        Cart cart = new Cart();
        // Crea un nuovo utente
        User user = new User();
        // Crea un nuovo operatore
        Operatore operator = new Operatore();
        // Crea uno scanner per l'input dell'utente
        Scanner sc = new Scanner(System.in);

        // Mostra le opzioni per la selezione del profilo
        System.out.println("1) Digitare 1 per profilo utente:");
        System.out.println("2) Digitare 2 per profilo operatore:");
        String scelta = sc.next();

        // Loop finché l'utente non seleziona una scelta valida
        while (!scelta.equals("1") && !scelta.equals("2")) {
            // Se la scelta non è valida, chiedi all'utente di reinserire
            if (!scelta.equals("1") && !scelta.equals("2")) {
                System.out.println("Scelta non disponiblie");
                System.out.println("1) Digitare 1 per profilo utente:");
                System.out.println("2) Digitare 2 per profilo operatore:");
            }
            // Ottieni l'input dell'utente per la scelta del profilo
            scelta = sc.next();
        }
        // Gestisci la scelta dell'utente
        switch (scelta) {
            case "1":
                // Avvia il menu dell'utente
                user.userMenu(cart, warehouse);
                break;
            case "2":
                // Avvia il menu dell'operatore
                operator.operatorMenu(warehouse);
                break;
        }
    }
}
