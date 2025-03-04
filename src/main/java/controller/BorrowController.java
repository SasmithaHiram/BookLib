package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Book;
import dto.Borrow;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import service.custom.BookService;
import service.custom.BorrowService;
import service.custom.MemberService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowController implements Initializable {
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

    @FXML
    void bntAddToListOnAction(ActionEvent event) {
        String borrowIdText = orderId.getText();
        String memberId = cmbMembersId.getValue().toString();
        String bookId = cmbBooksId.getValue().toString();
        String borrowDay = borrowDate.getValue().toString();
        String returnDay  = returnDate.getValue().toString();

        Borrow borrow = new Borrow(borrowIdText, memberId, bookId, borrowDay, returnDay);
        borrowService.placeBorrowOrder(borrow);
    }

    @FXML
    void btnConfirmBorrowingOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMembersId();
        loadBooksId();
    }

}
