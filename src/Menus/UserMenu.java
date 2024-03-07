package Menus;

import MenusMethods.ResearchMethods;
import MenusMethods.UserMethods;
import UserAndOperatorEnums.MenuOptionsUser;
import Utils.GetValidInput;
import WarehouseManagement.Cart;
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
        UserMethods userMethods = new UserMethods(warehouse, cart, iva, sc);
        ResearchMethods researchMethods = new ResearchMethods(warehouse, iva);
        GetValidInput getValidInput = new GetValidInput();

        do {
            System.out.println("Scegli un'opzione:");
            for (int i = 0; i < MenuOptionsUser.values().length - 1; i++) {
                System.out.println(MenuOptionsUser.values()[i].ordinal() + ") " + MenuOptionsUser.values()[i].getStringa() + ": ");
            }

            String input = sc.nextLine();

            if (input.matches("\\d+")) {
                sceltaUser = getValidInput.getMenuOptionsByIndexUser(input);
            } else {
                sceltaUser = MenuOptionsUser.UNKNOWN;
            }


            switch (sceltaUser) {

                case VISUALIZZA_TUTTI_PRODOTTI:
                    userMethods.printDevices(warehouse.getDevices());
                    break;
                case RICERCA_PER_TIPO_DISPOSITIVO:
                    userMethods.printDevices(researchMethods.searchByType());
                    break;
                case RICERCA_PER_PRODUTTORE:
                    userMethods.printDevices(researchMethods.searchByBrand());
                    break;
                case RICERCA_PER_MODELLO:
                    userMethods.printDevices(researchMethods.searchByModel());
                    break;
                case RICERCA_PER_PREZZO_DI_VENDITA:
                    userMethods.printDevices(researchMethods.searchBySellPrice());
                    break;
                case RICERCA_PER_RANGE_DI_VENDITA:
                    userMethods.printDevices(researchMethods.searchByPriceRange());
                    break;
                case AGGIUNGI_AL_CARRELLO:
                    userMethods.printDevices(userMethods.addToCartById());
                    break;
                case RIMUOVI_DAL_CARRELLO:
                    userMethods.printDevices(userMethods.removeFromCartById());
                    break;
                case CALCOLARE_IL_TOTALE:
                    if (userMethods.calcAndPrintTotal() == 0.0) {
                        System.out.println("Il carrello e vuoto");
                    } else {
                        System.out.println("Questo e il tuo totale: " + userMethods.calcAndPrintTotal());
                    }
                    break;
                case VISUALIZZA_IL_CARRELLO:
                    if(cart.isEmpty()) {
                        System.out.println("Il carrello e vuoto");
                    } else {
                        userMethods.printDevices(cart.getDevices());
                    }
                    break;
                case ACQUISTA:
                    if (cart.isEmpty()) {
                        System.out.println("Il carrello è vuoto.");
                    } else {
                        System.out.println(userMethods.finalizeSaleMenu());
                    }
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
