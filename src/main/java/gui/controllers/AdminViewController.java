package main.java.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.gui.controllers.pageController.DocumentController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable, UserViewInterface {
    @FXML
    public Button btnLogIns, btnOrders, btnDocuments, btnUsers, btnCustomers, btnProjects, btnSignout;
    @FXML
    AnchorPane painnnnn;
    @FXML
    private Stage primaryStage;

    private String selected;






    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            FXMLLoader loader = new FXMLLoader();
           // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
            painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/DocumentsPage.fxml")));
            DocumentController controller = loader.getController();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void newObject(ActionEvent actionEvent){

        switch (selected.toLowerCase()){
            case "logins":
                //open log in create fxml
                break;
            case "users":
                //open user in create fxml
                break;
            case "orders":
                //open order in create fxml
                break;
            case "customers":
                //open lcustomerog in create fxml
                break;
            case "projects":
                //open project in create fxml
                break;
            case "documents":
                //open document in create fxml
                break;
        }
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnLogIns) {
            try {
                FXMLLoader loader = new FXMLLoader();
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/LogInsView.fxml")));
                this.selected="LogIns";
                LoginPageController controller = loader.getController();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnDocuments){
            try {
                FXMLLoader loader = new FXMLLoader();
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/DocumentsPage.fxml")));
                this.selected="Documents";

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnOrders){
            try {
                FXMLLoader loader = new FXMLLoader();
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/OrdersView.fxml")));
                this.selected="Orders";
                LoginPageController controller = loader.getController();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnProjects){
            try {
                FXMLLoader loader = new FXMLLoader();
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/ProjectsView.fxml")));
                this.selected="Projects";

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnUsers){
            try {
                FXMLLoader loader = new FXMLLoader();
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/UsersView.fxml")));
<<<<<<< Updated upstream
                LoginPageController controller = loader.getController();

=======
                this.selected="Users";
>>>>>>> Stashed changes
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnCustomers){
            try {
                FXMLLoader loader = new FXMLLoader();
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/CustomersView.fxml")));
                LoginPageController controller = loader.getController();
                this.selected="Customers";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (actionEvent.getSource() == btnSignout){
            try {
                ((Node) ((Button) actionEvent.getSource())).getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginPageView.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.setTitle("Please Log In");
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
