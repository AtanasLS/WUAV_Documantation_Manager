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
import main.java.be.Project;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateProjectController implements Initializable, CreateController{

    public TextField type, price;
    public ComboBox customer;
    public Button saveBtn;
    public Button cancelBtn;

    private CreateModel createModel;
    private MainModel model;
    private ObservableList<Customer> customers;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model=new MainModel();
        this.customers = FXCollections.observableArrayList();
        try {
            model.loadCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.customers.addAll(model.getAllCustomers());
        this.customer.setItems(customers);
    }

   @Override
    public void setModel(MainModel model){
        this.createModel = new CreateModel(model);
    }


    @Override
    public void handleSave(ActionEvent actionEvent) {

        Customer customer1= (Customer) this.customer.getSelectionModel().getSelectedItem();
        Project project= new Project(this.type.getText(),0,customer1.getId());
        this.createModel.createInDatabase(project,"Project");
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }



   @Override
    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }


}