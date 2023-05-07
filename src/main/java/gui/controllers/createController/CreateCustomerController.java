package main.java.gui.controllers.createController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.be.User;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateCustomerController implements Initializable {
    public TextField firstName , lastName, email, phoneNum, address1, address2;
    public Button saveBtn, cancelBtn;


    private CreateModel createModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setModel(MainModel model){
        this.createModel = new CreateModel(model);
    }

    public void handleSave(ActionEvent actionEvent) {
        Customer newCustomer = new Customer(firstName.getText(), lastName.getText(),email.getText(),address1.getText()
                , address2.getText(),phoneNum.getText(), 1);
        createModel.createInDatabase(newCustomer, "Customer");

        Stage currentStage = (Stage) saveBtn.getScene().getWindow();
        currentStage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage currentStage = (Stage) cancelBtn.getScene().getWindow();
        currentStage.close();
    }
}
