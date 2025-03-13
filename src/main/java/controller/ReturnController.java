package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReturnController {

    @FXML
    private JFXComboBox<?> cmbBooksId;

    @FXML
    private JFXComboBox<?> cmbMembersId;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colBorrowId;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private DatePicker dewDate;

    @FXML
    private JFXTextField orderId;

    @FXML
    private TableView<?> tbCart;

    @FXML
    void bntAddToListOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmBorrowingOnAction(ActionEvent event) {

    }

}
