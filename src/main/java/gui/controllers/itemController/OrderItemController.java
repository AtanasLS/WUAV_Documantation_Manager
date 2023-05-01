package main.java.gui.controllers.itemController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderItemController implements Initializable,Items {

    public Label oName, pName, customer,date,price;
    private MainModel model ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        try {
            this.model.loadOrders();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setLabels(int numberOfElement, MainModel model) {
        model.setSelectedOrder(this.model.getAllOrders().get(numberOfElement));
        oName.setText(this.model.getAllOrders().get(numberOfElement).getName());
        pName.setText(this.model.getAllOrders().get(numberOfElement).getProject());
        customer.setText(this.model.getAllOrders().get(numberOfElement ).getCustomer());
        date.setText(this.model.getAllOrders().get(numberOfElement ).getDate().toString());
        price.setText(String.valueOf(this.model.getAllOrders().get(numberOfElement ).getPrice()));


    }
}
