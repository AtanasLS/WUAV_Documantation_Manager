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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.be.Document;
import main.java.be.LogIns;
import main.java.bll.Filter;
import main.java.gui.controllers.createController.CreateLoginController;
import main.java.gui.controllers.itemController.LogInsItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInsController implements Initializable {
    public TextField searchBar;
    public Label usernameLabel, passwordLabel, projectLabel;
    @FXML
    VBox pnItems = null;
    @FXML
    ProgressIndicator progressIndicator;

    private String searchType;


        MainModel model;
        private Filter filter;
        private ObservableList<LogIns> allLogins;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            this.model = new MainModel();
            this.filter = new Filter();
            this.allLogins = FXCollections.observableArrayList();
            searchType = "username";
            searchBar.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    loadLoginsAsync(newValue);
                }
            });


            this.allLogins.addListener((ListChangeListener<LogIns>) ch -> {
                this.setPnItems(this.allLogins);
            });

        }
        public void setPnItems(ObservableList<LogIns> searchedLogIns){
            pnItems.getChildren().clear();

            Node[] nodes = new Node[searchedLogIns.size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/LogIns.fxml"));
                    nodes[i] = loader.load();
                    LogInsItemController controller = loader.getController();
                    controller.setSearchedItemLabel(i, searchedLogIns);

                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    alert.showAndWait();                  }
            }
        }
        public void setModel(){
            try {
                model.loadLogIns();
                if (progressIndicator == null){
                    progressIndicator = new ProgressIndicator();
                }
                progressIndicator.setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();              }
            Node[] nodes = new Node[model.getAllLogIns().size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/LogIns.fxml"));
                    nodes[i] = loader.load();
                    LogInsItemController controller = loader.getController();
                    controller.setLabels(i, model);

                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    alert.showAndWait();                  }
            }
        }

    private void loadLoginsAsync(String searchValue) {
        Task<ObservableList<LogIns>> task = new Task<ObservableList<LogIns>>() {
            @Override
            protected ObservableList<LogIns> call() throws Exception {
                return filter.searchLogIns(searchValue, searchType);
            }
        };

        task.setOnSucceeded(event -> {
            allLogins = task.getValue();
            setPnItems(allLogins);
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
        if (mouseEvent.getSource() == usernameLabel){
            searchType = "username";
        }else if (mouseEvent.getSource() == passwordLabel){
            searchType = "password";
        }else if (mouseEvent.getSource() == projectLabel){
            searchType = "project";
        }
    }

    public void createHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/view/create/CreateLogin.fxml"));
        Parent loginsRoot = loginLoader.load();
        CreateLoginController loginController = loginLoader.getController();
        loginController.setModel(model);
        Stage loginStage = new Stage();
        loginStage.setScene(new Scene(loginsRoot));
        loginStage.show();
    }
}
