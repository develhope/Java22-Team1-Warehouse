package Devices;

public class DeviceClasses {
    private String device;
    private String brand;
    private String model;
    private String description;
    private double display;
    private double storage;
    private double purchase;
    private double sale;

    private long id;

    public DeviceClasses(double sale,
                         String device,
                         String brand,
                         String model,
                         String description,
                         double display,
                         double storage,
                         double purchase) {
        this.device = device;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.display = display;
        this.storage = storage;
        this.purchase = purchase;
        this.sale = sale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public String getDevice() {
        return device;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    public double getDisplay() {
        return display;
    }

    public double getStorage() {
        return storage;
    }

    public double getPurchase() {
        return purchase;
    }

    public double getSale() {
        return sale;
    }


    @Override
    public String toString() {
        return " " +
                "Device='" + device + '\'' +
                ", Brand='" + brand + '\'' +
                ", Model='" + model + '\'' +
                ", Description='" + description + '\'' +
                ", Display=" + display +
                ", storage=" + storage +
                ", purchase=" + purchase +
                ", sale=" + sale +
                ", id="+"\n";
    }
}