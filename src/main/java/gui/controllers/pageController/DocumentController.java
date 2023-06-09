package main.java.gui.controllers.pageController;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.be.Document;

import main.java.bll.utilties.Filter;
import main.java.gui.controllers.createController.CreateDocumentController;
import main.java.gui.controllers.itemController.DocumentItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DocumentController implements Initializable {

    public TextField searchBar;
    public Label projectLabel, nameLabel;
    @FXML
    VBox pnItems = null;

    MainModel model;

    @FXML
    private ProgressIndicator progressIndicator;


    private Filter filter;

    private ObservableList<Document> allDocs;
    private String searchType;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        this.filter = new Filter();
        this.allDocs = FXCollections.observableArrayList();

        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
             loadDocumentsAsync(newValue);
            }
        });

        searchType = "name";


    }
    public void setPnItemsSearched(ObservableList<Document> searchedDocuments) {
        pnItems.getChildren().clear();

        Node[] nodes = new Node[searchedDocuments.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/DocumentItem.fxml"));
                nodes[i] = loader.load();
                DocumentItemController controller = loader.getController();
                controller.setSearchedItems(i, searchedDocuments);
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();              }

        }
    }

        public void setPnItems(String type) {
        if (type.equals("Users")) {
            try {
                model.loadDocument();
                if (progressIndicator == null) {
                    progressIndicator = new ProgressIndicator();
                }

                progressIndicator.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();              }



            Node[] nodes = new Node[model.getAllDocuments().size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/DocumentItem.fxml"));
                    nodes[i] = loader.load();
                    DocumentItemController controller = loader.getController();
                    controller.setLabels(i);
                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();

                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    alert.showAndWait();                  }
            }
        }else if (type.equals("Technician")){

            try {
                model.loadEditedDocuments();
                if (progressIndicator == null) {
                    progressIndicator = new ProgressIndicator();
                }

                progressIndicator.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();              }


            Node[] nodes = new Node[model.getEditedDocuments().size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/DocumentItem.fxml"));
                    nodes[i] = loader.load();
                    DocumentItemController controller = loader.getController();
                    controller.setLabels(i);
                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    alert.showAndWait();                  }

            }
        }


        }


    public void createDocument(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/create/CreateDocumentView.fxml"));
        Parent root = loader.load();
        CreateDocumentController controller = loader.getController();
        controller.setModel(model);
        //controller.setInformation();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void loadDocumentsAsync(String searchValue) {
        Task<ObservableList<Document>> task = new Task<ObservableList<Document>>() {
            @Override
            protected ObservableList<Document> call() throws Exception {
                return filter.searchDocument(searchValue, searchType);
            }
        };

        task.setOnSucceeded(event -> {
            allDocs = task.getValue();
            setPnItemsSearched(allDocs);
            progressIndicator.setVisible(false);
        });

        task.setOnFailed(event -> {
            Throwable e = task.getException();
            e.printStackTrace();
        });

        progressIndicator.setVisible(true);
        new Thread(task).start();
    }

    public void handleClicks(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == nameLabel){
            searchType = "name";
        }else if (mouseEvent.getSource() == projectLabel){
            searchType = "project";
        }
    }

    public void createHandle(ActionEvent actionEvent) {

    }
}
