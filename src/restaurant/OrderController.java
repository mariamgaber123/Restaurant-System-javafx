package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;


import java.net.URL;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private ComboBox<String> comboProductID;

    @FXML
    private ComboBox<String> comboProductName;

    @FXML
    private Spinner<Integer> spinnerQuantity;

    @FXML
    private TableView<OrderItem> tableView;

    @FXML
    private TableColumn<OrderItem, String> colID;

    @FXML
    private TableColumn<OrderItem, String> colName;

    @FXML
    private TableColumn<OrderItem, String> colType;

    @FXML
    private TableColumn<OrderItem, Integer> colQuantity;

    @FXML
    private TableColumn<OrderItem, Double> colPrice;

    @FXML
    private TextField textAmount;

    @FXML
    private Label totalLabel;

    @FXML
    private Label balanceLabel;

    private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        spinnerQuantity.setValueFactory(valueFactory);
        spinnerQuantity.setEditable(true);

        colID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type")); // تم التعديل هنا
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.setItems(orderItems);

        loadProductIDs();
        comboProductID.setOnAction(e -> loadProductNames());
    }

    private void loadProductIDs() {
        try {
            connect = DBConnection.connectDb();
            String sql = "SELECT Category_ID FROM category";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList<String> ids = FXCollections.observableArrayList();
            while (result.next()) {
                ids.add(String.valueOf(result.getInt("Category_ID")));
            }
            comboProductID.setItems(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadProductNames() {
        try {
            connect = DBConnection.connectDb();
            String id = comboProductID.getValue();
            if (id == null) return;

            String sql = "SELECT Category_Name FROM category WHERE Category_ID = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, Integer.parseInt(id));
            result = prepare.executeQuery();

            ObservableList<String> names = FXCollections.observableArrayList();
            while (result.next()) {
                names.add(result.getString("Category_Name"));
            }
            comboProductName.setItems(names);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addItem() {
        String id = comboProductID.getValue();
        String name = comboProductName.getValue();
        int quantity = spinnerQuantity.getValue();

        if (id == null || name == null) {
            showAlert("Please select product ID and name");
            return;
        }

        double price = fetchPrice(id);
        if (price <= 0) return;

        String type = "Food"; 
        orderItems.add(new OrderItem(id, name, quantity, price, type));

        display();
    }

    private double fetchPrice(String productId) {
        double price = 0;
        try {
            connect = DBConnection.connectDb();
            String sql = "SELECT Price FROM menu_item WHERE Item_ID = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, Integer.parseInt(productId));
            result = prepare.executeQuery();
            if (result.next()) {
                price = result.getDouble("Price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    @FXML
    private void removeItem() {
        OrderItem selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            orderItems.remove(selected);
            display();
        } else {
            showAlert("Select item to remove");
        }
    }

    @FXML
    private void pay() {
        if (orderItems.isEmpty()) {
            showAlert("No items to pay");
            return;
        }

        double total = orderItems.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        double amountPaid = 0;

        try {
            amountPaid = Double.parseDouble(textAmount.getText());
        } catch (Exception e) {
            showAlert("Enter a valid amount");
            return;
        }

        if (amountPaid < total) {
            showAlert("Amount is less than total!");
            return;
        }

        display();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Payment successful!");
        alert.showAndWait();

        orderItems.clear();
        display();
    }

    @FXML
    private void receipt() {
        if (orderItems.isEmpty()) {
            showAlert("No items to print receipt");
            return;
        }

        StringBuilder receipt = new StringBuilder("***** Receipt *****\n");
        orderItems.forEach(item ->
                receipt.append(item.getProductName())
                        .append(" x").append(item.getQuantity())
                        .append(" = $").append(item.getPrice() * item.getQuantity())
                        .append("\n")
        );
        receipt.append("Total: $").append(textAmount.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION, receipt.toString());
        alert.setTitle("Receipt");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void display() {
        double total = orderItems.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        totalLabel.setText("Total: $" + String.format("%.2f", total));
        textAmount.setText(String.format("%.2f", total));

        double paid = 0;
        try {
            paid = Double.parseDouble(textAmount.getText());
        } catch (NumberFormatException e) {
            paid = 0;
        }
        double balance = paid - total;
        balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK).showAndWait();
    }
}
