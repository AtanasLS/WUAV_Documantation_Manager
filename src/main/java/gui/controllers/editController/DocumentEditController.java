package main.java.gui.controllers.editController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.be.*;
import main.java.gui.controllers.itemController.PhotoItemController;
import main.java.gui.model.CreateModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class DocumentEditController implements Initializable {


    public AnchorPane mainPane;
    public ImageView imageView;
    public VBox items;
    public Button createBtn, cancelBtn;

    public ArrayList<Image> allImages = new ArrayList<>();
    @FXML
    public TextArea documentDescription;
    public DatePicker date;
    public TextField documentName;
    public ComboBox loginBox, customerBox, technicianBox, projectBox;


    private Image layoutDrawing;

    private EditModel model;

    private int id;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMainModel(MainModel mvm) throws IOException, SQLException {
        model = new EditModel(mvm);
        System.out.println(mvm.getSelectedDocument());
        id = mvm.getSelectedDocument().getId();
        mvm.loadCustomers();
        mvm.loadProjects();
        mvm.loadTech();
        mvm.loadLogIns();
        documentDescription.setText(mvm.getSelectedDocument().getDescription());
        documentName.setText(mvm.getSelectedDocument().getName());
        date.setValue(mvm.getSelectedDocument().getDate());
        loginBox.setValue(mvm.getSelectedObject(mvm.getSelectedDocument().getLoginId(), "LogIn"));
        customerBox.setValue(mvm.getSelectedObject(mvm.getSelectedDocument().getCustomer(), "Customer"));
        technicianBox.setValue(mvm.getSelectedObject(mvm.getSelectedDocument().getUser(), "User"));
        projectBox.setValue(mvm.getSelectedObject(mvm.getSelectedDocument().getProject(), "Project"));
        loginBox.setItems(mvm.getAllLogIns());
        technicianBox.setItems(mvm.getAllTech());
        customerBox.setItems(mvm.getAllCustomers());
        projectBox.setItems(mvm.getAllProjects());
        Node node;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/PhotoItem.fxml"));
        node = loader.load();
        PhotoItemController controller = loader.getController();
        System.out.println(mvm.getSelectedDocument().getLayoutDrawing());
         this.layoutDrawing = new Image("/images/"+mvm.getSelectedDocument().getName()+".png");
        controller.setItems(
                layoutDrawing, "photo");
        items.getChildren().add(node);
        //allImages.add(selectedImage0);
    }


    public void insertPhoto(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        Image selectedImage = new Image(selectedFile.getPath());
        Node node;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/PhotoItem.fxml"));
        node = loader.load();
        PhotoItemController controller = loader.getController();
        controller.setItems(selectedImage, selectedFile.getName());
        items.getChildren().add(node);
        allImages.add(selectedImage);
    }



    public void editDocument(ActionEvent actionEvent) throws SQLException {
        LogIns selectedLogin = (LogIns) loginBox.getSelectionModel().getSelectedItem();
        User selectedUser = (User) technicianBox.getSelectionModel().getSelectedItem();
        Customer selectedCustomer = (Customer) customerBox.getSelectionModel().getSelectedItem();
        Project selectedProject = (Project) projectBox.getSelectionModel().getSelectedItem();

        Document editedDocument = new Document(this.id,layoutDrawing.getUrl(), documentDescription.getText(), selectedLogin.getId(),
                documentName.getText(),selectedUser.getId(), selectedCustomer.getId() ,selectedProject.getProjectId(),
                date.getValue(), 1);
        model.updateDatabaseElement(editedDocument, "Document", this.id);
    }

    public void handleCancel(ActionEvent actionEvent) {

    }

    public void createDrawing(ActionEvent actionEvent) {
    }
}
