package main.java.gui.controllers.editController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.be.User;
import main.java.gui.controllers.infoPageController.Info;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UserEditController implements Initializable {



    @FXML
    public javafx.scene.control.Button cancelBtn , saveBtn;
    @FXML
    public TextField firstName, lastName, email, username, password, type;

    private EditModel model;

    private int id;
    private int userID;

    private String img;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setMainModel(MainModel mvm){
        model = new EditModel(mvm);
        firstName.setText(mvm.getSelectedUser().getFirstName());
        lastName.setText(mvm.getSelectedUser().getLastName());
        username.setText(mvm.getSelectedUser().getUsername());
        email.setText(mvm.getSelectedUser().getEmail());
        type.setText(mvm.getSelectedUser().getType());
        this.id = mvm.getSelectedUser().getId();
        this.userID = mvm.getSelectedUser().getId();
        this.img=mvm.getSelectedUser().getImg();
        this.checkData();

    }

    public void handleSave(ActionEvent actionEvent) throws SQLException {
        System.out.println(password.getText() +"  "+this.id);
        User editedCustomer = new User( this.id,username.getText(), firstName.getText(), lastName.getText(), email.getText()
                ,password.getText(), type.getText(),this.img);

        model.updateDatabaseElement(editedCustomer,"User", this.id );

        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void checkData(){
        Pattern name = Pattern.compile("[A-Za-z\\s]{2,}");
        TextFormatter<?> formatter = new TextFormatter<>(change -> {
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        TextFormatter<?> formatter1 = new TextFormatter<>(change -> {
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        TextFormatter<?> formatter2 = new TextFormatter<>(change -> {
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern mail = Pattern.compile("[A-Za-z1-9]{2,}@[A-Za-z1-9].{2,}");
        TextFormatter<?> formatterMail = new TextFormatter<>(change -> {
            if (mail.matcher(change.getControlNewText()).matches()) {
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern phone = Pattern.compile("\\+?\\d[\\d-\\s]{8,}");
        TextFormatter<?> formatterPhone = new TextFormatter<>(change -> {
            if (phone.matcher(change.getControlNewText()).matches()) {
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern address = Pattern.compile("[A-Za-z0-9\\s,.]+");
        TextFormatter<?> formatterAddress = new TextFormatter<>(change -> {
            if (address.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern pass = Pattern.compile("[A-Za-z\\s1-9\\s]{2,}");
        TextFormatter<?> formatterPass = new TextFormatter<>(change -> {
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
}
