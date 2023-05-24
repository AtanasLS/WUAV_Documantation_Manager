package main.java.gui.controllers.createController;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import main.java.be.Project;
import main.java.be.ProjectToUser;
import main.java.be.User;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SetProjectToUserController implements Initializable {
    public MFXComboBox projects, technicians;
    private MainModel model;
    private CreateModel createModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        this.createModel = new CreateModel(model);

        try {
            model.loadTech();
            model.loadProjects();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        projects.setItems(model.getAllProjects());
        technicians.setItems(model.getAllTech());

    }

    public void setBtn(ActionEvent actionEvent) {
        Project selectedProject = (Project) projects.getSelectedItem();
        User selectedTech = (User) technicians.getSelectedItem();
        ProjectToUser projectToUser = new ProjectToUser(selectedProject.getProjectId(), selectedTech.getId());
        createModel.createInDatabase(projectToUser, "ProjectToUser");
    }

    public void cancelBtn(ActionEvent actionEvent) {

    }
}
