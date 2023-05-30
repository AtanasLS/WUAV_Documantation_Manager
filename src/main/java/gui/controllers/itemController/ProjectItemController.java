package main.java.gui.controllers.itemController;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.be.Project;
import main.java.be.User;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProjectItemController implements Initializable {

    public Label type, customer;
    private MainModel model ;

    private DeleteModel deleteModel;

    private EditModel editModel;

    private Project currentProject;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        this.deleteModel=new DeleteModel();
        this.editModel=new EditModel(model);


    }


    public void setLabels(int numberOfElement, MainModel model, User selectedUser) {
        this.model = model;
        if (selectedUser.getType().equals("Technician")){
            Task<Void> loadTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    model.loadProjects();
                    model.loadProjects();
                    model.loadTech();
                    model.loadProjectToUser();
                    return null;
                }
            };
            loadTask.setOnSucceeded(event -> {
                this.currentProject=this.model.getAllProjectToUser(selectedUser).get(numberOfElement);
                type.setText(this.model.getAllProjectToUser(selectedUser).get(numberOfElement).getType());
                customer.setText(this.model.getAllProjectToUser(selectedUser).get(numberOfElement).getCustomer());
            });
            Thread thread = new Thread(loadTask);
            thread.start();
        }else {
            Task<Void> loadTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    model.loadProjects();
                    return null;
                }
            };
            loadTask.setOnSucceeded(event -> {
                this.currentProject=this.model.getAllProjects().get(numberOfElement);
                type.setText(this.model.getAllProjects().get(numberOfElement).getType());
                customer.setText(this.model.getAllProjects().get(numberOfElement).getCustomer());
            });
            Thread thread = new Thread(loadTask);
            thread.start();
        }

    }
    public void setSearchedProjects(int numberOfElement, ObservableList<Project> searchedProjects){
        type.setText(searchedProjects.get(numberOfElement).getType());
        customer.setText(searchedProjects.get(numberOfElement).getCustomer());

    }

    public void editProject(ActionEvent actionEvent) throws SQLException {

        this.editModel.updateDatabaseElement(new Object(),"Project",this.currentProject.getProjectId());

    }

    public void deleteProject(ActionEvent actionEvent){
        this.deleteModel.deleteFromDatabase(this.currentProject.getProjectId(),"Project");

    }


}
