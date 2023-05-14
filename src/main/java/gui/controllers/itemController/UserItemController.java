package main.java.gui.controllers.itemController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.be.User;
import main.java.gui.controllers.infoPageController.CustomerInfoController;
import main.java.gui.controllers.infoPageController.UserInfoController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserItemController implements Initializable {

    public Label username, firstName, password;
    private MainModel model ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //@Override
    public void setLabels(int numberOfElement,String type) {
        this.model = new MainModel();
        if (type.equals("User")) {

            try {
                this.model.loadUsers();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.model.setSelectedUser(this.model.getAllUsers().get(numberOfElement));
            //System.out.println(model.getSelectedUser().getFirstName());
            username.setText(this.model.getSelectedUser().getUsername());
            firstName.setText(this.model.getSelectedUser().getFirstName());
            password.setText("**********");
        }else if (type.equals("Technician")){
            try {
                this.model.loadTech();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.model.setSelectedUser(this.model.getAllTech().get(numberOfElement));
            //System.out.println(model.getSelectedUser().getFirstName());
            username.setText(this.model.getSelectedUser().getUsername());
            firstName.setText(this.model.getSelectedUser().getFirstName());
            password.setText(this.model.getSelectedUser().getPassword());
        }
    }
    public void setSearchedItems(int numberOfElement, ObservableList<User> selectedUsers){
        this.model = new MainModel();
        this.model.setSelectedUser(selectedUsers.get(numberOfElement));
        System.out.println(model.getSelectedUser().getFirstName());
        username.setText(this.model.getSelectedUser().getUsername());
        firstName.setText(this.model.getSelectedUser().getFirstName());
        password.setText(this.model.getSelectedUser().getPassword());
    }
    public void infoBtnHandle(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/info/UserInfoView.fxml"));
        Parent root = loader.load();
        UserInfoController controller = loader.getController();
        controller.setMainModel(model);
        controller.setInfoLabels();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.show();
    }
}
