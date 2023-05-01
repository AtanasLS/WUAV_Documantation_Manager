package main.java.gui.controllers.itemController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.be.User;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class UserItemController implements Initializable,Items {

    public Label username, firstName, password;
    private MainModel model ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        try {
            this.model.loadUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setLabels(int numberOfElement) {

        username.setText(this.model.getAllUsers().get(numberOfElement).getUsername());
        firstName.setText(this.model.getAllUsers().get(numberOfElement).getFirstName());
        password.setText(this.model.getAllUsers().get(numberOfElement).getPassword());

    }
}
