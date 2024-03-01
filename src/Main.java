import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        Scanner sc = new Scanner(System.in);
        initializeMenu(sc, warehouse);
    }

    private static void initializeMenu(Scanner sc, Warehouse warehouse) {
        System.out.println("1) Digitare 1 per profilo utente:");
        System.out.println("2) Digitare 2 per profilo operatore:");
        System.out.println("0) Digitare 0 per uscire dal programma");
        String scelta = sc.next();

        while (!scelta.equals("1") && !scelta.equals("2") && !scelta.equals("0")) {
            System.out.println("Scelta non disponiblie");
            System.out.println("1) Digitare 1 per profilo utente:");
            System.out.println("2) Digitare 2 per profilo operatore:");
            System.out.println("0) Digitare 0 per uscire dal programma");

            scelta = sc.next();
        }
        switch (scelta) {
            case "1":
                warehouse.fillWarehouse();
                Cart cart = new Cart();
                User user = new User(warehouse, cart, sc);
                user.userMenu();
                break;
            case "2":
                warehouse.fillWarehouse();
                Operatore operator = new Operatore(warehouse, sc);
                operator.operatorMenu();
                break;
            case "0":
                break;
        }
    }
}