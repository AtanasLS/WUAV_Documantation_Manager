package main.java.gui.controllers.createController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.java.be.LogIns;
import main.java.be.Project;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateLoginController implements Initializable, CreateController {
    public TextField username, password;
    public ComboBox project;

    private CreateModel createModel;
    private MainModel model;
    private ObservableList<Project> projects;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model=new MainModel();
        this.projects.addAll(model.getAllProjects());
    }

    @Override
    public void setModel(MainModel model){
        this.createModel = new CreateModel(model);
    }


    @Override
    public void handleSave(ActionEvent actionEvent) {

        Project selectedProject= (Project) this.project.getSelectionModel().getSelectedItem();

        LogIns logIns=new LogIns(this.username.getText(),this.password.getText(),selectedProject.getType(),selectedProject.getProjectId());
        createModel.createInDatabase(logIns,"LogIn");
    }

    @Override
    public void handleCancel(ActionEvent actionEvent) {

    }
}