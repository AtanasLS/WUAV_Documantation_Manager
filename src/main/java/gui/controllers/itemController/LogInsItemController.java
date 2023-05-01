package main.java.gui.controllers.itemController;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.be.Customer;
import main.java.be.LogIns;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInsItemController implements Initializable,Items {

    public Label username, password, project;
    private MainModel model ;

    private DeleteModel deleteModel;

    private EditModel editModel;


    private LogIns currentLogIn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        this.deleteModel=new DeleteModel();
        this.editModel=new EditModel();
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

    public void editLogIN(ActionEvent actionEvent){
        this.editModel.updateDatabaseElement(new Object(),"username","LogIn");

    }

    public void deleteLogIn(ActionEvent actionEvent){
        this.deleteModel.deleteFromDatabase(this.currentLogIn.getUsername(),"LogIn");

    }
}
