package main.java.gui.controllers.editController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CustomerEditController implements Initializable {
    @FXML
    public TextField firstName, lastName, phoneNum, email, address1, address2;
    @FXML
    public Button cancelBtn , saveBtn;
    // private MainModel model;
    private EditModel model;
    private int selectedCustomerConsNum;
    private int id;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.checkData();
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
        this.id = mvm.getSelectedCustomer().getId();
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

    public void handleSave(ActionEvent actionEvent) throws SQLException {
        Customer editedCustomer = new Customer(firstName.getText(), lastName.getText(), email.getText(), address1.getText(),
                address2.getText(), phoneNum.getText(), selectedCustomerConsNum );
        System.out.println(id);
        model.updateDatabaseElement(editedCustomer, "Customer",this.id );

        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }


    public void checkData(){
        Pattern name = Pattern.compile("[A-Za-z\\s]{1,}");
        TextFormatter<String> formatter = new TextFormatter<>( change -> {
            System.out.println(change.getControlNewText().matches("[A-Za-z\\s]{1,}"));
            if (change.getControlNewText().matches(name.pattern())) {
                System.out.println("work");
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        TextFormatter<String> formatter1 = new TextFormatter<>(change -> {
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        TextFormatter<String> formatter2 = new TextFormatter<>(change -> {
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern mail = Pattern.compile("[A-Za-z1-9]{1,}@[A-Za-z1-9].{1,}");
        TextFormatter<String> formatterMail = new TextFormatter<>(change -> {
            if (mail.matcher(change.getControlNewText()).matches()) {
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern phone = Pattern.compile("\\+?\\d[\\d-\\s]{1,}");
        TextFormatter<String> formatterPhone = new TextFormatter<>(change -> {
            if (phone.matcher(change.getControlNewText()).matches()) {
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern address = Pattern.compile("[A-Za-z0-9\\s,.]+");
        TextFormatter<String> formatterAddress = new TextFormatter<>(change -> {
            if (address.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern pass = Pattern.compile("[A-Za-z\\s1-9\\s]{1,}");
        TextFormatter<String> formatterPass = new TextFormatter<>(change -> {
            if (pass.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });


        email.setTextFormatter(formatterMail);
        firstName.setTextFormatter(formatter);
        lastName.setTextFormatter(formatter1);
        this.phoneNum.setTextFormatter(formatterPhone);
        this.address1.setTextFormatter(formatterAddress);
        this.address2.setTextFormatter(formatter2);
        this.email.setTextFormatter(formatterMail);



    }

}
