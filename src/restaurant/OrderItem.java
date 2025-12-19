package restaurant;

import javafx.beans.property.*;

public class OrderItem {
    private StringProperty productID;
    private StringProperty productName;
    private IntegerProperty quantity;
    private DoubleProperty price;
    private StringProperty type; 

    public OrderItem(String id, String name, int qty, double price, String type) {
        this.productID = new SimpleStringProperty(id);
        this.productName = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(qty);
        this.price = new SimpleDoubleProperty(price);
        this.type = new SimpleStringProperty(type);
    }

    public StringProperty productIDProperty() { return productID; }
    public StringProperty productNameProperty() { return productName; }
    public IntegerProperty quantityProperty() { return quantity; }
    public DoubleProperty priceProperty() { return price; }
    public StringProperty typeProperty() { return type; }

    public String getProductID() { return productID.get(); }
    public String getProductName() { return productName.get(); }
    public int getQuantity() { return quantity.get(); }
    public double getPrice() { return price.get(); }
    public String getType() { return type.get(); }
}
