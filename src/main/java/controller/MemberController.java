package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXTextField;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.custom.MemberService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MemberController {

    @FXML
    private TableColumn colContactNumber;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colMembershipDate;

    @FXML
    private TableColumn colName;

    @FXML
    private TableView tableMember;

    @FXML
    private JFXTextField txtContactInfo;

    @FXML
    private JFXTextField txtId;

    @FXML
    private DatePicker membershipDate;

    @FXML
    private JFXTextField txtName;

    @Inject
    MemberService service;

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {
        String txtIdText = txtId.getText();
        String txtNameText = txtName.getText();
        String txtContactInfoText = txtContactInfo.getText();
        String dateString = membershipDate.getValue().toString();

        if (txtIdText.isEmpty() || txtNameText.isEmpty() || txtContactInfoText.isEmpty() || dateString.isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, "ALL FIELD MUST BE FILLED OUT").show();
        } else {
            Member member = new Member(txtIdText, txtNameText, txtContactInfoText, dateString);

            boolean addMember = service.addMember(member);

            if (addMember) {
                new Alert(Alert.AlertType.INFORMATION, "MEMBER ADDED").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "BOOK NOT ADDED").show();
            }
        }
    }

    @FXML
    void btnSearchMemberOnAction(ActionEvent event) {
        Member member = service.searchMember(txtId.getText());

        if (member!=null) {
            txtId.setText(member.getId());
            txtName.setText(member.getName());
            txtContactInfo.setText(member.getContactInfo());
        }
    }

    @FXML
    void btnDeleteMemberOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateMemberOnAction(ActionEvent event) {

    }

}
