package MenusMethods;

import Devices.DeviceClasses;
import Utils.GetValidInput;
import WarehouseManagement.Warehouse;

import java.util.List;

public class ResearchMethods {
    private final Warehouse warehouse;
    private final boolean iva;
    private final boolean includePurchasePrice;
    private final GetValidInput getValidInput = new GetValidInput();

    public ResearchMethods(Warehouse warehouse, boolean iva, boolean includePurchasePrice) {
        this.warehouse = warehouse;
        this.iva = iva;
        this.includePurchasePrice = includePurchasePrice;
    }


    public List<DeviceClasses> searchByType() {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return null;
        }
        String sceltaDisp = getValidInput.getString("Inserisci il nome del tipo di dispositivo", 15);

        return warehouse.getCompatibles(sceltaDisp, "device");
    }

    // Cerca per brand, ritorna i dispositivi trovati con prezzi dipendenti da iva

    public List<DeviceClasses> searchByBrand() {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return null;
        }
        String sceltaBrand = getValidInput.getString("Inserisci il nome del brand del dispositivo:", 15);
        List<DeviceClasses> brandCompatibili = warehouse.getCompatibles(sceltaBrand, "brand");

       return brandCompatibili;
    }

    // Cerca per modello, ritorna i dispositivi trovati con prezzi dipendenti da iva

    public List<DeviceClasses> searchByModel() {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return null;
        }
        String scelta = getValidInput.getString("Inserisci il nome del modello del dispositivo:", 15);

        return warehouse.getCompatibles(scelta, "model");
    }

    // Cerca per prezzo di vendità, ritorna i dispositivi trovati con prezzi dipendenti da iva

    public List<DeviceClasses> searchBySellPrice() {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return null;
        }
        int price = getValidInput.getInteger("Inserisci il prezzo:");
        int searchedPrice = iva ? (int) (price / 1.22) : price;

       return warehouse.getBySellPrice(searchedPrice);

    }

    // ricerca per range del prezzo di vendita
    public List<DeviceClasses> searchByPriceRange() {
        if (warehouse.isEmpty()) {
            System.out.println("Il magazzino e' vuoto!");
            return null;
        }
        int minPrice = getValidInput.getInteger("Inserisci il prezzo minimo:");
        int maxPrice = getValidInput.getInteger("Inserisci il prezzo massimo:");

        int minSearchedPrice = iva ? (int) (minPrice / 1.22) : minPrice;
        int maxSearchedPrice = iva ? (int) (maxPrice / 1.22) : maxPrice;

        if (minSearchedPrice > maxSearchedPrice) {
            System.out.println("Il prezzo minimo non può essere maggiore del prezzo massimo.");
            return null;
        }

       return warehouse.getRangeSale(minSearchedPrice, maxSearchedPrice);
    }
}