public class ClasseDispositivi {
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

    private String device;
    private String brand;
    private String model;
    private String description;
    private double display;
    private double storage;
    private double purchase;

    public ClasseDispositivi (Magazzino magazzino, String device, String brand, String model, String description, double display, double storage, double purchase) {
        this.device = device;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.display = display;
        this.storage = storage;
        this.purchase = purchase;

        magazzino.aggiungi(this);
    }



    @Override
    public String toString() {
        return "" +
                " dispositivo = '" + device + '\'' +
                ", brand = '" + brand + '\'' +
                ", modello = '" + model + '\'' +
                ", descrizione = '" + description + '\'' +
                ", display = " + display +
                ", memoria = " + storage +
                ", acquisto = " + purchase +
                '}';
    }
}
