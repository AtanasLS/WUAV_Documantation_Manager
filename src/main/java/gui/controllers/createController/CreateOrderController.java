package main.java.gui.controllers.createController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.java.be.Customer;
import main.java.be.Order;
import main.java.be.Project;
import main.java.be.User;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateOrderController implements Initializable,CreateController {
    public TextField name, price;
    public ComboBox customer,project,user;
    public DatePicker date;

    private CreateModel createModel;
    private MainModel model;
    private ObservableList<Customer> customers;
    private ObservableList<Project> projects;
    private ObservableList<User> users;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model=new MainModel();
        this.customers.addAll(model.getAllCustomers());
        this.projects.addAll(model.getAllProjects());
        this.users.addAll(model.getAllUsers());
    }

    @Override
    public void setModel(MainModel model){
        this.createModel = new CreateModel(model);
    }



    @Override
    public void handleSave(ActionEvent actionEvent) {
        User user1= (User) this.user.getSelectionModel().getSelectedItem();
        Project project1= (Project) this.project.getSelectionModel().getSelectedItem();
        Customer customer1= (Customer) this.customer.getSelectionModel().getSelectedItem();
        double price= Double.parseDouble(this.price.getText());

        //Order order=new Order(user1.getId(),project1.getProjectId(),this.name.getText(),user1.getUsername(),project1.getType(),customer1.getFirstName(),customer1.getId(),this.date.getValue(),price);

    }

    @Override
    public void handleCancel(ActionEvent actionEvent) {

    }
}
