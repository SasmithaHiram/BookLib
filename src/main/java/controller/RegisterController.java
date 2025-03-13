package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import service.custom.UserService;

public class RegisterController {
    @Inject
    UserService service;


    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    void btnRegisterCustomer(ActionEvent event) {
        if (txtEmail.getText().isEmpty() || txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty() || txtConfirmPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "ALL FIELD MUST BE FILLED OUT").show();

        } else if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
            User user = new User(txtUsername.getText(), txtEmail.getText(), txtPassword.getText());
            boolean saved = service.saveUser(user);

            if (saved) {
                new Alert(Alert.AlertType.INFORMATION, "USER ADDED").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "PASSWORDS DO NOT MATCH").showAndWait();
        }
    }

}


