package main.java.gui.controllers.pageController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.java.gui.controllers.itemController.OrderItemController;
import main.java.gui.controllers.itemController.UserItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    VBox pnItems = null;
    MainModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setMainModel(String type) {
        this.model = new MainModel();
        if (type.equals("User")) {
            try {
                model.loadUsers();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(model.getAllUsers());
            Node[] nodes = new Node[model.getAllUsers().size()];
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
}
