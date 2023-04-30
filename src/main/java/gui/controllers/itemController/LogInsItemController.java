package main.java.gui.controllers.itemController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInsItemController implements Initializable,Items {

    public Label username, password, project;
    private MainModel model ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        try {
            this.model.loadLogIns();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setLabels(int numberOfElement) {

        username.setText(this.model.getAllLogIns().get(numberOfElement).getUsername() );
        password.setText(this.model.getAllLogIns().get(numberOfElement).getPassword());
        project.setText(this.model.getAllLogIns().get(numberOfElement).getProject());

    }
}
