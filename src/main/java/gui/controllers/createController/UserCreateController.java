package main.java.gui.controllers.createController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.be.User;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.ResourceBundle;

public class UserCreateController implements Initializable {
    public TextField firstName , lastName, email, username, password;
    public ComboBox typeBox;
    public Button saveBtn, cancelBtn;

    private CreateModel createModel;
    private ObservableList<String> types;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        types = FXCollections.observableArrayList();
        types.add("TechnicianManager");
        types.add("Technician");
        types.add("Seller");

        typeBox.setItems(types);
    }

    public void setModel(MainModel model){
        this.createModel = new CreateModel(model);
    }

    public void handleSave(ActionEvent actionEvent) {
        User newUser = new User(username.getText(), firstName.getText(), lastName.getText(),
                email.getText(), password.getText(), (String) typeBox.getSelectionModel().getSelectedItem());
        createModel.createInDatabase(newUser, "User");
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        LocalDate cureentDate = LocalDate.now();
        System.out.println(cureentDate);

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();

    }
}
