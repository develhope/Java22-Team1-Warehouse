package Devices;

import java.util.Objects;

public class Notebook extends Devices.DeviceClasses {
    // Costruttore della classe Notebook che estende la classe DeviceClasses
    public Notebook(double sale,
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