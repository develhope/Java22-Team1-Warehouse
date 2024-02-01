package Devices;

public class Smartphone extends DeviceClasses {
    public Smartphone(double sale,
                      String device,
                      String brand,
                      String model,
                      String description,
                      double display,
                      double storage,
                      double purchase) {
        super(sale, device, brand, model, description, display, storage, purchase);
    }

    private double id;

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }
}


