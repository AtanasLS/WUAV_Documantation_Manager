package main.java.gui.controllers.createController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.be.Document;
import main.java.be.LogIns;
import main.java.gui.controllers.itemController.CustomerItemController;
import main.java.gui.controllers.itemController.PhotoItemController;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateDocumentController implements Initializable {


    public AnchorPane mainPane;
    public ImageView imageView;
    public VBox items;

    public ArrayList<Image> allImages = new ArrayList<>();
    @FXML
    public TextArea documentDescription;
    public DatePicker date;
    public TextField documentName;
    public ComboBox loginBox, customerBox, technicianBox, projectBox;

    private CreateModel createModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setModel(MainModel mainModel){
        createModel = new CreateModel(mainModel);
        loginBox.setItems(mainModel.getAllLogIns());
        customerBox.setItems(mainModel.getAllCustomers());
        technicianBox.setItems(mainModel.getAllTech());
        projectBox.setItems(mainModel.getAllProjects());


    }
    public void createDrawing(ActionEvent actionEvent) {

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


    public void createDocument(ActionEvent actionEvent) {
        System.out.println(customerBox.getSelectionModel().getSelectedItem());
        //Document newDocument = new Document(allImages.get(0), documentDescription.getText(), date.getValue());
        //createModel.createInDatabase(document, "Document");

    }
}
