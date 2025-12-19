package restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OrderPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Order.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("order.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Order Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
