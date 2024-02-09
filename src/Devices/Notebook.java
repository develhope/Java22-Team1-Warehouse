package Devices;

import java.util.Objects;

public class Notebook extends Devices.DeviceClasses {
    public Notebook(double sale,
                    String device,
                    String brand,
                    String model,
                    String description,
                    double display,
                    double storage,
                    double purchase) {
        super(sale, device, brand, model, description, display, storage, purchase);
    }


}