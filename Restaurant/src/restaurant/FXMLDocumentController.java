/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 *
 * @author hp
 */

public class FXMLDocumentController implements Initializable {

    @FXML
    public Label label;

    @FXML
    public AnchorPane root;

    @FXML
    public ImageView backgroundImage;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ربط الخلفية بحجم الصفحة
        backgroundImage.fitWidthProperty().bind(root.widthProperty());
        backgroundImage.fitHeightProperty().bind(root.heightProperty());
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }


   @FXML
public void gotomembers(ActionEvent event) throws Exception {
            System.out.println("Team Members button clicked!");
    Parent root = FXMLLoader.load(getClass().getResource("members.fxml"));
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.setResizable(false);
    stage.show();
}
    
}