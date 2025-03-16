package controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import config.AppModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import service.custom.BookService;

import java.io.IOException;

public class DashboardController {
    @Inject
    BookService service;

    @FXML
    private AnchorPane loadFormContent;

    @FXML
    void btnBookManagementOnAction(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/book-form.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(loader.load());
    }

    @FXML
    void btnMemberManagementOnAction(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/member-form.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(loader.load());
    }

    @FXML
    void btnborrowBooksOnAction(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/borrow-form.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(loader.load());
    }

    public void btnReturnBooksOnAction(ActionEvent actionEvent) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/return-form.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(loader.load());
    }

    public void btnFineCalculatorOnAction(ActionEvent actionEvent) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/fine-form.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(loader.load());
    }

    public void btnRegisterNewUserAction(ActionEvent actionEvent) {
    }
}
