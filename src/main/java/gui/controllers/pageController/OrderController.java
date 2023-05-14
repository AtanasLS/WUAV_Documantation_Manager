package main.java.gui.controllers.pageController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.java.be.Order;
import main.java.bll.Filter;
import main.java.gui.controllers.itemController.DocumentItemController;
import main.java.gui.controllers.itemController.OrderItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    public TextField searchBar;
    @FXML
    VBox pnItems = null;

    MainModel model;
    private Filter filter;
    private ObservableList<Order> allOrders;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        this.filter = new Filter();
        this.allOrders = FXCollections.observableArrayList();

        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                allOrders.clear();
                try {
                    allOrders.addAll(filter.searchOrder(newValue));
                    setPnItems(allOrders);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void setPnItems(ObservableList<Order> searchedOrders){
        pnItems.getChildren().clear();
        Node[] nodes = new Node[searchedOrders.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/OrderItem.fxml"));
                nodes[i] = loader.load();
                OrderItemController controller = loader.getController();
                controller.setSearchedItems(i, searchedOrders);
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void setModel(){
        try {
            model.loadOrders();
            System.out.println(model.getAllOrders());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Node[] nodes = new Node[model.getAllOrders().size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/OrderItem.fxml"));
                nodes[i] = loader.load();
                OrderItemController controller = loader.getController();
                controller.setLabels(i);

                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
