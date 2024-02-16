import Devices.DeviceClasses;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//IL CODICE CREATO NON VA RICOPIATO

public class User {
    // Metodo per gestire il menu dell'utente
    public void userMenu(Cart cart, Warehouse warehouse) {
        Scanner sc = new Scanner(System.in);
        String sceltaUser;
        // Loop per mantenere attivo il menu finché l'utente non sceglie di uscire
        do {
            // Mostra le opzioni disponibili nel menu
            System.out.println("Scegli l'operazione da effettuare:");
            System.out.println("1) Visualizza tutti i prodotti");
            System.out.println("2) Ricerca per tipo di dispositivo:");
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
            // Ottiene l'input dell'utente
            sceltaUser = sc.next();

            try {
                switch (sceltaUser) {
                    // Caso 1: Visualizza tutti i prodotti nel magazzino
                    case "1":
                        if (warehouse.isEmpty()) {
                            System.out.println("Il magazzino è vuoto.");
                            continue;
                        }
                        warehouse.printAllDevices();
                        break;
                    // Caso 2: Ricerca per tipo di dispositivo
                    case "2":
                        searchByType(warehouse, sc);
                        break;
                    // Caso 3: Ricerca per produttore
                    case "3":
                        searchByBrand(warehouse, sc);
                        break;
                    // Caso 4: Ricerca per modello
                    case "4":
                        searchByModel(warehouse, sc);
                        break;
                    // Caso 5: Ricerca per prezzo di vendita
                    case "5":
                        searchBySellPrice(warehouse, sc);
                        break;
                    // Caso 6: Ricerca per range di prezzo
                    case "6":
                        searchByPriceRange(warehouse, sc);
                        break;
                    // Caso 7: Aggiungi un dispositivo al carrello
                    case "7":
                        addToCartById(warehouse, cart, sc);
                        break;
                    // Caso 8: Rimuovi un dispositivo dal carrello
                    case "8":
                        removeFromCartById(warehouse, cart, sc);
                        break;
                    // Caso 9: Calcola il totale del carrello
                    case "9":
                        if (cart.isEmpty()) {
                            System.out.println("Il carrello è vuoto.");
                            break;
                        }
                        System.out.println("Il prezzo finale del carrello è:");
                        System.out.println(cart.getFinalPrice());
                        break;
                    // Caso 10: Visualizza tutti i dispositivi nel carrello
                    case "10":
                        cart.printAllDevices();
                        break;
                    // Caso 11: Acquista i prodotti nel carrello
                    case "11":
                        if (cart.isEmpty()) {
                            System.out.println("Il carrello è vuoto.");
                            break;
                        }
                        System.out.println("1) per procedere;");
                        System.out.println("2) per tornare nel menu principale;");
                        String sceltaFinale = sc.next();
                        if (sceltaFinale.equals("1")) {
                            boolean partitaIva = getIvaUser(new Scanner(System.in));
                            System.out.println(finalizeSale(cart, partitaIva));
                            break;
                        } else if (sceltaFinale.equals("2")) {
                            break;
                        } else {
                            System.out.println("Scelta non consentita.");
                        }
                        break;
                    // Caso 0: Esci dal menu
                    case "0":
                        break;
                    // Gestione dell'input non valido
                    default:
                        System.out.println("Scelta non valida.");
                }
            } catch (InputMismatchException e) {
            }
        } while (!sceltaUser.equals("0"));
    }

    // Metodo per spostare un dispositivo dal magazzino al carrello
    public static void fromWarehouseToCart(Warehouse warehouse, Cart cart, long id) {
        cart.addDevice(warehouse.getDeviceById(id));
        warehouse.removeDeviceById(id);
        cart.printAllDevices();
    }

    // Metodo per spostare un dispositivo dal carrello al magazzino
    public static void fromCartToWarehouse(Warehouse warehouse, Cart cart, long id) {
        warehouse.addDevice(cart.getDeviceById(id));
        cart.removeDeviceById(id);
        cart.printAllDevices();
    }

    // Metodo per finalizzare l'acquisto nel carrello
    public static String finalizeSale(Cart cart, boolean iva) {
        double finalPrice;
        // Calcola il prezzo finale considerando l'IVA se necessario
        if (iva) {
            finalPrice = cart.getFinalPrice() * 1.22;
        } else {
            finalPrice = cart.getFinalPrice();
        }
        // Svuota il carrello dopo l'acquisto
        cart.emptyList();
        return "Questo è il tuo prezzo finale: " + finalPrice;
    }

