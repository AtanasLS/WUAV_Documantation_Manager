package main.java.gui.controllers.createController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.be.Project;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
        this.checkData();

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
        double price= Double.parseDouble(this.price.getText());
        int id=model.getAllProjects().get(model.getAllCustomers().size()-1).getProjectId()+1;
        Customer customer1= (Customer) this.customer.getSelectionModel().getSelectedItem();
        Project project= new Project(id,this.type.getText(),price,customer1.getFirstName(),customer1.getId());
        this.createModel.createInDatabase(project,"Project");
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