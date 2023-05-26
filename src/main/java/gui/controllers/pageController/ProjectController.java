package main.java.gui.controllers.pageController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.be.Document;
import main.java.be.Project;
import main.java.be.User;
import main.java.bll.Filter;
import main.java.gui.controllers.createController.CreateProjectController;
import main.java.gui.controllers.itemController.ProjectItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProjectController implements Initializable{
    public Label mostSoldProduct;
    public TextField searchBar;
    public Button createBtn, setBtn;
    @FXML
    VBox pnItems = null;
    @FXML
    public Label customerLabel, typeLabel;
    @FXML
    private ProgressIndicator progressIndicator;

    MainModel model;

    private String searchType;



    private Filter filter;
    private ObservableList<Project> allProjects;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        this.filter = new Filter();
        this.allProjects = FXCollections.observableArrayList();
        searchType = "type";
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               loadProjectAsync(newValue);
            }
        });

        this.allProjects.addListener((ListChangeListener<Project>) ch -> {
            this.setPnItems(this.allProjects);
        });
    }

    public void setPnItems(ObservableList<Project> searchedProjects) {
        pnItems.getChildren().clear();
        Node[] nodes = new Node[searchedProjects.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/ProjectItem.fxml"));
                nodes[i] = loader.load();
                ProjectItemController controller = loader.getController();

                controller.setSearchedProjects(i, searchedProjects);

                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void setModel(User loggedUser){
        if (loggedUser.getType().equals("Seller") || loggedUser.getType().equals("Technician")){
            createBtn.setVisible(false);
            setBtn.setVisible(false);
        }
        if (loggedUser.getType().equals("Seller") || loggedUser.getType().equals("Admin") || loggedUser.getType().equals("TechnicianManager")) {
            try {
                model.loadProjects();
                if (progressIndicator == null) {
                    progressIndicator = new ProgressIndicator();
                }
                progressIndicator.setVisible(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Node[] nodes = new Node[model.getAllProjects().size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/ProjectItem.fxml"));
                    nodes[i] = loader.load();
                    ProjectItemController controller = loader.getController();

                    controller.setLabels(i, model,loggedUser);

                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                model.loadProjects();
                model.loadTech();
                model.loadProjectToUser();

                if (progressIndicator == null) {
                    progressIndicator = new ProgressIndicator();
                }
                progressIndicator.setVisible(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Node[] nodes = new Node[model.getAllProjectToUser(loggedUser).size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/ProjectItem.fxml"));
                    nodes[i] = loader.load();
                    ProjectItemController controller = loader.getController();

                    controller.setLabels(i, model, loggedUser);

                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadProjectAsync(String searchValue) {
        Task<ObservableList<Project>> task = new Task<ObservableList<Project>>() {
            @Override
            protected ObservableList<Project> call() throws Exception {
                return filter.searchProject(searchValue, searchType);
            }
        };

        task.setOnSucceeded(event -> {
            allProjects = task.getValue();
            setPnItems(allProjects);
            progressIndicator.setVisible(false);
        });

        task.setOnFailed(event -> {
            Throwable e = task.getException();
            e.printStackTrace();
        });

        progressIndicator.setVisible(true);
        new Thread(task).start();
    }
    public void setMostSoldProduct() throws SQLException {

    }

    public void handleClicks(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == typeLabel){
            searchType = "type";
        }else if (mouseEvent.getSource() == customerLabel){
            searchType = "customer";
        }
    }

    public void createHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader projectLoader = new FXMLLoader(getClass().getResource("/view/create/CreateProject.fxml"));
        Parent projectsRoot = projectLoader.load();
        CreateProjectController createProjectController = projectLoader.getController();
        createProjectController.setModel(model);
        Stage projectStage = new Stage();
        projectStage.setScene(new Scene(projectsRoot));
        projectStage.show();
    }

    public void setHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader projectLoader = new FXMLLoader(getClass().getResource("/view/create/SetProjectToUser.fxml"));
        Parent projectsRoot = projectLoader.load();

        Stage projectStage = new Stage();
        projectStage.setScene(new Scene(projectsRoot));
        projectStage.show();

    }
}
