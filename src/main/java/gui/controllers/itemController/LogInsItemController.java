package main.java.gui.controllers.itemController;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import main.java.be.Customer;
import main.java.be.LogIns;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInsItemController implements Initializable,Items {

    public Label username, password, project;
    private MainModel model ;

    private DeleteModel deleteModel;

    private EditModel editModel;

    @FXML
    private ProgressIndicator progressIndicator;


    private LogIns currentLogIn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        this.deleteModel=new DeleteModel();
        this.editModel=new EditModel(model);

    }

    @Override
    public void setLabels(int numberOfElement) {

        Task<Void> loadTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                model.loadLogIns();
                return null;
            }
        };
        loadTask.setOnSucceeded(event -> {
            this.currentLogIn=this.model.getAllLogIns().get(numberOfElement);
            username.setText(this.model.getAllLogIns().get(numberOfElement).getUsername());
            password.setText(this.model.getAllLogIns().get(numberOfElement).getPassword());
            project.setText(this.model.getAllLogIns().get(numberOfElement).getProject());

        });
        Thread loadThread = new Thread(loadTask);
        loadThread.start();
    }
    public void  setSearchedItemLabel(int numberOfElement, ObservableList<LogIns> searchedLogins){
        this.currentLogIn=searchedLogins.get(numberOfElement);
        username.setText(searchedLogins.get(numberOfElement).getUsername());
        password.setText(searchedLogins.get(numberOfElement).getPassword());
        project.setText(searchedLogins.get(numberOfElement).getProject());

    }

    public void editLogIN(ActionEvent actionEvent) throws SQLException {
        this.editModel.updateDatabaseElement(new Object(),"LogIn",this.currentLogIn.getId());

    }

    public void deleteLogIn(ActionEvent actionEvent){
        this.deleteModel.deleteFromDatabase(this.currentLogIn.getId(),"LogIn");

    }
}
