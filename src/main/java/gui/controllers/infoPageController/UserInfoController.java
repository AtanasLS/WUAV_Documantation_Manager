package main.java.gui.controllers.infoPageController;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.gui.controllers.editController.UserEditController;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.awt.event.ActionEvent;
import java.io.IOException;
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
    public String delete(int id) {
        return this.deleteModel.deleteFromDatabase(this.model.getSelectedUser().getId(),"User");

    }

    @Override
    public void edit(ActionEvent actionEvent) {

    }

    public void edit(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit/UserEdit.fxml"));
        Parent root = loader.load();
        UserEditController controller = loader.getController();
        controller.setMainModel(model);
        //controller.setInformation();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) ((javafx.scene.control.Button) actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void delete(javafx.event.ActionEvent actionEvent) {

    }
}
