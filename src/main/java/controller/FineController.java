package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Borrow;
import dto.Fine;
import dto.Return;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import service.custom.FineService;
import service.custom.ReturnService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FineController {
    @Inject
    ReturnService returnService;

    @Inject
    FineService fineService;

    @FXML
    private JFXComboBox cmbBooksId;

    @FXML
    private JFXTextField txtBorrowDate;

    @FXML
    private JFXTextField txtBorrowId;

    @FXML
    private JFXTextField txtDewDate;

    @FXML
    private JFXTextField txtMemberId;

    public JFXTextField txtReturnDate;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");

    final Integer feePerDay = 50;

    public Label lateFee;

    public JFXTextField txtPayAmount;

    @FXML
    void btnSearchBorrowIdOnAction(ActionEvent event) {
        Return searched = returnService.search(txtBorrowId.getText());

        if (searched != null) {
            txtMemberId.setText(searched.getMemberId());
            txtBorrowDate.setText(searched.getBorrowDate().toString());
            txtDewDate.setText(searched.getDewDate().toString());

            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.add(searched.getBookId());
            cmbBooksId.setItems(observableList);
            cmbBooksId.getSelectionModel().selectFirst();
            txtReturnDate.setText(searched.getReturnDate().toString());

            String dewDateText = txtDewDate.getText();
            String txtReturnDateText = txtReturnDate.getText();

            LocalDate returnDate = LocalDate.parse(txtReturnDateText, formatter);
            LocalDate dewDate = LocalDate.parse(dewDateText, formatter);

            if (returnDate.isAfter(dewDate)) {
                long lateDays = ChronoUnit.DAYS.between(dewDate, returnDate);
                long lateDayFee = lateDays * feePerDay;
                int lateDayFeeInt = (int) lateDayFee;

                lateFee.setText(String.valueOf(lateDayFeeInt));

            } else {
                new Alert(Alert.AlertType.INFORMATION, "TRY").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "NO BORROW ID FOUND. PLEASE CHECK AND TRY AGAIN").show();
        }

    }

    @FXML
    void btnConfirmPayAmountOnAction(ActionEvent event) {
        String borrowIdText = txtBorrowId.getText();
        String txtBorrowDateText = txtBorrowDate.getText();
        String dewDateText = txtDewDate.getText();
        String txtReturnDateText = txtReturnDate.getText();
        Integer lateFeeText = Integer.valueOf(lateFee.getText());
        Double txtPayAmountText = Double.valueOf(txtPayAmount.getText());

        Fine fine = new Fine(borrowIdText, txtBorrowDateText, dewDateText, txtReturnDateText, lateFeeText, txtPayAmountText);
        boolean isAddFine = fineService.addFine(fine);

        if (isAddFine) {
            new Alert(Alert.AlertType.INFORMATION, "PAYMENT SUCCESSFUL").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "PAYMENT FAILED. PLEASE TRY AGAIN").show();
        }

    }

}
