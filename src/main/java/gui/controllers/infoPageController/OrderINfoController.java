package main.java.gui.controllers.infoPageController;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.be.Customer;
import main.java.be.Order;
import main.java.be.Project;
import main.java.be.User;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class OrderINfoController implements Initializable,Info
{


    public TextField nameField;
    public ComboBox project,customer,user;
    public DatePicker date;


    private MainModel model;
    private DeleteModel deleteModel;
    private EditModel editModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @Override
    public void setMainModel(MainModel mvm) throws SQLException {
       this.model=mvm;
       this.deleteModel=new DeleteModel();
       this.editModel = new EditModel(mvm);

       this.model.loadProjects();
       this.model.loadUsers();
       this.model.loadCustomers();

    }

    @Override
    public void setInfoLabels() {
        System.out.println(model.getSelectedOrder());
        this.customer.setItems(model.getAllCustomers());
        this.customer.setValue(model.getSelectedObject(model.getSelectedOrder().getCustomerID(), "Customer"));
        this.user.setItems(model.getAllUsers());
        this.user.setValue(model.getSelectedObject(model.getSelectedOrder().getUserID(), "User"));
        this.project.setItems(model.getAllProjects());
        this.project.setValue(model.getSelectedObject(model.getSelectedOrder().getProjectID(), "Project"));
        this.nameField.setText(model.getSelectedOrder().getName());


        Date selectedDate = model.getSelectedOrder().getDate();
        LocalDate localDate = selectedDate.toLocalDate();

        this.date.setValue(localDate);





        System.out.println(model.getSelectedOrder().getCustomer() +" " + model.getSelectedOrder().getUserName() +" " +model.getSelectedOrder().getProject());

    }

    @Override
    public String delete(int id) {
        return this.deleteModel.deleteFromDatabase(id,"Order");
    }

    @Override
    public void edit(ActionEvent actionEvent) {

    }


    public void handleSave(javafx.event.ActionEvent actionEvent) throws SQLException {
        User selectedUser = (User) user.getSelectionModel().getSelectedItem();
        Project selectedProject = (Project) project.getSelectionModel().getSelectedItem();
        Customer selectedCustomer = (Customer) customer.getSelectionModel().getSelectedItem();


        LocalDate selectedDate = date.getValue();
        Date sqlDate = Date.valueOf(selectedDate);

        Order editedOrder = new Order(
                selectedUser.getId(),
                selectedProject.getProjectId(),
                nameField.getText(),
                selectedUser.getUsername(),
                selectedProject.toString(),
                selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName(),
                selectedCustomer.getId(),
                sqlDate,
                0.0
        );
        System.out.println(date.getValue() + "etooo");
        System.out.println("here is the ID:  " + model.getSelectedOrder().getId());
        editModel.updateDatabaseElement(editedOrder, "Order", model.getSelectedOrder().getId());
    }

    public void handleCancel(javafx.event.ActionEvent actionEvent) {

    }

    public void handleDelete(javafx.event.ActionEvent actionEvent) {
        System.out.println(model.getSelectedOrder().getId());
        delete(model.getSelectedOrder().getId());
    }
}
