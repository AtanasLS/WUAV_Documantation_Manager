package main.java.gui.controllers.createController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.java.be.Customer;
import main.java.be.Project;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateProjectController implements Initializable,CreateController {

    public TextField type, price;
    public ComboBox customer;

    private CreateModel createModel;
    private MainModel model;
    private ObservableList<Customer> customers;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model=new MainModel();
        this.customers.addAll(model.getAllCustomers());
    }

    @Override
    public void setModel(MainModel model){
        this.createModel = new CreateModel(model);
    }


    @Override
    public void handleSave(ActionEvent actionEvent) {
        double price= Double.parseDouble(this.price.getText());
        Customer customer1= (Customer) this.customer.getSelectionModel().getSelectedItem();

        Project project= new Project(this.type.getText(),price,customer1.getFirstName(),customer1.getId());

        this.createModel.createInDatabase(project,"project");
    }

    @Override
    public void handleCancel(ActionEvent actionEvent) {

    }
}
