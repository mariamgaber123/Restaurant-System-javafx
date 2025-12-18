/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Yasmine Naser
 */
public class dashboardController implements Initializable {
   @FXML
    private Button avaliableFD_Updatebtn;

    @FXML
    private Button avaliableFD_addbtn;

    @FXML
    private Button avaliableFD_clearbtn;

    @FXML
    private TableColumn<?, ?> avaliableFD_coi_productid;

    @FXML
    private TableColumn<?, ?> avaliableFD_coi_productname;

    @FXML
    private TableColumn<?, ?> avaliableFD_coi_productprice;

    @FXML
    private TableColumn<?, ?> avaliableFD_coi_productstatus;

    @FXML
    private TableColumn<?, ?> avaliableFD_coi_producttype;

    @FXML
    private Button avaliableFD_deletebtn;

    @FXML
    private TextField avaliableFD_productID;

    @FXML
    private TextField avaliableFD_productName;

    @FXML
    private TextField avaliableFD_productPrice;

    @FXML
    private ComboBox<?> avaliableFD_productType;

    @FXML
    private ComboBox<?> avaliableFD_productstatus;

    @FXML
    private AnchorPane avalibleFD_form;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
