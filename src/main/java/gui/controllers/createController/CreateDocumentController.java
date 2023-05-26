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

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.be.*;
import main.java.bll.utilties.PDFGenerator;
import main.java.gui.controllers.itemController.PhotoItemController;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CreateDocumentController implements Initializable, CreateController {


    public AnchorPane mainPane;
    public VBox items;
    public Button createBtn, cancelBtn;

    public ArrayList<File> allImages = new ArrayList<>();
    @FXML
    public TextArea documentDescription;
    public DatePicker date;

    @FXML
    public CheckBox includeDate, includeLogin, includeCustomer, includeTechnicians, includeProject, includePhotos, includeDescription;
    public TextField documentName;
    public ComboBox<LogIns> loginBox;
    public ComboBox<Customer> customerBox;
    public ComboBox<User> technicianBox;
    public ComboBox<Project> projectBox;
    private CreateModel createModel;
    private String layoutDrawing;

    private ObservableList<Document> allDocs;

    private ArrayList<Boolean> includes;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        documentDescription.appendText("\n");
        documentDescription.setPrefColumnCount(20);
        this.allDocs = FXCollections.observableArrayList();
        this.includes = new ArrayList<>();

        this.checkData();

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

    }

    @Override
    public void handleSave(ActionEvent actionEvent) throws DocumentException, IOException {

        includes.add(includeDate.isSelected());
        includes.add(includeLogin.isSelected());
        includes.add(includeCustomer.isSelected());
        includes.add(includeTechnicians.isSelected());
        includes.add(includeProject.isSelected());
        includes.add(includePhotos.isSelected());
        includes.add(includeDescription.isSelected());


        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = new Stage();
        File selectedDirectory = directoryChooser.showDialog(stage);
        String path = selectedDirectory.getPath();

        LogIns selectedLogin = loginBox.getValue();
        User selectedUser = technicianBox.getValue();
        Customer selectedCustomer = customerBox.getValue();
        Project selectedProject = projectBox.getValue();

        //generating the .pdf file
        PDFGenerator pdfGenerator = new PDFGenerator();
        pdfGenerator.generatePDF(path, documentName.getText(), selectedCustomer, selectedProject, selectedLogin,
                documentDescription.getText(), allImages, layoutDrawing, includes);
        //creating a new Document in the Database
        Document newDocument = new Document(layoutDrawing, documentDescription.getText(), selectedLogin.getId(),
                documentName.getText(), selectedUser.getId(), selectedCustomer.getId(), selectedProject.getProjectId(),
                date.getValue(), 0);
        createModel.createInDatabase(newDocument, "Document");

        //creating new Picture connected to the Document in the Database
        for (File file : allImages) {
            int docId = (allDocs.size() > 0) ? allDocs.get(allDocs.size() - 1).getId() + 1 : 1;
            Picture picture = new Picture(file.getName(), file.getAbsolutePath(), docId);
            createModel.createInDatabase(picture, "Picture");
        }

        Stage currentStage = (Stage) createBtn.getScene().getWindow();
        currentStage.close();
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

    public void handleCancel(ActionEvent actionEvent) {
        Stage currentStage = (Stage) cancelBtn.getScene().getWindow();
        currentStage.close();
    }

    @Override
    public void checkData(){
        Pattern name = Pattern.compile("[A-Za-z\\s]{1,}");
        TextFormatter<String> formatter = new TextFormatter<>( change -> {
            System.out.println(change.getControlNewText().matches("[A-Za-z\\s]{1,}"));
            if (change.getControlNewText().matches(name.pattern())) {
                System.out.println("work");
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        TextFormatter<String> formatter1 = new TextFormatter<>(change -> {
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        TextFormatter<String> formatter2 = new TextFormatter<>(change -> {
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern mail = Pattern.compile("[A-Za-z1-9]{2,}@[A-Za-z1-9].{1,}");
        TextFormatter<String> formatterMail = new TextFormatter<>(change -> {
            if (mail.matcher(change.getControlNewText()).matches()) {
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern phone = Pattern.compile("\\+?\\d[\\d-\\s]{1,}");
        TextFormatter<String> formatterPhone = new TextFormatter<>(change -> {
            if (phone.matcher(change.getControlNewText()).matches()) {
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern address = Pattern.compile("[A-Za-z0-9\\s,.]+");
        TextFormatter<String> formatterAddress = new TextFormatter<>(change -> {
            if (address.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern pass = Pattern.compile("[A-Za-z\\s1-9\\s]{1,}");
        TextFormatter<String> formatterPass = new TextFormatter<>(change -> {
            if (pass.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        documentName.setTextFormatter(formatter1);




    }
}
