package main.java.bll;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.*;

import java.sql.SQLException;

public class Filter {

    private AppLogicManager appLogicManager;

    public ObservableList<User> searchUsers(String input) throws SQLException {
        appLogicManager = new AppLogicManager();
        ObservableList<User> matchingUsers = FXCollections.observableArrayList();
        ObservableList<User> allUsers = appLogicManager.getAllUsersFromDatabase();

        for (User u: allUsers) {
            if (u.getUsername().toLowerCase().contains(input.toLowerCase())){
                matchingUsers.add(u);
            }
        }
        return matchingUsers;
    }
    public ObservableList<Document> searchDocument(String input) throws SQLException{
        appLogicManager = new AppLogicManager();
        ObservableList<Document> matchingDocuments = FXCollections.observableArrayList();
        ObservableList<Document> allDocuments = appLogicManager.getAllDocumentsFromDatabase();

        for (Document d: allDocuments) {
            if (d.getName().toLowerCase().contains(input.toLowerCase())){
                matchingDocuments.add(d);
            }
        }
        return matchingDocuments;
    }
    public ObservableList<Customer> searchCustomers(String input) throws SQLException{
        appLogicManager = new AppLogicManager();
        ObservableList<Customer> matchingCustomers = FXCollections.observableArrayList();
        ObservableList<Customer> allCustomers = appLogicManager.getAllCustomersFromDatabase();

        for (Customer c: allCustomers) {
            if (c.getFirstName().toLowerCase().contains(input.toLowerCase())){
                matchingCustomers.add(c);
            }
        }
        return matchingCustomers;
    }
    public ObservableList<LogIns> searchLogIns(String input) throws SQLException{
        appLogicManager = new AppLogicManager();
        ObservableList<LogIns> matchingLogins = FXCollections.observableArrayList();
        ObservableList<LogIns> allLogIns = appLogicManager.getAllLogInsFromDatabase();

        for (LogIns logIns: allLogIns) {
            if (logIns.getUsername().toLowerCase().contains(input.toLowerCase())){
                matchingLogins.add(logIns);
            }
        }
        return matchingLogins;
    }
    public ObservableList<Order> searchOrder(String input) throws SQLException{
        appLogicManager = new AppLogicManager();
        ObservableList<Order> matchingLogins = FXCollections.observableArrayList();
        ObservableList<Order> allOrders = appLogicManager.getAllOrdersFromDatabas();

        for (Order o: allOrders) {
            if (o.getName().toLowerCase().contains(input.toLowerCase())){
                matchingLogins.add(o);
            }
        }
        return matchingLogins;
    }
    public ObservableList<Project> searchProject(String input) throws SQLException{
        appLogicManager = new AppLogicManager();
        ObservableList<Project> matchingProjects = FXCollections.observableArrayList();
        ObservableList<Project> allProjects = appLogicManager.getAllProjectsFromDatabase();

        for (Project p: allProjects) {
            if (p.getType().toLowerCase().contains(input.toLowerCase())){
                matchingProjects.add(p);
            }
        }
        return matchingProjects;
    }

}
