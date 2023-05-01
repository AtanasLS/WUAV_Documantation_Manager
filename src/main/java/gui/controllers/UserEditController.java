package main.java.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.be.User;
import main.java.gui.controllers.infoPageController.Info;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserEditController implements Initializable {



    @FXML
    public javafx.scene.control.Button cancelBtn , saveBtn;
    public TextField firstName, lastName, email, username, password, type;

    private EditModel model;

    private String id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setMainModel(MainModel mvm){
        model = new EditModel(mvm);
        firstName.setText(mvm.getSelectedUser().getFirstName());
        lastName.setText(mvm.getSelectedUser().getLastName());
        username.setText(mvm.getSelectedUser().getUsername());
        email.setText(mvm.getSelectedUser().getEmail());
        password.setText(mvm.getSelectedUser().getPassword());
        type.setText(mvm.getSelectedUser().getType());
        this.id = mvm.getSelectedUser().getUsername();
    }

    public void handleSave(ActionEvent actionEvent) throws SQLException {
        User editedCustomer = new User(username.getText(), firstName.getText(), lastName.getText(), email.getText()
                ,password.getText(), type.getText());

        model.updateDatabaseElement(editedCustomer, this.id ,"User");

        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
