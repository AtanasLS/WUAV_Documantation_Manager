package main.java.gui.controllers.pageController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import main.java.be.Order;
import main.java.bll.utilties.Filter;
import main.java.gui.controllers.createController.CreateOrderController;
import main.java.gui.controllers.itemController.OrderItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    public TextField searchBar;
    public Label nameLabel, projectLabel, customerLabel, dateLabel;
    public Button createBtn;
    @FXML
    VBox pnItems = null;

    MainModel model;
    private Filter filter;
    private ObservableList<Order> allOrders;
    private String searchType;

    private ProgressIndicator progressIndicator;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        this.filter = new Filter();
        this.allOrders = FXCollections.observableArrayList();
        searchType = "name";
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                loadOrdersAsync(newValue);
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
    public void setModel(String type){
        if (type.equals("Seller") || type.equals("Technician")){
            createBtn.setVisible(false);
        }
        try {
            model.loadOrders();
            if (progressIndicator == null){
                progressIndicator = new ProgressIndicator();
            }
            progressIndicator.setVisible(false);
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
    private void loadOrdersAsync(String searchValue) {
        Task<ObservableList<Order>> task = new Task<ObservableList<Order>>() {
            @Override
            protected ObservableList<Order> call() throws Exception {
                return filter.searchOrder(searchValue, searchType);
            }
        };

        task.setOnSucceeded(event -> {
            allOrders = task.getValue();
            setPnItems(allOrders);
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
        }else if (mouseEvent.getSource() == customerLabel){
            searchType = "customer";
        }
    }

    public void createHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader ordersLoader = new FXMLLoader(getClass().getResource("/view/create/CreateOrder.fxml"));
        Parent ordersRoot = ordersLoader.load();
        CreateOrderController createOrderController = ordersLoader.getController();
        createOrderController.setModel(model);
        Stage orderStage = new Stage();
        orderStage.setScene(new Scene(ordersRoot));
        orderStage.show();
    }
}
