package main.java.gui.controllers.pageController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.java.be.User;
import main.java.bll.Filter;
import main.java.gui.controllers.itemController.OrderItemController;
import main.java.gui.controllers.itemController.UserItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public TextField searchBar;
    @FXML
    VBox pnItems = null;
    MainModel model;

    private Filter filter;

    private ObservableList<User> allUsers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filter = new Filter();
        this.allUsers = FXCollections.observableArrayList();
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                allUsers.clear();
                try {
                    allUsers.addAll(filter.searchUsers(newValue));
                    setPnItems(allUsers);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    public void setMainModel(String type) {
        this.model = new MainModel();
        if (type.equals("User")) {
            try {
                model.loadUsers();
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
        }else if (type.equals("Technician")){
            try {
                model.loadTech();
                System.out.println(model.getAllTech());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(model.getAllTech());
            Node[] nodes = new Node[model.getAllTech().size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/UserItem.fxml"));
                    nodes[i] = loader.load();

                    UserItemController controller = loader.getController();
                    controller.setLabels(i, "Technician");


                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        System.out.println(selectedUsers);
    }


    public void searchHandle(MouseEvent mouseEvent) throws SQLException {
        setPnItems(filter.searchUsers(searchBar.getText()));
    }
}
