package main.java.gui.controllers.itemController;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.be.Project;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProjectItemController implements Initializable,Items {

    public Label type, customer, price;
    private MainModel model ;

    private DeleteModel deleteModel;

    private EditModel editModel;

    private Project currentProject;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        this.deleteModel=new DeleteModel();
        this.editModel=new EditModel(model);
        try {
            this.model.loadProjects();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void setLabels(int numberOfElement, MainModel model) {

        this.currentProject=this.model.getAllProjects().get(numberOfElement);

        type.setText(this.model.getAllProjects().get(numberOfElement).getType());
        customer.setText(this.model.getAllProjects().get(numberOfElement).getCustomer());
        price.setText(String.valueOf(this.model.getAllProjects().get(numberOfElement).getPrice()));



    }

    public void editProject(ActionEvent actionEvent) throws SQLException {
        this.editModel.updateDatabaseElement(new Object(),"username","Project");

    }

    public void deleteProject(ActionEvent actionEvent){
        this.deleteModel.deleteFromDatabase(String.valueOf(this.currentProject.getProjectId()),"Project");

    }
}
