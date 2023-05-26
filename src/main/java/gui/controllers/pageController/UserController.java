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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.be.Document;
import main.java.be.User;
import main.java.bll.utilties.Filter;
import main.java.gui.controllers.createController.UserCreateController;
import main.java.gui.controllers.itemController.UserItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public TextField searchBar;
    @FXML
    Label usernameLabel, firstNameLabel;
    @FXML
    VBox pnItems = null;
    MainModel model;

    private Filter filter;

    private ObservableList<User> allUsers;

    @FXML
    private ProgressIndicator progressIndicator;

    private String searchType;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filter = new Filter();
        this.allUsers = FXCollections.observableArrayList();
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               loadUsersAsync(newValue);
            }
        });
        searchType = "username";
        this.model = new MainModel();
        model.getAllUsers().addListener((ListChangeListener.Change<? extends User> change) -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                    allUsers.setAll(model.getAllUsers());
                    setPnItems(allUsers);
                }
            }

        this.allUsers.addListener((ListChangeListener<User>) ch -> {
            this.setPnItems(this.allUsers);
        });
    }

    public void setMainModel(String type, MainModel model) {
        this.model = model ;

            try {
                model.loadUsers();
                if (progressIndicator == null) {
                    progressIndicator = new ProgressIndicator();
                }
                progressIndicator.setVisible(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.allUsers = model.getAllUsers();
            Node[] nodes = new Node[allUsers.size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/UserItem.fxml"));
                    nodes[i] = loader.load();

                    UserItemController controller = loader.getController();
                    controller.setLabels(i, "User");


                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    public void setPnItems(ObservableList<User> selectedUsers) {
        pnItems.getChildren().clear();
        Node[] nodes = new Node[selectedUsers.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/UserItem.fxml"));
                nodes[i] = loader.load();
                UserItemController controller = loader.getController();
                controller.setSearchedItems(i, selectedUsers);
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void loadUsersAsync(String searchValue) {
        Task<ObservableList<User>> task = new Task<ObservableList<User>>() {
            @Override
            protected ObservableList<User> call() throws Exception {
                return filter.searchUsers(searchValue,searchType);
            }
        };

        task.setOnSucceeded(event -> {
            allUsers = task.getValue();
            setPnItems(allUsers);
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
            System.out.println("work!!!");
            searchType = "username";
        }else if (mouseEvent.getSource() == firstNameLabel){
            searchType = "first name";
        }

    }

    public void createHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader userLoader = new FXMLLoader(getClass().getResource("/view/create/UserCreate.fxml"));
        Parent root = userLoader.load();
        UserCreateController controller = userLoader.getController();
        controller.setModel(model);
        Stage userStage = new Stage();
        userStage.setScene(new Scene(root));
        userStage.show();
    }
}
