package main.java.gui.controllers.itemController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.gui.controllers.infoPageController.CustomerInfoController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerItemController implements Initializable,Items {
    public Label firstNameLabel, lastNameLabel, emailLabel, adress1Label;
    public Label infoNameLabel;
    private MainModel model ;

    private Customer currentCustomer;
    @Override
    public void initialize(URL location, ResourceBundle resources) {}



    public void infoBtnHandle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/info/CustomerInfoView.fxml"));
        Parent root = loader.load();
        CustomerInfoController customerInfoController = loader.getController();
        customerInfoController.setMainModel(model);
        customerInfoController.setInfoLabels();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void setLabels(int numberOfElement, MainModel model) {
        this.model = model;
        try {
            this.model.loadCustomers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.setSelectedCustomer(model.getAllCustomers().get(numberOfElement));
        firstNameLabel.setText(this.model.getSelectedCustomer().getFirstName());
        lastNameLabel.setText(this.model.getSelectedCustomer().getLastName());
        emailLabel.setText(this.model.getSelectedCustomer().getEmail());
        adress1Label.setText(this.model.getSelectedCustomer().getAddress());
    }
}
