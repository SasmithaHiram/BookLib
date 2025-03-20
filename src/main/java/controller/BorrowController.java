package controller;

import com.google.inject.Inject;
import com.google.protobuf.Value;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import javafx.beans.value.ObservableValue;
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
import service.impl.BookServiceImpl;
import service.impl.BorrowServiceImpl;
import service.impl.MemberServiceImpl;
import util.BookStatus;
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
    public JFXTextField txtMemberName;
    public JFXTextField txtBookName;
    public JFXTextField txtStatus;

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

    public void setMemberDetails() {
        ObservableList<String> memberObservableList = FXCollections.observableArrayList();

        service.getAllMembers().forEach(members -> {
            memberObservableList.add(members.getId());
        });
        cmbMembersId.setItems(memberObservableList);
    }

    public void setBookDetails() {
        ObservableList<String> bookObservableList = FXCollections.observableArrayList();

        bookService.getAll().forEach(books -> {
            bookObservableList.add(books.getId());
        });

        cmbBooksId.setItems(bookObservableList);

    }

    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    @FXML
    void bntAddToListOnAction(ActionEvent event) {
        String bookId = cmbBooksId.getValue().toString();
        String borrowDay = borrowDate.getValue().toString();
        String dewDay = dewDate.getValue().toString();

        if (txtStatus.getText().toUpperCase().equals(BookStatus.AVAILABLE.toString())) {
            if (cartTMS.size() < 1) {
                cartTMS.add(new CartTM(bookId, borrowDay, dewDay));
                tbCart.setItems(cartTMS);
                addToCart();
            } else {
                new Alert(Alert.AlertType.WARNING, "BORROW LIMIT EXCEEDED").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "THIS BOOK IS CURRENTLY NOT AVAILABLE").show();
        }


    }

    public boolean placeBorrow() {
        String orderIdText = orderId.getText();
        String memberId = cmbMembersId.getValue().toString();
        String borrowDay = borrowDate.getValue().toString();
        String dewDay = dewDate.getValue().toString();

        List<BorrowDetails> borrowDetails = new ArrayList<>();

        cartTMS.forEach(cartTM -> {
            borrowDetails.add(
                    new BorrowDetails(
                            orderIdText,
                            cartTM.getBookId(),
                            cartTM.getBorrowDate(),
                            null,
                            BorrowStatus.BORROWED
                    )
            );
        });

        Borrow borrow = new Borrow(orderIdText, memberId, borrowDay, dewDay, BorrowStatus.BORROWED, borrowDetails);

        boolean placeBorrowOrder = borrowService.placeBorrowOrder(borrow);

        if (placeBorrowOrder) {
            boolean isAddBorrowDetail = new BorrowDetailController().addBorrowDetail(borrow.getBorrowedBooks());

            if (isAddBorrowDetail) {
                boolean updateAvailability = new BookServiceImpl().updateAvailability(borrow.getBorrowedBooks());

                if (updateAvailability) {
                    return true;
                }
            }
        }
        return placeBorrowOrder;

    }

    @FXML
    void btnConfirmBorrowingOnAction(ActionEvent event) {
        boolean isBorrow = placeBorrow();

        if (isBorrow) {
            new Alert(Alert.AlertType.INFORMATION, "BOOK ISSUED SUCCESSFULLY", ButtonType.OK).show();
            clear();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "UNABLE TO COMPLETE YOUR REQUEST. PLEASE TRY AGAIN", ButtonType.OK).show();
        }

    }

    public void addToCart() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("dewDate"));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMemberDetails();
        setBookDetails();

        cmbMembersId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchMemberDetails(newValue.toString());
            }
        });

        cmbBooksId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchBookDetails(newValue.toString());
            }
        });
    }

    private void searchMemberDetails(String string) {
        Member member = service.searchMember(string);
        txtMemberName.setText(member.getName());
    }

    private void searchBookDetails(String string) {
        Book book = bookService.searchBook(string);
        txtBookName.setText(book.getTitle());
        txtStatus.setText(book.getAvailability());
    }


    public void clear() {
        orderId.clear();
        cmbMembersId.setValue(null);
        cmbBooksId.setValue(null);
        txtBookName.clear();
        borrowDate.setValue(null);
        dewDate.setValue(null);
    }

}
