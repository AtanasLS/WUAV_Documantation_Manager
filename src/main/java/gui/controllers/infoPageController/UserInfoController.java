package main.java.gui.controllers.infoPageController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable, Info {

    public Label firstName,lastName,username,email,password,type;

    private MainModel model;
    private DeleteModel deleteModel;
    private EditModel editModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.editModel=new EditModel(model);
        this.deleteModel=new DeleteModel();
}

    @Override
    public void setMainModel(MainModel mvm) {
        this.model=mvm;


    }
    @Override
    public void setInfoLabels() {
        firstName.setText(this.model.getSelectedUser().getFirstName());
        lastName.setText(this.model.getSelectedUser().getLastName());
        username.setText(this.model.getSelectedUser().getUsername());
        email.setText(this.model.getSelectedUser().getEmail());
        password.setText(this.model.getSelectedUser().getPassword());
        type.setText(this.model.getSelectedUser().getType());



    }

    @Override
    public String delete(String id) {
        return this.deleteModel.deleteFromDatabase(this.model.getSelectedUser().getUsername(),"User");

    }

    @Override
    public void edit(ActionEvent actionEvent) {

    }
}
