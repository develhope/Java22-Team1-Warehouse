package UserInterface;

import UserAndOperatorEnums.MenuOptionsUser;
import Utils.GetValidInput;
import WarehouseManagement.Cart;
import WarehouseManagement.ResearchMethods;
import WarehouseManagement.Warehouse;

import java.util.Scanner;

public class UserMenu {
    private final Warehouse warehouse;
    private final Cart cart;
    private final Scanner sc;

    public UserMenu(Warehouse warehouse, Cart cart, Scanner sc) {
        this.warehouse = warehouse;
        this.cart = cart;
        this.sc = sc;
    }
    public void menu() {
        MenuOptionsUser sceltaUser;
        boolean iva = UserMethods.getIvaUser();
        UserMethods.setIva(iva);
        ResearchMethods researchMethods = new ResearchMethods(warehouse, iva, false);


        sc.nextLine();
        do {
            System.out.println("Scegli un'opzione:");
            for (int i = 0; i < MenuOptionsUser.values().length - 1; i++) {
                System.out.println(MenuOptionsUser.values()[i].ordinal() + ") " + MenuOptionsUser.values()[i].getStringa() + ": ");
            }

            String input = sc.nextLine();

            if (input.matches("\\d+")) {
                sceltaUser = GetValidInput.getMenuOptionsByIndexUser(input);
            } else {
                sceltaUser = MenuOptionsUser.UNKNOWN;
            }


            switch (sceltaUser) {

                case VISUALIZZA_TUTTI_PRODOTTI:
                    UserMethods.printDevices(warehouse.getDevices());
                    break;
                case RICERCA_PER_TIPO_DISPOSITIVO:
                    researchMethods.searchByType();
                    break;
                case RICERCA_PER_PRODUTTORE:
                    researchMethods.searchByBrand();
                    break;
                case RICERCA_PER_MODELLO:
                    researchMethods.searchByModel();
                    break;
                case RICERCA_PER_PREZZO_DI_VENDITA:
                    researchMethods.searchBySellPrice();
                    break;
                case RICERCA_PER_RANGE_DI_VENDITA:
                    researchMethods.searchByPriceRange();
                    break;
                case AGGIUNGI_AL_CARRELLO:
                    UserMethods.addToCartById();
                    break;
                case RIMUOVI_DAL_CARRELLO:
                    UserMethods.removeFromCartById();
                    break;
                case CALCOLARE_IL_TOTALE:
                    UserMethods.calcAndPrintTotal();
                    break;
                case VISUALIZZA_IL_CARRELLO:
                    researchMethods.printDevices(cart.getDevices());
                    break;
                case ACQUISTA:
                    UserMethods.finalizeSaleMenu();
                    break;
                case FINE:
                    System.out.println("Arrivederci!");
                    break;
                case UNKNOWN:
                    System.out.println("Opzione non valida. Riprova.");
                    break;

            }

        } while (sceltaUser != MenuOptionsUser.FINE);
    }
}
