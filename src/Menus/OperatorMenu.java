package Menus;

import Devices.DeviceClasses;
import MenusMethods.OperatorMethods;
import MenusMethods.ResearchMethods;
import UserAndOperatorEnums.MenuOptionsOperator;
import Utils.GetValidInput;
import WarehouseManagement.Warehouse;

import java.text.DecimalFormat;
import java.util.Scanner;

public class OperatorMenu {
    private final Warehouse warehouse;
    private final Scanner sc;

    public OperatorMenu(Warehouse warehouse, Scanner sc) {
        this.warehouse = warehouse;
        this.sc = sc;
    }

    public void menu() {
        OperatorMethods operatorMethods = new OperatorMethods(warehouse);
        ResearchMethods researchMethods = new ResearchMethods(warehouse, false);
        GetValidInput getValidInput = new GetValidInput();
        MenuOptionsOperator sceltaUser;

        do {
            System.out.println("Scegli un'opzione:");
            for (int i = 0; i < MenuOptionsOperator.values().length - 1; i++) {
                System.out.println(MenuOptionsOperator.values()[i].ordinal() + ") " + MenuOptionsOperator.values()[i].getStringa() + ": ");
            }
            String input = sc.nextLine();

            if (input.matches("\\d+")) {
                sceltaUser = getValidInput.getMenuOptionsByIndex(input);
            } else {
                sceltaUser = MenuOptionsOperator.UNKNOWN;
            }

            switch (sceltaUser) {
                case VISUALIZZA_TUTTI_PRODOTTI:
                    operatorMethods.printDevices(warehouse.getDevices());
                    break;
                case RICERCA_PER_TIPO_DISPOSITIVO:
                    operatorMethods.printDevices(researchMethods.searchByType());
                    break;
                case RICERCA_PER_PRODUTTORE:
                    operatorMethods.printDevices(researchMethods.searchByBrand());
                    break;
                case RICERCA_PER_MODELLO:
                    operatorMethods.printDevices(researchMethods.searchByModel());
                    break;
                case RICERCA_PER_PREZZO_DI_VENDITA:
                    operatorMethods.printDevices(researchMethods.searchBySellPrice());
                    break;
                case RICERCA_PER_PREZZO_DI_ACQUISTO:
                    operatorMethods.printDevices(operatorMethods.searchByPurchasePrice());
                    break;
                case RICERCA_PER_RANGE_DI_ACQUISTO:
                    operatorMethods.printDevices(researchMethods.searchByPriceRange());
                    break;
                case RICERCA_SPESA_MEDIA_DISPOSITIVO:
                    System.out.println(operatorMethods.searchByAverageDevicePrice());
                    break;
                case AGGIUNGI_DISPOSITIVO_AL_MAGAZZINO:
                    DecimalFormat df = new DecimalFormat("#.##");
                    DeviceClasses device = operatorMethods.addNewDevice();
                    if (operatorMethods.setIdAddDeviceInWarehouse(device)) {
                        System.out.println("Il dispositivo:\n" + "Id: " + device.getId() +
                                ", Dispositivo: " + device.getDevice() +
                                ", Brand: " + device.getBrand() +
                                "\nModello: " + device.getModel() +
                                ", Descrizione: " + device.getDescription() +
                                ", Display: " + df.format(device.getDisplay()) + "o" +
                                ", Archiviazione: " + df.format(device.getStorage()) + "gb" +
                                "\nPrezzo di vendità: " + df.format(device.getSale()) + "€" + ", Prezzo di acquisto: " + df.format(device.getPurchase()) + "€\n" + "è stato aggiunto con successo.");
                    } else {
                        System.out.println("Si è verificato un errore...");
                    }
                    break;
                case RIMUOVI_DISPOSITIVO_DAL_MAGAZZINO:
                    operatorMethods.removeFromWarehouseById();
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
