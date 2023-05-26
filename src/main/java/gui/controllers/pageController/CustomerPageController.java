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
import main.java.be.Customer;
import main.java.bll.utilties.Filter;
import main.java.gui.controllers.createController.CreateCustomerController;
import main.java.gui.controllers.itemController.CustomerItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {

    public TextField searchBar;
    public Button createBtn;
    @FXML
    VBox pnItems = null;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    public Label lastNameLabel, firstNameLabel, emailLabel, addressLabel;

    MainModel model;
    private Filter filter;
    private ObservableList<Customer> allCustomers;
    private String searchedType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel() ;
        this.allCustomers = FXCollections.observableArrayList();
        filter = new Filter();
        searchedType = "first name";
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                loadCustomersAsync(newValue);
            }
        });

        model.getAllCustomers().addListener((ListChangeListener.Change<? extends Customer> change) -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                    allCustomers.setAll(model.getAllCustomers());
                    setPnItems(allCustomers);
                }
            }
  

        this.allCustomers.addListener((ListChangeListener<Customer>) ch -> {
           this.setPnItems(allCustomers);
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

    public void setMainModel(String type) {
        if (type.equals("Seller") || type.equals("Technician")){
            createBtn.setVisible(false);
        }
        try {
            model.loadCustomers();
            if (progressIndicator == null) {
                progressIndicator = new ProgressIndicator();
            }

            progressIndicator.setVisible(false);
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

    private void loadCustomersAsync(String searchValue) {
        Task<ObservableList<Customer>> task = new Task<ObservableList<Customer>>() {
            @Override
            protected ObservableList<Customer> call() throws Exception {
                return filter.searchCustomers(searchValue, searchedType);
            }
        };

        task.setOnSucceeded(event -> {
            allCustomers = task.getValue();
            setPnItems(allCustomers);
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
        if (mouseEvent.getSource() == firstNameLabel){
            searchedType = "first name";
        }else if (mouseEvent.getSource() == lastNameLabel){
            searchedType = "last name";
        }else if (mouseEvent.getSource() == emailLabel){
            searchedType = "email";
        }else if (mouseEvent.getSource() == addressLabel){
            searchedType = "address";
        }
    }

    public void createHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader customerLoader = new FXMLLoader(getClass().getResource("/view/create/CustomerCreate.fxml"));
        Parent customerRoot = customerLoader.load();
        CreateCustomerController createCustomerController = customerLoader.getController();
        createCustomerController.setModel(model);
        Stage customerStage = new Stage();
        customerStage.setScene(new Scene(customerRoot));
        customerStage.show();
    }
}