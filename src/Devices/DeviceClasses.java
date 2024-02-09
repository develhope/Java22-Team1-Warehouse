package Devices;

import java.util.Objects;

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
        return
                "Device: " + device +
                        ", Brand: " + brand +
                        ", Model: " + model +
                        ", Description: " + description +
                        ", Display: " + display +
                        ", Storage: " + storage +
                        ", Surchaseç " + purchase +
                        ", Sale: " + sale +
                        ", Id: " + id + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceClasses that = (DeviceClasses) o;

        if (Double.compare(display, that.display) != 0) return false;
        if (Double.compare(storage, that.storage) != 0) return false;
        if (Double.compare(purchase, that.purchase) != 0) return false;
        if (Double.compare(sale, that.sale) != 0) return false;
        if (!Objects.equals(device, that.device)) return false;
        if (!Objects.equals(brand, that.brand)) return false;
        if (!Objects.equals(model, that.model)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = device != null ? device.hashCode() : 0;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(display);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(storage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(purchase);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sale);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}