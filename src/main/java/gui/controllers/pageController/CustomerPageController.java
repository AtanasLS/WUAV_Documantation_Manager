package main.java.gui.controllers.pageController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.java.be.Customer;
import main.java.bll.Filter;
import main.java.gui.controllers.itemController.CustomerItemController;
import main.java.gui.controllers.itemController.UserItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {

    public TextField searchBar;
    @FXML
    VBox pnItems = null;

    MainModel model;
    private Filter filter;
    private ObservableList<Customer> allCustomers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel() ;
        this.allCustomers = FXCollections.observableArrayList();
        filter = new Filter();

        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                allCustomers.clear();
                try {
                    allCustomers.addAll(filter.searchCustomers(newValue));
                    setPnItems(allCustomers);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    public void setPnItems(ObservableList<Customer> searchedCustomers) {
        pnItems.getChildren().clear();


        Node[] nodes = new Node[searchedCustomers.size()];
        for (int i = 0; i < nodes.length; i++) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/CustomerItem.fxml"));
                nodes[i] = loader.load();
                CustomerItemController controller = loader.getController();
                controller.setSearchedItems(i, searchedCustomers);
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void setMainModel() {
        try {
            model.loadCustomers();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Node[] nodes = new Node[model.getAllCustomers().size()];
        for (int i = 0; i < nodes.length; i++) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/CustomerItem.fxml"));
                nodes[i] = loader.load();
                CustomerItemController controller = loader.getController();
                controller.setLabels(i);
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

