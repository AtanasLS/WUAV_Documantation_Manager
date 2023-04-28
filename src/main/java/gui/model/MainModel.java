package main.java.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.Customer;
import main.java.be.Document;
import main.java.be.User;
import main.java.bll.AppLogicManager;

import java.sql.SQLException;

public class MainModel {


    private AppLogicManager appLogicManager;

    private final ObservableList<Customer> allCustomers;


    public MainModel(){
        this.appLogicManager = new AppLogicManager();
        this.allCustomers = FXCollections.observableArrayList();

    }

    public void loadInfo() throws SQLException {
        this.allCustomers.clear();
        this.allCustomers.addAll(appLogicManager.getAllCustomersFromDatabase());

    }

    public ObservableList<Customer> getAllCustomers(){
        return allCustomers;
    }


}
