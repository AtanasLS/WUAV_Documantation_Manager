package main.java.gui.controllers.pageController;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class DropdownController implements Initializable {
    public MFXComboBox dropdown;
    @FXML
    private Label infoLabel;

    private String category;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dropdown.getItems().addAll("Users", "Documents", "Logins", "Projects", "Customers");

    }

    public void showInformation(ActionEvent actionEvent) {
        String information = "";
        category = dropdown.getSelectedItem().toString();

        if (category.equals("Users")) {
            information = "Information about users:\n" +
                    "User can be created only by the admin or the project manager.\n(Keep in mind that the project manager can't create seller\n " +
                    "or another project manager, he is allowed only to create users\nof type technicians.";
        } else if (category.equals("Documents")) {
            information = "Information about documents:\n"+
                    "For the creating of a document you need first to create\n" +
            "1. Create a customer.\n" +
                    "2. Created project that is assigned to that customer.\n" +
            "3. Created technician that is assigned to that project.\n" +
            "4. Created Logins that are assigned to that project.\n";
        } else if (category.equals("Logins")) {
            information = "Information about logins:\n" +
                    "To create login you need a created project first,\n" +
                    " because this login credentials need to be assigned\nto a certain project.";
        } else if (category.equals("Projects")) {
            information = "Information about projects:\n"+
                    "To create a project you must have an already created customer to which to assign the project.\n" +
                    "The project includes the type of the project and the client\nfor whom this project is being made. ";
        } else if (category.equals("Customers")) {
            information = "Information about customers:\n " +
                    "A customer can be created only by admin or a technician manager. He needs to have the following credentials:\n" +
                    "1. Personal Information (first and last name)\n" +
                    "2. Contacts such as email and tel. number.\n" +
                    "3. First and second address (second address is not mandatory)"
            ;
        }

        infoLabel.setText(information);
    }
}