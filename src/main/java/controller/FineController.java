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
import service.custom.ReturnService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FineController {
    @Inject
    ReturnService returnService;

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
                //Double textDouble = Double.valueOf(txtPayAmount.getText());

                Fine fine = new Fine(txtBorrowId.getText(), txtBorrowDate.getText(), dewDate.toString(), returnDate.toString(), lateDayFeeInt, 100.0 );

                System.out.println(fine);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Not Fee").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "NO BORROW ID FOUND. PLEASE CHECK AND TRY AGAIN").show();
        }

    }

    @FXML
    void btnConfirmPayAmountOnAction(ActionEvent event) {
        String borrowIdText = txtBorrowId.getText();
        String txtMemberIdText = txtMemberId.getText();
        String txtBookIdText = cmbBooksId.getValue().toString();
        String txtBorrowDateText = txtBorrowDate.getText();

    }


}
