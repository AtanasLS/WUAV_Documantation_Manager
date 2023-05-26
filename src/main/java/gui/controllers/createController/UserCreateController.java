package main.java.gui.controllers.createController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
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
import java.util.regex.Pattern;

public class UserCreateController implements Initializable, CreateController {
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
        this.checkData();

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
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
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

    @Override
    public void checkData(){
        Pattern name = Pattern.compile("[A-Za-z\\s]{1,}");
        TextFormatter<String> formatter = new TextFormatter<>( change -> {
            System.out.println(change.getControlNewText().matches("[A-Za-z\\s]{1,}"));
            if (change.getControlNewText().matches(name.pattern())) {
                System.out.println("work");
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        TextFormatter<String> formatter1 = new TextFormatter<>(change -> {
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        TextFormatter<String> formatter2 = new TextFormatter<>(change -> {
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern mail = Pattern.compile("[a-zA-Z0-9._%+-@]{1,}");
        TextFormatter<String> formatterMail = new TextFormatter<>(change -> {
            if (mail.matcher(change.getControlNewText()).matches()) {
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern phone = Pattern.compile("\\+?\\d[\\d-\\s]{1,}");
        TextFormatter<String> formatterPhone = new TextFormatter<>(change -> {
            if (phone.matcher(change.getControlNewText()).matches()) {
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern address = Pattern.compile("[A-Za-z0-9\\s,.]+");
        TextFormatter<String> formatterAddress = new TextFormatter<>(change -> {
            if (address.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern pass = Pattern.compile("[A-Za-z\\s1-9\\s]{1,}");
        TextFormatter<String> formatterPass = new TextFormatter<>(change -> {
            if (pass.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        email.setTextFormatter(formatterMail);
        firstName.setTextFormatter(formatter);
        lastName.setTextFormatter(formatter1);
        username.setTextFormatter(formatter2);
        password.setTextFormatter(formatterPass);



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
