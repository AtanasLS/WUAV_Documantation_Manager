package main.java.gui.controllers.editController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.java.be.LogIns;
import main.java.be.Project;
import main.java.be.User;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {
    @FXML
    public ComboBox users;

    @FXML
    public Button cancelBtn,addBtn;

    private EditModel editModel;
    private MainModel mainModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMainModel(MainModel mvm) throws IOException, SQLException {
        editModel = new EditModel(mvm);
        mainModel=mvm;
        mvm.loadTech();
        users.setItems(mvm.getAllTech());

    }


    public void save(ActionEvent actionEvent) throws SQLException {

        Project project=mainModel.getSelectedProject();
        User user=(User) users.getSelectionModel().getSelectedItem();

        project.setCustomerId(user.getId());

        this.editModel.updateDatabaseElement(project,"Project",project.getProjectId());
        Stage stage = (Stage) addBtn.getScene().getWindow();
        stage.close();
    }

    public void cancel(ActionEvent actionEvent){

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();

    }

}
