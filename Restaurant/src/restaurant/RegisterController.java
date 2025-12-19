package restaurant.management.system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterController {

    @FXML
    private AnchorPane registerForm;

    @FXML
    private TextField regUsername;

    @FXML
    private PasswordField regPassword;

    @FXML
    private Button registerBtn;

    @FXML
    private Hyperlink loginLink;

    // دالة لإغلاق البرنامج
    @FXML
    private void close() {
        System.exit(0);
    }

    // فتح صفحة الـ Login من صفحة الـ Register
    @FXML
    private void openLoginForm() {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            registerForm.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // دالة تسجيل المستخدم وربطه بالـ DB
    @FXML
    private void registerUser() {
        String username = regUsername.getText();
        String password = regPassword.getText();

        if(username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Form Error!", "Please enter username and password");
            return;
        }

        Connection connect = null;
        PreparedStatement prepare = null;

        try {
            connect = database.connectDb();
            String sql = "INSERT INTO admin(username, password) VALUES(?, ?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);
            prepare.setString(2, password);
            int rows = prepare.executeUpdate();

            if(rows > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Registration Successful!", "User registered: " + username);
                // بعد ما المستخدم يشوف الرسالة نرجع للـ Login
                openLoginForm();
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error: " + e.getMessage());
        } finally {
            try {
                if(prepare != null) prepare.close();
                if(connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // دالة لإظهار الرسائل
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
