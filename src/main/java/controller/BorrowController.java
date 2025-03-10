package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.*;
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
import util.BorrowStatus;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    private DatePicker dewDate;

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
        String memberId = cmbMembersId.getValue().toString();
        String bookId = cmbBooksId.getValue().toString();
        String borrowDay = borrowDate.getValue().toString();
        String dewDay = dewDate.getValue().toString();

        cartTMS.add(new CartTM(memberId, bookId, borrowDay, dewDay));
        tbCart.setItems(cartTMS);
    }

    public void placeBorrow() {
        String orderIdText = orderId.getText();
        String memberId = cmbMembersId.getValue().toString();
        String bookId = cmbBooksId.getValue().toString();
        String borrowDay = borrowDate.getValue().toString();
        String dewDay = dewDate.getValue().toString();

        List<BorrowDetail> borrowDetails = new ArrayList<>();

            cartTMS.forEach(cartTM -> {
                borrowDetails.add(
                        new BorrowDetail(orderIdText, cartTM.getMemberId(), cartTM.getBookId())
                );
            });


        Borrow borrow = new Borrow(borrowDetails, borrowDay, dewDay, BorrowStatus.BORROWED);
borrowService.placeBorrowOrder(borrow);

    }

    @FXML
    void btnConfirmBorrowingOnAction(ActionEvent event) {
       placeBorrow();

//            boolean placeBorrowOrder = borrowService.placeBorrowOrder();
//
//            if(placeBorrowOrder) {
//                new Alert(Alert.AlertType.INFORMATION, "ORDER ADDED").show();
//                clear();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "ORDER NOT ADDED").show();
//            }
//
//
//        });

    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("dewDate"));

        loadMembersId();
        loadBooksId();
    }

    public void clear() {
        orderId.clear();
        cmbMembersId.setValue(null);
        cmbBooksId.setValue(null);
        borrowDate.setValue(null);
        dewDate.setValue(null);
    }

}
