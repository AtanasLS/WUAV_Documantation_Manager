package main.java.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerEditController implements Initializable {
    @FXML
    public TextField firstName, lastName, phoneNum, email, address1, address2;
    @FXML
    public Button cancelBtn , saveBtn;
    // private MainModel model;
    private EditModel model;
    private int selectedCustomerConsNum;
    private String id;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMainModel(MainModel mvm){
        model = new EditModel(mvm);
        firstName.setText(mvm.getSelectedCustomer().getFirstName());
        lastName.setText(mvm.getSelectedCustomer().getLastName());
        phoneNum.setText(mvm.getSelectedCustomer().getPhone());
        email.setText(mvm.getSelectedCustomer().getEmail());
        address1.setText(mvm.getSelectedCustomer().getAddress());
        address2.setText(mvm.getSelectedCustomer().getAddress2());
        selectedCustomerConsNum = mvm.getSelectedCustomer().getConsumptionNumber();
        this.id = mvm.getSelectedCustomer().getEmail();
    }

    public void setInformation(){
        /*
        firstName.setText(model.getSelectedCustomer().getFirstName());
        lastName.setText(model.getSelectedCustomer().getLastName());
        phoneNum.setText(model.getSelectedCustomer().getPhone());
        email.setText(model.getSelectedCustomer().getEmail());
        address1.setText(model.getSelectedCustomer().getAddress());
        address2.setText(model.getSelectedCustomer().getAddress2());

         */
    }

    public void handleSave(ActionEvent actionEvent) {
        Customer editedCustomer = new Customer(firstName.getText(), lastName.getText(), email.getText(), address1.getText(),
                address2.getText(), phoneNum.getText(), selectedCustomerConsNum );
        System.out.println(id);
        model.updateDatabaseElement(editedCustomer, this.id ,"Customer");

        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