    // Metodo per ottenere il tipo di utente per l'applicazione dell'IVA
    public static boolean getIvaUser(Scanner sc) {
        while (true) {
            System.out.println("Seleziona il tipo di utente:");
            System.out.println("1) Privato");
            System.out.println("2) Azienda con Partita IVA");
            if (sc.hasNextInt()) {
                int sceltaUser = sc.nextInt();
                switch (sceltaUser) {
                    case 1:
                        return true;
                    case 2:
                        return false;
                    default:
                        System.out.println("Opzione non valida, riprova.");
                        break;
                }
            } else {
                System.out.println("Input non valido, riprova.");
                sc.next();
            }
        }
    }

    // Metodo privato per la ricerca per tipo di dispositivo
    private void searchByType(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        System.out.println("Inserisci il nome del tipo di dispositivo:");
        String sceltaDisp = sc.next();
        ArrayList<DeviceClasses> devicesCompatibili = warehouse.getCompatibles(sceltaDisp, "device");
        if (devicesCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            System.out.println(devicesCompatibili);
        }
    }

    // Metodo privato per la ricerca per produttore
    private void searchByBrand(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        System.out.println("Inserisci il nome del brand del dispositivo:");
        String sceltaBrand = sc.next();
        ArrayList<DeviceClasses> brandCompatibili = warehouse.getCompatibles(sceltaBrand, "brand");
        if (brandCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            System.out.println(brandCompatibili);
        }
    }

    // Metodo privato per la ricerca per modello
    private void searchByModel(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        System.out.println("Inserisci il nome del modello del dispositivo:");
        String sceltaModel = sc.next();
        ArrayList<DeviceClasses> modelCompatibili = warehouse.getCompatibles(sceltaModel, "model");
        if (modelCompatibili.isEmpty()) {
            System.out.println("Nessun dispositivo compatibile trovato.");
        } else {
            System.out.println(modelCompatibili);
        }
    }

    // Metodo privato per la ricerca per prezzo di vendita
    private void searchBySellPrice(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        System.out.println("Inserisci il prezzo:");
        try {
            int scelta = Integer.parseInt(sc.next());
            ArrayList<DeviceClasses> priceCompatibili = warehouse.getBySellPrice(scelta);
            if (priceCompatibili.isEmpty()) {
                System.out.println("Nessun dispositivo compatibile trovato.");
            } else {
                System.out.println(priceCompatibili);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, inserisci un numero valido per il prezzo.");
            sc.nextLine();
        }
    }

    // Metodo privato per la ricerca per range di prezzo
    private void searchByPriceRange(Warehouse warehouse, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        try {
            System.out.println("Inserisci il prezzo minimo:");
            int scelta1 = Integer.parseInt(sc.next());
            System.out.println("Inserisci il prezzo massimo:");
            int scelta2 = Integer.parseInt(sc.next());

            ArrayList<DeviceClasses> rangeCompatibili = warehouse.getRangeSale(scelta1, scelta2);
            if (rangeCompatibili.isEmpty()) {
                System.out.println("Nessun dispositivo in range trovato.");
            } else {
                System.out.println(rangeCompatibili);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un numero.");
            sc.nextLine();
        }
    }

    // Metodo privato per aggiungere un dispositivo al carrello tramite ID
    private void addToCartById(Warehouse warehouse, Cart cart, Scanner sc) {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto.");
            return;
        }
        System.out.println("Digita un id per aggiungere al carrello:");
        try {
            long sceltaId = Long.parseLong(sc.next());
            if (!warehouse.containsDeviceById(sceltaId)) {
                System.out.println("Non è stato trovato alcun dispositivo con questo ID");
                return;
            }
            fromWarehouseToCart(warehouse, cart, sceltaId);
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un formato ID corretto.");
            sc.nextLine();
        }
    }

    // Metodo privato per rimuovere un dispositivo dal carrello tramite ID
    private void removeFromCartById(Warehouse warehouse, Cart cart, Scanner sc) {
        if (cart.isEmpty()) {
            System.out.println("Il carrello è vuoto.");
            return;
        }
        System.out.println("Digita un id per rimuovere al carrello:");
        try {
            long sceltaId2 = Long.parseLong(sc.next());
            if (!cart.containsDeviceById(sceltaId2)) {
                System.out.println("Non è stato trovato alcun dispositivo con questo ID.");
                return;
            }
            fromCartToWarehouse(warehouse, cart, sceltaId2);
        } catch (NumberFormatException e) {
            System.out.println("Input non valido, assicurati di mettere un formato ID corretto.");
            sc.nextLine();
        }
    }

}