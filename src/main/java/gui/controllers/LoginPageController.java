package main.java.gui.controllers;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import main.java.be.User;
import main.java.gui.controllers.mainDashboardsController.AdminViewController;
import main.java.gui.controllers.mainDashboardsController.SellerViewController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    @FXML
    public MFXTextField usernameField, passwordField;
    private MainModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new MainModel();
        try {
            model.loadCustomers();
            model.loadUsers();
            model.loadTech();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void logIn(ActionEvent actionEvent) throws Exception {
        if (model.checkIfUserExist(usernameField.getText(), passwordField.getText())) {
            model.setUser(usernameField.getText());
            User loggedInUser = model.getUser(usernameField.getText());
            if (loggedInUser.getType().equals("Admin")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminMainView.fxml"));
                    Parent root = loader.load();
                    AdminViewController controller = loader.getController();
                    controller.setMainModel(model);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setFullScreen(false);
                    stage.setResizable(false);

                    stage.show();

                    ((Node) ((javafx.scene.control.Button) actionEvent.getSource())).getScene().getWindow().hide();                   //w ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();


                } catch (IOException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load App.fxml");
                    alert.showAndWait();
                }

            }else if (loggedInUser.getType().equals("Technician")){
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TechnicianView.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    // controller.setLoggedInUser(loggedInUser.getUsername(), loggedInUser.getType());
                    stage.setFullScreen(false);
                    stage.setResizable(false);
                    stage.show();

                    ((Node) ((javafx.scene.control.Button) actionEvent.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load App.fxml");
                    alert.showAndWait();
                }
            }else if (loggedInUser.getType().equals("TechnicianManager")){
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TechnicianManagerView.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    // controller.setLoggedInUser(loggedInUser.getUsername(), loggedInUser.getType());
                    stage.setFullScreen(false);
                    stage.setResizable(false);

                    stage.show();

                    ((Node) ((javafx.scene.control.Button) actionEvent.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load App.fxml");
                    alert.showAndWait();
                }
            }else if (loggedInUser.getType().equals("Seller")){
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SellerView.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    SellerViewController controller = loader.getController();
                    // controller.setLoggedInUser(loggedInUser.getUsername(), loggedInUser.getType());
                    stage.setFullScreen(false);
                    stage.setResizable(false);
                    stage.show();

                    ((Node) ((javafx.scene.control.Button) actionEvent.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load App.fxml");
                    alert.showAndWait();
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong username or password");
            alert.showAndWait();
        }
    }


}
