package controller;

import com.google.inject.Inject;
import dto.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.custom.BookService;
import util.ServiceType;

public class BookFormController {

    @FXML
    private TableColumn colGenre;

    @FXML
    private TableColumn colISBN;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colStatus;

    @FXML
    private TableColumn colTitle;

    @FXML
    private TableView tbIBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtId;

    @Inject
    BookService service;
//  ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    @FXML
    void btnAddBookOnAction(ActionEvent event) {
        String txtIdText = txtId.getText();
        String txtISBNText = txtISBN.getText();
        String txtTitleText = txtTitle.getText();
        String txtAuthorText = txtAuthor.getText();
        String txtGenreText = txtGenre.getText();

        if (txtIdText.isEmpty() || txtISBNText.isEmpty() || txtTitleText.isEmpty() || txtAuthorText.isEmpty() || txtGenreText.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "ALL FIELD MUST BE FILLED OUT").show();
        } else {
            Book book = new Book(txtIdText, txtISBNText, txtTitleText, txtAuthorText, txtGenreText);
            boolean isBookAdded = service.addBook(book);
            if (isBookAdded) {
                new Alert(Alert.AlertType.INFORMATION, "BOOK ADDED").show();
                clearText();
            } else {
                new Alert(Alert.AlertType.ERROR, "BOOK NOT ADDED").show();
                clearText();
            }
        }

    }

    @FXML
    void btnSearchBookOnAction(ActionEvent event) {
        service.searchBook(txtId.getText());

    }

    @FXML
    void btnUpdateBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteBookOnAction(ActionEvent event) {
        if (service.deleteBook(txtId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "BOOK DELETED SUCCESSFULLY").show();
            clearText();
        } else {
                new Alert(Alert.AlertType.ERROR, "FAILED TO DELETE BOOK. PLEASE TRY AGAIN").show();
                clearText();
            }

    }

    @FXML
    void btnReloadBooksOnAction(ActionEvent event) {

    }

    private void clearText() {
        txtId.clear();
        txtISBN.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtGenre.clear();

    }

}
