package main.java.gui.controllers.itemController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectItemController implements Initializable,Items {

    public Label type, customer, price;
    private MainModel model ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        try {
            this.model.loadProjects();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void setLabels(int numberOfElement) {

        type.setText(this.model.getAllProjects().get(numberOfElement).getType());
        customer.setText(this.model.getAllProjects().get(numberOfElement).getCustomer());
        price.setText(String.valueOf(this.model.getAllProjects().get(numberOfElement).getPrice()));



    }
}
