package main.java.gui.controllers.mainDashboardsController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.gui.controllers.pageController.*;
import main.java.be.User;
import main.java.gui.controllers.itemController.PhotoItemController;
import main.java.gui.controllers.pageController.CustomerPageController;
import main.java.gui.controllers.pageController.DocumentController;
import main.java.gui.controllers.pageController.UserController;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TechnicianViewController implements Initializable {
    public Button btnOrders, btnCustomers, btnProjects, btnLogIns, btnSignout, btnDocuments;
    public AnchorPane painnnnn;
    public Label usernameLogIN;
    public ImageView profileImage;

    private String selected;

    private MainModel model;

    private EditModel editModel;

    @FXML
    private Image avatar;

    private String img;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMainModel(MainModel mvm){
        this.model = mvm ;
        this.editModel=new EditModel(mvm);
        this.usernameLogIN.setText(this.model.getLogInUser().getFirstName()+ "  "+this.model.getLogInUser().getLastName() );

        this.avatar=new Image("/images/"+this.model.getLogInUser().getImg());
        this.profileImage.setImage(avatar);
        System.out.println("/images/" + this.model.getLogInUser().getUsername()+".png");
        System.out.println(model.getLogInUser().getImg());

    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnLogIns) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/LogInsView.fxml"));
                painnnnn.getChildren().setAll((Node) loader.load());
                LogInsController controller = loader.getController();
                controller.setModel();

                selected = "LogIns";
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();              }

        }else if (actionEvent.getSource() == btnDocuments){
            try {
                FXMLLoader loader = new FXMLLoader((getClass().getResource("/view/pages/DocumentsPage.fxml")));
                painnnnn.getChildren().setAll((Node) loader.load());
                DocumentController documentController = loader.getController();
                documentController.setPnItems("Technician");

                selected = "Documents";

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();              }
        }else if (actionEvent.getSource() == btnOrders){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/OrdersView.fxml"));
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load());
                OrderController controller = loader.getController();
                controller.setModel("Technician");
                selected = "Orders";
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();              }
        }else if (actionEvent.getSource() == btnProjects){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/ProjectsView.fxml"));
                painnnnn.getChildren().setAll((Node) loader.load());
                ProjectController controller = loader.getController();
                controller.setModel(model.getLogInUser());
                selected = "Projects";
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();              }

        }else if (actionEvent.getSource() == btnCustomers){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/CustomersView.fxml"));
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load());
                CustomerPageController controller = loader.getController();
                controller.setMainModel("Technician");
                selected = "Customers";
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();              }
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
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();              }
        }
    }

    public void createDrawing(MouseEvent actionEvent) throws IOException, SQLException {
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
        User user=this.model.getLogInUser();
        user.setImg(this.img);
        this.editModel.updateDatabaseElement(user,"User",user.getId());

        this.avatar=new Image(layoutDrawing.getUrl());



    }


    public void createDrawing(javafx.scene.input.MouseEvent mouseEvent) {

    }
}
