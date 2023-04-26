package main.java.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.java.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    @FXML
    AnchorPane painnnnn;
    @FXML
    private Stage primaryStage;

    public void handleClicks(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader();
           // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
            painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/LoginPageView.fxml")));
            LoginPageController controller = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
