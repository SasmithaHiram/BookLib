package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Borrow;
import dto.BorrowDetails;
import dto.Return;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.custom.BorrowService;
import service.custom.ReturnService;
import service.impl.BookServiceImpl;
import util.BorrowStatus;

import java.util.ArrayList;
import java.util.List;

public class ReturnController {
    @Inject
    ReturnService returnService;
    @Inject
    BorrowService borrowService;

    @FXML
    private JFXComboBox cmbBooksId;

    @FXML
    private JFXComboBox cmbReturnBookId;

    @FXML
    private TableColumn colBookId;

    @FXML
    private TableColumn colBorrowDate;

    @FXML
    private TableColumn colBorrowId;

    @FXML
    private TableColumn colMemberId;

    @FXML
    private TableColumn colReturnDate;

    @FXML
    private DatePicker returnDate;

    @FXML
    private TableView tbCart;

    @FXML
    private JFXTextField txtBorrowId;

    @FXML
    private JFXTextField txtMemberId;

    public JFXTextField txtBorrowDate;

    public JFXTextField txtDewDate;

    @FXML
    void bntAddToListOnAction(ActionEvent event) {

    }

    @FXML
    boolean btnConfirmBorrowingOnAction(ActionEvent event) {
        String borrowIdText = txtBorrowId.getText();
        String txtMemberIdText = txtMemberId.getText();
        String txtBookIdText = cmbBooksId.getValue().toString();
        String txtBorrowDateText = txtBorrowDate.getText();
        String dewDateText = txtDewDate.getText();
        String returnD = returnDate.getValue().toString();

        List<BorrowDetails> borrowDetails = new ArrayList<>();

        borrowDetails.add(
                new BorrowDetails(
                        borrowIdText,
                        txtBookIdText,
                        txtBorrowDateText,
                        returnD
                )
        );

        Borrow borrow = new Borrow(borrowIdText, txtMemberIdText, txtBorrowDateText, dewDateText, BorrowStatus.RETURNED, borrowDetails);
        borrowService.UpdateBorrowOrder(borrow);


            boolean updateBorrowDetail = new BorrowDetailController().updateBorrowDetail(borrow.getBorrowedBooks());


            return updateBorrowDetail;


    }

    @FXML
    void btnSearchBorrowIdOnAction(ActionEvent event) {
        Return searchBorrow = returnService.search(txtBorrowId.getText());

        if (searchBorrow!= null) {
            txtMemberId.setText(searchBorrow.getMemberId());
            txtBorrowDate.setText(searchBorrow.getBorrowDate().toString());
            txtDewDate.setText(searchBorrow.getDewDate().toString());

            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.add(searchBorrow.getBookId());
            cmbBooksId.setItems(observableList);
            cmbBooksId.getSelectionModel().selectFirst();
        }

    }

}

