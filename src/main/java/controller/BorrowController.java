package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Book;
import dto.Borrow;
import dto.CartTM;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.BookService;
import service.custom.BorrowService;
import service.custom.MemberService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import static org.hibernate.internal.util.StringHelper.count;

public class BorrowController implements Initializable {
    public TableView tbCart;
    public TableColumn colBorrowId;
    public TableColumn colMemberId;
    public TableColumn colBookId;
    public TableColumn colBorrowDate;
    public TableColumn colReturnDate;

    @Inject
    MemberService service;

    @Inject
    BookService bookService;

    @Inject
    BorrowService borrowService;

    public JFXTextField orderId;

    @FXML
    private DatePicker borrowDate;

    @FXML
    private JFXComboBox cmbBooksId;

    @FXML
    private JFXComboBox cmbMembersId;

    @FXML
    private DatePicker returnDate;

    public ObservableList<String> setMembersId() {
        ObservableList<String> membersId = FXCollections.observableArrayList();
        List<Member> list = service.getAllMembers();

        list.forEach(id -> {
            membersId.add(id.getId());
        });
        return membersId;
    }

    public ObservableList<String> setBooksId() {
        ObservableList<String> booksId = FXCollections.observableArrayList();
        List<Book> all = bookService.getAll();

        all.forEach(bookId -> {
            booksId.add(bookId.getId());
        });
        return booksId;
    }

    public void loadMembersId() {
        cmbMembersId.setItems(setMembersId());
    }

    public void loadBooksId() {
        cmbBooksId.setItems(setBooksId());
    }

    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    @FXML
    void bntAddToListOnAction(ActionEvent event) {
        String borrowIdText = orderId.getText();
        String memberId = cmbMembersId.getValue().toString();
        String bookId = cmbBooksId.getValue().toString();
        String borrowDay = borrowDate.getValue().toString();
        String returnDay  = returnDate.getValue().toString();

        cartTMS.add(new CartTM(borrowIdText, memberId, bookId, borrowDay, returnDay));
        tbCart.setItems(cartTMS);
    }

    @FXML
    void btnConfirmBorrowingOnAction(ActionEvent event) {
        String orderIdText = orderId.getText();

        cartTMS.forEach(cartTM -> {
            Borrow borrow = new Borrow(
                    orderIdText,
                    cartTM.getMemberId(),
                    cartTM.getBookId(),
                    cartTM.getBorrowDate(),
                    cartTM.getReturnDate()
            );
            boolean placeBorrowOrder = borrowService.placeBorrowOrder(borrow);

            if(placeBorrowOrder) {
                new Alert(Alert.AlertType.INFORMATION, "ORDER ADDED").show();
                clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "ORDER NOT ADDED").show();
            }


        });

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colBorrowId.setCellValueFactory(new PropertyValueFactory<>("borrowId"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        loadMembersId();
        loadBooksId();
    }

    public void clear() {
        orderId.clear();
        cmbMembersId.setValue(null);
        cmbBooksId.setValue(null);
        borrowDate.setValue(null);
        returnDate.setValue(null);
    }

}
