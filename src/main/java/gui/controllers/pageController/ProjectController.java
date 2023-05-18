package main.java.gui.controllers.pageController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import main.java.be.Project;
import main.java.bll.Filter;
import main.java.gui.controllers.itemController.ProjectItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProjectController implements Initializable{
    public Label mostSoldProduct;
    public TextField searchBar;
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

    public void setModel(){
        try {
            model.loadProjects();
            if (progressIndicator == null){
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

                controller.setLabels(i, model);

                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
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
        mostSoldProduct.setText("Most sold product: " + model.getTheMOstSelledProject().getType());
    }

    public void handleClicks(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == typeLabel){
            searchType = "type";
        }else if (mouseEvent.getSource() == customerLabel){
            searchType = "customer";
        }
    }
}
