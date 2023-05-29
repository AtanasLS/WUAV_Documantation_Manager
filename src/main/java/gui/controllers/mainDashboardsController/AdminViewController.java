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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.be.User;
import main.java.gui.controllers.LoginPageController;
import main.java.gui.controllers.createController.*;
import main.java.gui.controllers.pageController.*;
import main.java.gui.controllers.itemController.PhotoItemController;
import main.java.gui.controllers.createController.*;
import main.java.gui.controllers.pageController.*;
import main.java.gui.controllers.pageController.CustomerPageController;
import main.java.gui.controllers.pageController.DocumentController;
import main.java.gui.controllers.pageController.UserController;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {
    @FXML
    public Button btnLogIns, btnOrders, btnDocuments, btnUsers, btnCustomers, btnProjects, btnSignout;
    public Label usernameLogIN;
    public ImageView avatarImage;
    public Button mainDashBtn;
    @FXML
    AnchorPane painnnnn;
    @FXML
    private Stage primaryStage;
;
    private String selected;

    @FXML
    private javafx.scene.image.Image avatar;
    private String img;
    private EditModel editModel;



    private MainModel model;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setMainModel(MainModel mvm) throws IOException {
        this.model = mvm ;
        this.editModel=new EditModel(mvm);
        this.usernameLogIN.setText(this.model.getLogInUser().getFirstName()+ "  "+this.model.getLogInUser().getLastName() );
        System.out.println(this.model.getLogInUser().getUsername());
        System.out.println(this.model.getLogInUser().getImg());
        this.avatar=new Image("/images/"+this.model.getLogInUser().getImg());
        this.avatarImage.setImage(avatar);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/MainDashboardView.fxml"));
        painnnnn.getChildren().setAll((Node) loader.load());


    }
    public void newObject(ActionEvent actionEvent) throws IOException, SQLException {

        switch (selected.toLowerCase()){
            case "logins":

                break;
            case "user":
                FXMLLoader userLoader = new FXMLLoader(getClass().getResource("/view/create/UserCreate.fxml"));
                Parent root = userLoader.load();
                UserCreateController controller = userLoader.getController();
                controller.setModel(model);
                Stage userStage = new Stage();
                userStage.setScene(new Scene(root));
                userStage.show();
                break;
            case "orders":

                break;
            case "customers":


                break;
            case "projects":
                FXMLLoader projectLoader = new FXMLLoader(getClass().getResource("/view/create/CreateProject.fxml"));
                Parent projectsRoot = projectLoader.load();
                CreateProjectController createProjectController = projectLoader.getController();
                createProjectController.setModel(model);
                Stage projectStage = new Stage();
                projectStage.setScene(new Scene(projectsRoot));
                projectStage.show();
                break;
            case "documents":
                FXMLLoader documentsLoader = new FXMLLoader(getClass().getResource("/view/create/CreateDocumentView.fxml"));
                Parent documentsRoot = documentsLoader.load();
                CreateDocumentController createDocumentController = documentsLoader.getController();
                createDocumentController.setModel(model);
                //controller.setInformation();
                Stage documentsStage = new Stage();
                documentsStage.setScene(new Scene(documentsRoot));
                documentsStage.show();
                break;
        }
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
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
                alert.showAndWait();

            }
        }else if (actionEvent.getSource() == mainDashBtn){
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/view/pages/MainDashboardView.fxml")));
            painnnnn.getChildren().setAll((Node) loader.load());
            selected = "Documents";
        }
        else if (actionEvent.getSource() == btnDocuments){
            try {
                FXMLLoader loader = new FXMLLoader((getClass().getResource("/view/pages/DocumentsPage.fxml")));

                painnnnn.getChildren().setAll((Node) loader.load());
                DocumentController documentController = loader.getController();
                documentController.setPnItems("Users");
                selected = "Documents";

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();

            }
        }else if (actionEvent.getSource() == btnOrders){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/OrdersView.fxml"));
                painnnnn.getChildren().setAll((Node) loader.load());
                OrderController controller = loader.getController();
                controller.setModel("Admin");
                selected = "Orders";
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();

            }
        }else if (actionEvent.getSource() == btnProjects){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/ProjectsView.fxml"));
                painnnnn.getChildren().setAll((Node) loader.load());
                ProjectController controller = loader.getController();
                controller.setModel(model.getLogInUser());
                selected = "Projects";
            } catch (IOException e) {
]                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();

            }
        }else if (actionEvent.getSource() == btnUsers){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/UsersView.fxml"));
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load());
                UserController controller = loader.getController();
                selected = "User";
                controller.setMainModel(selected, this.model);

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();

            }
        }else if (actionEvent.getSource() == btnCustomers){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/CustomersView.fxml"));
                // loader.setLocation(Main.class.getResource("/view/LoginPageView.fxml"));;
                painnnnn.getChildren().setAll((Node) loader.load());
                CustomerPageController controller = loader.getController();
                controller.setMainModel("admin");
                selected = "Customers";
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();

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
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();              }

        }
    }


    public void setImage(javafx.scene.input.MouseEvent mouseEvent) throws SQLException, IOException {
        FileChooser layoutDrawingChooser = new FileChooser();
        Stage stage = new Stage();
        File selectedFile = layoutDrawingChooser.showOpenDialog(stage);
        Image layoutDrawing = new Image(selectedFile.getAbsolutePath());
        this.img = layoutDrawing.getUrl();
        System.out.println(layoutDrawing.getUrl());
        User user=this.model.getLogInUser();
        user.setImg(this.img);
        this.editModel.updateDatabaseElement(user,"User",user.getId());

        this.avatar=new Image(layoutDrawing.getUrl());
        this.avatarImage.setImage(avatar);


    }

    public void handleClick(MouseEvent mouseEvent) {
    }
}
