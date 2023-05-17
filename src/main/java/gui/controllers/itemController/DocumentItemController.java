package main.java.gui.controllers.itemController;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.be.Document;
import main.java.be.Project;
import main.java.gui.controllers.editController.DocumentEditController;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DocumentItemController implements Initializable {
    public Label documentName,  date, project;
    private MainModel model;

    private DeleteModel deleteModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        this.deleteModel = new DeleteModel();
    }

    public void setLabels(int numberOfElement) {
        Task<Void> loadTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                model.loadDocument();
                return null;
            }
        };
        loadTask.setOnSucceeded(event -> {
            model.setSelectedDocument(model.getAllDocuments().get(numberOfElement));
            documentName.setText(model.getAllDocuments().get(numberOfElement).getName());
            date.setText(model.getAllDocuments().get(numberOfElement).getDate().toString());
            loadProjectData(model.getAllDocuments().get(numberOfElement).getProject());
        });
        Thread loadThread = new Thread(loadTask);
        loadThread.start();
    }

    private void loadProjectData(int projectId) {
        Task<Project> projectTask = new Task<Project>() {
            @Override
            protected Project call() throws Exception {
                return (Project) model.getSelectedObject(projectId, "Project");
            }
        };
        projectTask.setOnSucceeded(event -> {
            Project selectedProject = projectTask.getValue();
            if (selectedProject != null) {
                project.setText(selectedProject.getType());
            }
        });
        Thread projectThread = new Thread(projectTask);
        projectThread.start();
    }

    public void setSearchedItems(int numberOfElement, ObservableList<Document> documents) {
        model.setSelectedDocument(documents.get(numberOfElement));
        documentName.setText(documents.get(numberOfElement).getName());
        date.setText(documents.get(numberOfElement).getDate().toString());
        loadProjectData(documents.get(numberOfElement).getProject());
    }

    public void infoBtnHandle(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit/DocumentEdit.fxml"));
        Parent root = loader.load();
        DocumentEditController controller = loader.getController();
        controller.setMainModel(model);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.show();
    }

    public void handleDelete(ActionEvent actionEvent) {
        deleteModel.deleteFromDatabase(model.getSelectedDocument().getId(), "Document");

    }
}