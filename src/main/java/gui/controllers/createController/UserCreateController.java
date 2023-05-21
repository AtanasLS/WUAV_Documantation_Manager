package main.java.gui.controllers.createController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.be.User;
import main.java.gui.controllers.itemController.PhotoItemController;
import main.java.gui.controllers.pageController.UserController;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.io.File;
import java.io.IOException;
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
    private MainModel model;
    private ObservableList<String> types;

    private String img="images/avatar-icon.png";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        types = FXCollections.observableArrayList();
        types.add("TechnicianManager");
        types.add("Technician");
        types.add("Seller");

        typeBox.setItems(types);
    }

    public void setModel(MainModel model){
        this.model = model;
        this.createModel = new CreateModel(model);
    }

    public void handleSave(ActionEvent actionEvent) {
        User newUser = new User(username.getText(), firstName.getText(), lastName.getText(),
                email.getText(), password.getText(), (String) typeBox.getSelectionModel().getSelectedItem(),this.img);
        createModel.createInDatabase(newUser, "User");
        this.model.addUser(newUser);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/UsersView.fxml"));
        try {
            Node newNode = loader.load();

            UserController controller = loader.getController();
            controller.setPnItems(this.model.getAllUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();



    }

    public void handleCancel(ActionEvent actionEvent) {
        LocalDate cureentDate = LocalDate.now();
        System.out.println(cureentDate);

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();

    }
    public void createDrawing(ActionEvent actionEvent) throws IOException {
        FileChooser layoutDrawingChooser = new FileChooser();
        Stage stage = new Stage();
        File selectedFile = layoutDrawingChooser.showOpenDialog(stage);
        Image layoutDrawing = new Image(selectedFile.getPath());
        Node node;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/PhotoItem.fxml"));
        node = loader.load();
        PhotoItemController controller = loader.getController();
        controller.setItems(layoutDrawing, selectedFile.getName());
        this.img = layoutDrawing.getUrl();


    }
}
