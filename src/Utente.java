import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Utente {
    public void userMenu(Cart cart, Warehouse warehouse) {
        Scanner sc = new Scanner(System.in);
        String sceltaUser;
        do {
            System.out.println("Scegli l operazione da effettuare:");
            System.out.println("1) Visualizza tutti prodotti");
            System.out.println("2) Ricerca per tipo dispositivo:");
            System.out.println("3) Ricerca per produttore:");
            System.out.println("4) Ricerca per modello:");
            System.out.println("5) Ricerca per prezzo di vendita:");
            System.out.println("6) Ricerca per range di prezzo:");
            System.out.println("7) Aggiungi al carrello:");
            System.out.println("8) Rimuovi dal carrello:");
            System.out.println("9) Calcolare il totale:");
            System.out.println("10) Visualizza carrello:");
            System.out.println("11) Acquista:");
            System.out.println("0) Fine:");
            sceltaUser = sc.next();

            try {
                switch (sceltaUser) {

                    case "1":
                        if(warehouse.isEmpty()) {
                            System.out.println("Il magazzino e' vuoto!");
                            continue;
                        }
                        warehouse.printAllDevices();
                        break;
                    case "2":
                        if(warehouse.isEmpty()) {
                            System.out.println("Il magazzino e' vuoto!");
                            continue;
                        }
                        System.out.println("Inserisci il nome del tipo di dispositivo:");
                        String sceltaDisp = sc.next();
                        ArrayList<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaDisp, "device");
                        if (devicesCompatibili.isEmpty()) {
                            System.out.println("Nessun dispositivo compatibile trovato.");
                        } else {
                            System.out.println(devicesCompatibili);
                        }
                        break;
                    case "3":
                        if(warehouse.isEmpty()) {
                            System.out.println("Il magazzino e' vuoto!");
                            continue;
                        }
                        System.out.println("Inserisci il nome del brand del dispositivo:");
                        String sceltaBrand = sc.next();
                        ArrayList<DeviceClasses> brandCompatibili = warehouse.getCompatibles(sceltaBrand, "brand");
                        if (brandCompatibili.isEmpty()) {
                            System.out.println("Nessun dispositivo compatibile trovato.");
                        } else {
                            System.out.println(brandCompatibili);
                        }
                        break;
                    case "4":
                        if(warehouse.isEmpty()) {
                            System.out.println("Il magazzino e' vuoto!");
                            continue;
                        }
                        System.out.println("Inserisci il nome del modello del dispositivo:");
                        String sceltaModel = sc.next();
                        ArrayList<DeviceClasses> modelCompatibili = warehouse.getCompatibles(sceltaModel, "model");
                        if (modelCompatibili.isEmpty()) {
                            System.out.println("Nessun dispositivo compatibile trovato.");
                        } else {
                            System.out.println(modelCompatibili);
                        }
                        break;
                    case "5":
                        if(warehouse.isEmpty()) {
                            System.out.println("Il magazzino e' vuoto!");
                            continue;
                        }
                        System.out.println("Inserisci il prezzo:");
                        int sceltaForPrice = sc.nextInt();
                        ArrayList<DeviceClasses> priceCompatibili = warehouse.getBySellPrice(sceltaForPrice);
                        if (priceCompatibili.isEmpty()) {
                            System.out.println("Nessun dispositivo compatibile trovato.");
                        } else {
                            System.out.println(priceCompatibili);
                        }
                        break;
                    case "6":
                        if(warehouse.isEmpty()) {
                            System.out.println("Il magazzino e' vuoto!");
                            continue;
                        }
                        System.out.println("Inserisci il prezzo minimo:");
                        int sceltaForPriceRange = sc.nextInt();
                        System.out.println("Inserisci il prezzo massimo:");
                        int sceltaForPriceRange2 = sc.nextInt();

                        ArrayList<DeviceClasses> rangeCompatibili = warehouse.getRangeSale(sceltaForPriceRange, sceltaForPriceRange2);
                        if (rangeCompatibili.isEmpty()) {
                            System.out.println("Nessun dispositivo in range trovato");
                        } else {
                            System.out.println(rangeCompatibili);
                        }
                        break;
                    case "7":
                        if(warehouse.isEmpty()) {
                            System.out.println("Il magazzino e' vuoto!");
                            continue;
                        }
                        System.out.println("Digita un id per aggiungere al carrello:");
                            long sceltaId = sc.nextLong();
                            if (!warehouse.containsDeviceById(sceltaId)) {
                                System.out.println("Non è stato trovato alcun dispositivo con questo ID");
                                break;
                            }
                            fromWarehouseToCart(warehouse, cart, sceltaId);
                        break;
                    case "8":
                        if (cart.isEmpty()) {
                            System.out.println("Il carrello è vuoto");
                            break;
                        }
                        System.out.println("Digita un id per rimuovere al carrello:");
                            long sceltaId2 = sc.nextLong();
                            if (!cart.containsDeviceById(sceltaId2)) {
                                System.out.println("Non è stato trovato alcun dispositivo con questo ID");
                                break;
                            }
                            fromCartToWarehouse(warehouse, cart, sceltaId2);
                        break;
                    case "9":
                        if (cart.isEmpty()) {
                            System.out.println("Il carrello è vuoto");
                            break;
                        }
                        System.out.println("Il prezzo finale del carrello è:");
                        System.out.println(cart.getFinalPrice());
                        break;
                    case "10":
                        cart.printAllDevices();
                        break;
                    case "11":

                        if (cart.isEmpty()) {
                            System.out.println("Il carrello è ancora vuoto.");
                            break;

                        }
                        System.out.println("1) per procedere;");
                        System.out.println("2) per tornare nel menu principale;");
                        String sceltaFinale = sc.next();
                        if (Objects.equals(sceltaFinale, "1")) {
                            boolean partitaIva = getIvaUtente(new Scanner(System.in));

                            System.out.println(finalizzaVendita(cart, partitaIva));
                            break;
                        } else if (Objects.equals(sceltaFinale, "2")) {
                            break;
                        } else {
                            System.out.println("Scelta non consentita!");
                        }
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println("Scelta non valida");
                }
            } catch (InputMismatchException e) {
            }
        } while (!sceltaUser.equals("0"));
    }

    public static void fromWarehouseToCart(Warehouse warehouse, Cart cart, long id) {
        cart.addDevice(warehouse.getDeviceById(id));
        warehouse.removeDeviceById(id);
        cart.printAllDevices();
    }

    public static void fromCartToWarehouse(Warehouse warehouse, Cart cart, long id) {
        warehouse.addDevice(cart.getDeviceById(id));
        cart.removeDeviceById(id);
        cart.printAllDevices();
    }

    public static String finalizzaVendita(Cart cart, boolean iva) {
        double finalPrice;
        if (iva) {
            finalPrice = cart.getFinalPrice() * 1.22;
        } else {
            finalPrice = cart.getFinalPrice();
        }
        cart.emptyList();
        return "Questo è il tuo prezzo finale: " + finalPrice;
    }

    public static boolean getIvaUtente(Scanner sc) {
        while (true) {
            System.out.println("1) Sei un privato ");
            System.out.println("2) Hai una partita iva");
            if (sc.hasNextInt()) {
                int sceltaUtente = sc.nextInt();
                if (sceltaUtente == 1) {
                    return true;
                } else if (sceltaUtente == 2) {
                    return false;
                } else {
                    System.out.println("Scelta non valida, riprova.");
                }
            } else {
                System.out.println("Scelta non valida, riprova.");
                sc.next();
            }
        }
    }


}
