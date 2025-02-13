package controller.book;

import dto.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.ServiceFactory;
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

    BookService service = ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    @FXML
    void btnAddBookOnAction(ActionEvent event) {
        String txtIdText = txtId.getText();
        String txtISBNText = txtISBN.getText();
        String txtTitleText = txtTitle.getText();
        String txtAuthorText = txtAuthor.getText();
        String txtGenreText = txtGenre.getText();

        Book book = new Book(txtIdText, txtISBNText, txtTitleText, txtAuthorText, txtGenreText);
        service.addBook(book);

    }

    @FXML
    void btnDeleteBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateBookOnAction(ActionEvent event) {

    }

}
