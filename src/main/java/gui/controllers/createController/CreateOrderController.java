package main.java.gui.controllers.createController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.be.Order;
import main.java.be.Project;
import main.java.be.User;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CreateOrderController implements Initializable,CreateController {
    public TextField name,price;
    public ComboBox customer,project,user;
    public DatePicker date;
    public Button cancelBtn, saveBtn;

    private CreateModel createModel;
    private MainModel model;
    private ObservableList<Customer> customers;
    private ObservableList<Project> projects;
    private ObservableList<User> users;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model=new MainModel();
        this.customers = FXCollections.observableArrayList();
        this.projects = FXCollections.observableArrayList();
        this.users =  FXCollections.observableArrayList();
        this.checkData();


        try {
            model.loadProjects();
            model.loadCustomers();
            model.loadUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.customers.addAll(model.getAllCustomers());
        this.projects.addAll(model.getAllProjects());
        this.users.addAll(model.getAllUsers());
        this.user.setItems(users);
        this.customer.setItems(customers);
        this.project.setItems(projects);
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

        LocalDate selectedDate = date.getValue();
        Date sqlDate = Date.valueOf(selectedDate);

        Order order=new Order(user1.getId(),project1.getProjectId(),
                this.name.getText(),user1.getUsername(),project1.getType(),
                customer1.getFirstName(),customer1.getId(),sqlDate,price);
        createModel.createInDatabase(order, "Order");

        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();

    }

    @Override
    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void checkData(){
        Pattern name = Pattern.compile("\\^(?!\\s)([a-z ,.'-]+)$");
        TextFormatter<?> formatter = new TextFormatter<>(change -> {
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern mail = Pattern.compile("\\^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        TextFormatter<?> formatterMail = new TextFormatter<>(change -> {
            if (mail.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern phone = Pattern.compile("\\^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
        TextFormatter<?> formatterPhone = new TextFormatter<>(change -> {
            if (phone.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern address = Pattern.compile("\\^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        TextFormatter<?> formatterAddress = new TextFormatter<>(change -> {
            if (address.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern pass = Pattern.compile("\\^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}$");
        TextFormatter<?> formatterPass = new TextFormatter<>(change -> {
            if (pass.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });



    }
}