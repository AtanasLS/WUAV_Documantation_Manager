package main.java.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.gui.controllers.pageController.CustomerPageController;
import main.java.gui.controllers.pageController.UserController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TechnicianViewController implements Initializable {
    public Button btnOrders, btnCustomers, btnProjects, btnLogIns, btnSignout, btnDocuments;
    public AnchorPane painnnnn;

    private String selected;

    private MainModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMainModel(MainModel mvm){
        this.model = mvm ;

    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnLogIns) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/LogInsView.fxml"));
                painnnnn.getChildren().setAll((Node) loader.load());
                selected = "LogIns";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else if (actionEvent.getSource() == btnDocuments){
            try {
                FXMLLoader loader = new FXMLLoader((getClass().getResource("/view/pages/DocumentsPage.fxml")));
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load());
                selected = "Documents";

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnOrders){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/OrdersView.fxml"));
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load());

                selected = "Orders";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (actionEvent.getSource() == btnProjects){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/ProjectsView.fxml"));
                painnnnn.getChildren().setAll((Node) loader.load());
                selected = "Projects";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else if (actionEvent.getSource() == btnCustomers){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/CustomersView.fxml"));
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load());
                CustomerPageController controller = loader.getController();
                controller.setMainModel();
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
