package main.java.gui.controllers.itemController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class TechnicianItemController implements Initializable,Items {

    public Label username, firstName, lastName;
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
    public void setLabels(int numberOfElement, MainModel model) {
        this.model = model;
        username.setText(this.model.getAllTech().get(numberOfElement).getUsername());
        firstName.setText(this.model.getAllTech().get(numberOfElement).getFirstName());
        lastName.setText(this.model.getAllTech().get(numberOfElement).getLastName());

    }
}
