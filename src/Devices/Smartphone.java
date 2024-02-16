package Devices;

public class Smartphone extends DeviceClasses {
    // Costruttore della classe Smartphone che estende la classe DeviceClasses
    public Smartphone(double sale,
                      String device,
                      String brand,
                      String model,
                      String description,
                      double display,
                      double storage,
                      double purchase) {
        // Richiama il costruttore della classe madre DeviceClasses
        super(sale, device, brand, model, description, display, storage, purchase);
    }
}


