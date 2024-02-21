import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        warehouse.fillWarehouse();
        Cart cart = new Cart();
        User user = new User();
        Operatore operator = new Operatore();
        Scanner sc = new Scanner(System.in);

        System.out.println("1) Digitare 1 per profilo utente:");
        System.out.println("2) Digitare 2 per profilo operatore:");
        String scelta = sc.next();

        while (!scelta.equals("1") && !scelta.equals("2")) {

            if (!scelta.equals("1") && !scelta.equals("2")) {
                System.out.println("Scelta non disponiblie");
                System.out.println("1) Digitare 1 per profilo utente:");
                System.out.println("2) Digitare 2 per profilo operatore:");
            }
            scelta = sc.next();
        }
        switch (scelta) {
            case "1":
                user.userMenu(cart, warehouse);
                break;
            case "2":
                operator.operatorMenu(warehouse);
                break;
        }
    }
}

//