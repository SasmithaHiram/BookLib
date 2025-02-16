package controller;

import com.google.inject.Inject;
import dto.Book;
import entity.BookEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.BookService;

public class BookFormController {

    @FXML
    private TableColumn colGenre;

    @FXML
    private TableColumn colISBN;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colAuthor;

    @FXML
    private TableColumn colTitle;

    @FXML
    private TableView tableBooks;

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
        if (txtId.getText().isEmpty() || txtISBN.getText().isEmpty() || txtTitle.getText().isEmpty() || txtAuthor.getText().isEmpty() || txtGenre.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "ALL FIELD MUST BE FILLED OUT").show();
        } else {
            Book book = new Book(txtId.getText(), txtISBN.getText(), txtTitle.getText(), txtAuthor.getText(), txtGenre.getText());
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
        Book book = service.searchBook(txtId.getText());

        if (book!=null) {
            txtISBN.setText(book.getISBN());
            txtTitle.setText(book.getTitle());
            txtAuthor.setText(book.getAuthor());
            txtGenre.setText(book.getGenre());
        } else {
            new Alert(Alert.AlertType.ERROR, "BOOK NOT FOUND").show();

        }

    }

    @FXML
    void btnUpdateBookOnAction(ActionEvent event) {
        Book book = new Book(txtId.getText(), txtISBN.getText(), txtTitle.getText(), txtAuthor.getText(), txtGenre.getText());
        boolean isBookUpdate = service.updateBook(book);

        if (isBookUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "BOOK UPDATED SUCCESSFULLY").show();
            clearText();
        } else {
            new Alert(Alert.AlertType.ERROR, "FAILED TO UPDATE BOOK").show();
            clearText();

        }

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
        loadTable();
    }

    private void clearText() {
        txtId.clear();
        txtISBN.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtGenre.clear();

    }

    private void loadTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colISBN.setCellValueFactory(new PropertyValueFactory<>("iSBN"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        ObservableList<Book> booksObservable = FXCollections.observableArrayList();

        service.getAll().forEach(book -> {
            booksObservable.add(book);
        });

        tableBooks.setItems(booksObservable);
    }

}
