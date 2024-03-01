package UserInterface;

import Devices.DeviceClasses;
import UserAndOperatorEnums.MenuOptionsOperator;
import Utils.GetValidInput;
import WarehouseManagement.ResearchMethods;
import WarehouseManagement.Warehouse;

import java.util.Scanner;

public class OperatorMenu {
    private final Warehouse warehouse;
    private final Scanner sc;
    private final ResearchMethods researchMethods;

    public OperatorMenu(Warehouse warehouse, Scanner sc) {
        this.warehouse = warehouse;
        this.sc = sc;
        this.researchMethods = new ResearchMethods(warehouse, false, false);
    }

    public void menu() {
        OperatorMethods.setWarehouse(warehouse);
        MenuOptionsOperator sceltaUser;
        sc.nextLine();

        do {
            System.out.println("Scegli un'opzione:");
            for (int i = 0; i < MenuOptionsOperator.values().length - 1; i++) {
                System.out.println(MenuOptionsOperator.values()[i].ordinal() + ") " + MenuOptionsOperator.values()[i].getStringa() + ": ");
            }
            String input = sc.nextLine();

            if (input.matches("\\d+")) {
                sceltaUser = GetValidInput.getMenuOptionsByIndex(input);
            } else {
                sceltaUser = MenuOptionsOperator.UNKNOWN;
            }

            switch (sceltaUser) {
                case VISUALIZZA_TUTTI_PRODOTTI:
                    researchMethods.printDevices(warehouse.getDevices());
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
                case RICERCA_PER_PREZZO_DI_ACQUISTO:
                    OperatorMethods.searchByPurchasePrice();
                    break;
                case RICERCA_PER_RANGE_DI_ACQUISTO:
                    researchMethods.searchByPriceRange();
                    break;
                case RICERCA_SPESA_MEDIA_DISPOSITIVO:
                    OperatorMethods.searchByAverageDevicePrice();
                    break;
                case AGGIUNGI_DISPOSITIVO_AL_MAGAZZINO:
                    DeviceClasses newDevice = OperatorMethods.addNewDevice();
                    OperatorMethods.setIdAddDeviceInWarehouse(newDevice);
                    break;
                case RIMUOVI_DISPOSITIVO_DAL_MAGAZZINO:
                    OperatorMethods.removeFromWarehouseById();
                    break;
                case FINE:
                    System.out.println("Arrivederci!");
                    break;
                case UNKNOWN:
                    System.out.println("Opzione non valida. Riprova.");
                    break;

            }

        } while (sceltaUser != MenuOptionsOperator.FINE);
    }
}
