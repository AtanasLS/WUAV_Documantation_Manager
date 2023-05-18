package main.java.gui.controllers.itemController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.be.Project;
import main.java.gui.controllers.editController.AddUserController;
import main.java.gui.controllers.editController.DocumentEditController;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.io.IOException;
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
        System.out.println(model.getAllProjects().size());
        this.currentProject=this.model.getAllProjects().get(numberOfElement);
        type.setText(this.model.getAllProjects().get(numberOfElement).getType());
        customer.setText(this.model.getAllProjects().get(numberOfElement).getCustomer());
       // price.setText(String.valueOf(10.10));



    }

    public void editProject(ActionEvent actionEvent) throws SQLException {
        this.editModel.updateDatabaseElement(new Object(),"Project",this.currentProject.getProjectId());

    }

    public void addUser(ActionEvent actionEvent) throws IOException, SQLException {
        model.setSelectedProject(currentProject);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit/AddUser.fxml"));
        Parent root = loader.load();
        AddUserController controller = loader.getController();
        controller.setMainModel(this.model);
        //  controller.setInfoLabels();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.show();
    }
    public void deleteProject(ActionEvent actionEvent){
        this.deleteModel.deleteFromDatabase(this.currentProject.getProjectId(),"Project");

    }
}
