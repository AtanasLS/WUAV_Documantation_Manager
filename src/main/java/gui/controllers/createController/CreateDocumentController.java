package main.java.gui.controllers.createController;

import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.be.*;
import main.java.bll.AppLogicManager;
import main.java.bll.PDFGenerator;
import main.java.gui.controllers.itemController.CustomerItemController;
import main.java.gui.controllers.itemController.PhotoItemController;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateDocumentController implements Initializable {


    public AnchorPane mainPane;
    public ImageView imageView;
    public VBox items;
    public Button createBtn, cancelBtn;

    public ArrayList<File> allImages = new ArrayList<>();
    @FXML
    public TextArea documentDescription;
    public DatePicker date;
    public TextField documentName;
    public ComboBox loginBox, customerBox, technicianBox, projectBox;

    private CreateModel createModel;
    private String layoutDrawing;

    private ObservableList<Document> allDocs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        documentDescription.appendText("\n");
        documentDescription.setPrefColumnCount(20);
        this.allDocs = FXCollections.observableArrayList();
    }


    public void setModel(MainModel mainModel) throws SQLException {
        createModel = new CreateModel(mainModel);
        mainModel.loadTech();
        mainModel.loadLogIns();
        mainModel.loadProjects();
        mainModel.loadCustomers();
        loginBox.setItems(mainModel.getAllLogIns());
        customerBox.setItems(mainModel.getAllCustomers());
        technicianBox.setItems(mainModel.getAllTech());
        projectBox.setItems(mainModel.getAllProjects());


        allDocs.addAll(mainModel.getAllDocuments());
        System.out.println(allDocs.size());
        System.out.println(allDocs.get(allDocs.size() - 1).getId());

    }
    public void createDrawing(ActionEvent actionEvent) throws IOException {
        FileChooser layoutDrawingChooser = new FileChooser();
        Stage stage = new Stage();
        File selectedFile = layoutDrawingChooser.showOpenDialog(stage);
        Image layoutDrawing = new Image(selectedFile.getPath());
        Node node;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/PhotoItem.fxml"));
        node = loader.load();
        PhotoItemController controller = loader.getController();
        controller.setItems(layoutDrawing, selectedFile.getName());
        items.getChildren().add(node);
        this.layoutDrawing = layoutDrawing.getUrl();


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
        allImages.add(selectedFile);



    }


    public void createDocument(ActionEvent actionEvent) throws DocumentException, IOException {
        /*
        DirectoryChooser directoryChooser = new DirectoryChooser();
       // directoryChooser.setInitialDirectory(new File("src"));
        Stage stage = new Stage();
        File selectedDirectory = directoryChooser.showDialog(stage);
        PDFGenerator pdfGenerator = new PDFGenerator();
        System.out.println(selectedDirectory.getPath());
        String path = selectedDirectory.getPath();
        pdfGenerator.generatePDF(path, documentName.getText(), documentDescription.getText(), layoutDrawing.getUrl());

         */
        LogIns selectedLogin = (LogIns) loginBox.getSelectionModel().getSelectedItem();
        User selectedUser = (User) technicianBox.getSelectionModel().getSelectedItem();
        Customer selectedCustomer = (Customer) customerBox.getSelectionModel().getSelectedItem();
        Project selectedProject = (Project) projectBox.getSelectionModel().getSelectedItem();




        Document newDocument = new Document(layoutDrawing, documentDescription.getText(), selectedLogin.getId(), documentName.getText(),
               selectedUser.getId(), selectedCustomer.getId(), selectedProject.getProjectId(), date.getValue(), 0);
        createModel.createInDatabase(newDocument, "Document");

        for (File i: allImages) {
            Picture picture = new Picture(i.getName(), i.getPath(), allDocs.get(allDocs.size()-1).getId() + 1);
            
            createModel.createInDatabase(picture, "Picture");
        }


        Stage currentStage = (Stage) createBtn.getScene().getWindow();
        currentStage.close();


    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage currentStage = (Stage) cancelBtn.getScene().getWindow();
        currentStage.close();
    }
}
