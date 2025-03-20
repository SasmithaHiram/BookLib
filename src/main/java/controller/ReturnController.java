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
import javafx.scene.control.cell.PropertyValueFactory;
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

    ObservableList<Borrow> observableList = FXCollections.observableArrayList();


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
                        returnD,
                        BorrowStatus.RETURNED
                )
        );

        Borrow borrow = new Borrow(borrowIdText, txtMemberIdText, txtBorrowDateText, dewDateText, BorrowStatus.RETURNED, borrowDetails);
        borrowService.UpdateBorrowOrder(borrow);

        boolean updateBorrowDetail = new BorrowDetailController().updateBorrowDetail(borrow.getBorrowedBooks());

        if (updateBorrowDetail) {
            new Alert(Alert.AlertType.INFORMATION, "RETURNED SUCCESSFULLY").show();
            clear();
        } else {
            new Alert(Alert.AlertType.ERROR, "THIS BOOK HAS ALREADY BEEN RETURNED").show();
        }

        return updateBorrowDetail;
    }

    @FXML
    void btnSearchBorrowIdOnAction(ActionEvent event) {
        Return searchBorrow = returnService.search(txtBorrowId.getText());

        if (searchBorrow != null) {
            txtMemberId.setText(searchBorrow.getMemberId());
            txtBorrowDate.setText(searchBorrow.getBorrowDate().toString());
            txtDewDate.setText(searchBorrow.getDewDate().toString());

            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.add(searchBorrow.getBookId());
            cmbBooksId.getItems().setAll(observableList);
        } else {
            new Alert(Alert.AlertType.INFORMATION, "NO BORROW ID FOUND. PLEASE CHECK AND TRY AGAIN").show();
        }
    }

    public void clear() {
        txtBorrowId.clear();
        txtMemberId.clear();
        cmbBooksId.setValue(null);
        txtBorrowDate.clear();
        txtDewDate.clear();
        returnDate.setValue(null);
    }
}

