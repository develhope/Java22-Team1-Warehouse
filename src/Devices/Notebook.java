package Devices;

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

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}