package controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import config.AppModule;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;
import service.custom.UserService;

import java.io.IOException;

public class LoginController {
    @Inject
    UserService service;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnLoginAction(ActionEvent event) throws IOException {
        User user = service.searchByEmail(txtEmail.getText());

        if (txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "ALL FIELD MUST BE FILLED OUT").show();
        } else if (txtEmail.getText().equals(user.getEmail()) && txtPassword.getText().equals(user.getPassword())) {
            Stage stage = new Stage();
            Injector injector = Guice.createInjector(new AppModule());

            FXMLLoader loader = new FXMLLoader((getClass().getResource("/view/dashboard-form.fxml")));
            loader.setControllerFactory(injector::getInstance);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            txtEmail.clear();
            txtPassword.clear();
        }else {
            new Alert(Alert.AlertType.ERROR, "INVALID PASSWORD", ButtonType.OK).show();
        }
    }

    @FXML
    void hyperRegisterAction(ActionEvent event) throws IOException {
        txtEmail.clear();
        txtPassword.clear();

        Stage stage = new Stage();
        Injector injector = Guice.createInjector(new AppModule());

        FXMLLoader loader = new FXMLLoader((getClass().getResource("/view/register-form.fxml")));
        loader.setControllerFactory(injector::getInstance);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
