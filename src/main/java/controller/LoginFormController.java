package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.jasypt.util.text.BasicTextEncryptor;
import service.custom.UserService;

public class LoginFormController {
    @Inject
    UserService service;

    @FXML
    private JFXTextField txtLEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnLoginAction(ActionEvent event) {
    }

    @FXML
    void hyperRegisterAction(ActionEvent event) {

    }

}
