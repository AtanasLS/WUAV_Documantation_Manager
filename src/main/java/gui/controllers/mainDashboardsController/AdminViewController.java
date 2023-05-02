package main.java.gui.controllers.mainDashboardsController;

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
import main.java.gui.controllers.LoginPageController;
import main.java.gui.controllers.createController.CreateCustomerController;
import main.java.gui.controllers.createController.CreateDocumentController;
import main.java.gui.controllers.createController.UserCreateController;
import main.java.gui.controllers.pageController.UserController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {
    @FXML
    public Button btnLogIns, btnOrders, btnDocuments, btnUsers, btnCustomers, btnProjects, btnSignout;
    @FXML
    AnchorPane painnnnn;
    @FXML
    private Stage primaryStage;
    private String selected;


    private MainModel model;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setMainModel(MainModel mvm){
        this.model = mvm ;

    }
    public void newObject(ActionEvent actionEvent) throws IOException {

        switch (selected.toLowerCase()){
            case "logins":
                //open log in create fxml
                break;
            case "users":
                FXMLLoader userLoader = new FXMLLoader(getClass().getResource("/view/create/UserCreate.fxml"));
                Parent root = userLoader.load();
                UserCreateController controller = userLoader.getController();
                controller.setModel(model);
                //controller.setInformation();
                Stage userStage = new Stage();
                userStage.setScene(new Scene(root));
                userStage.show();
                break;
            case "orders":
                //open order in create fxml
                break;
            case "customers":

                FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("/view/create/CustomerCreate.fxml"));
                Parent customerRoot = customerLoader.load();
                CreateCustomerController createCustomerController = customerLoader.getController();
                createCustomerController.setModel(model);
                //controller.setInformation();
                Stage customerStage = new Stage();
                customerStage.setScene(new Scene(customerRoot));
                customerStage.show();
                break;
            case "projects":
                //open project in create fxml
                break;
            case "documents":
                /*
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/create/CreateDocumentView.fxml"));
                Parent root = loader.load();
                CreateDocumentController controller = loader.getController();
                controller.setModel(model);
                //controller.setInformation();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                 */

                break;
        }
    }
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnLogIns) {
            try {
                FXMLLoader loader = new FXMLLoader();
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/LogInsView.fxml")));
                selected = "LogIns";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnDocuments){
            try {
                FXMLLoader loader = new FXMLLoader();
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/DocumentsPage.fxml")));
                selected = "Documents";

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnOrders){
            try {
                FXMLLoader loader = new FXMLLoader();
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/OrdersView.fxml")));
                LoginPageController controller = loader.getController();
                selected = "Orders";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnProjects){
            try {
                FXMLLoader loader = new FXMLLoader();
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/ProjectsView.fxml")));
                selected = "Projects";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnUsers){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/UsersView.fxml"));
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load());
                UserController controller = loader.getController();
                controller.setMainModel(model);
                selected = "Users";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnCustomers){
            try {
                FXMLLoader loader = new FXMLLoader();
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load(getClass().getResource("/view/pages/CustomersView.fxml")));
                LoginPageController controller = loader.getController();
                selected = "Customers";
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
