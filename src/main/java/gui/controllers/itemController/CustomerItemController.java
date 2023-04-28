package main.java.gui.controllers.itemController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.be.Customer;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerItemController implements Initializable {
    public Label firstNameLabel, lastNameLabel, emailLabel, adress1Label;
    private MainModel model ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new MainModel();
        try {
            model.loadInfo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public void setLabels(int numberOfCustomer){
        firstNameLabel.setText(model.getAllCustomers().get(numberOfCustomer).getFirstName());
        lastNameLabel.setText(model.getAllCustomers().get(numberOfCustomer).getLastName());
        emailLabel.setText(model.getAllCustomers().get(numberOfCustomer).getEmail());
        adress1Label.setText(model.getAllCustomers().get(numberOfCustomer).getAddress());
    }
}
