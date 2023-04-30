package main.java.gui.controllers.itemController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.be.Customer;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerItemController implements Initializable,Items {
    public Label firstNameLabel, lastNameLabel, emailLabel, adress1Label;
    private MainModel model ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        try {
            this.model.loadCustomers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /*
        firstNameLabel.setText(model.getAllCustomers().get(0).getFirstName());
        lastNameLabel.setText(model.getAllCustomers().get(0).getLastName());
        emailLabel.setText(model.getAllCustomers().get(0).getEmail());
        adress1Label.setText(model.getAllCustomers().get(0).getAddress());

         */

    }
    public void setLabels(int numberOfCustomer){
        firstNameLabel.setText(this.model.getAllCustomers().get(numberOfCustomer ).getFirstName());
        lastNameLabel.setText(this.model.getAllCustomers().get(numberOfCustomer).getLastName());
        emailLabel.setText(this.model.getAllCustomers().get(numberOfCustomer ).getEmail());
        adress1Label.setText(this.model.getAllCustomers().get(numberOfCustomer ).getAddress());
    }
}
