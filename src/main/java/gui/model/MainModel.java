package main.java.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.*;
import main.java.bll.AppLogicManager;

import java.sql.SQLException;
import java.util.List;

public class MainModel {


    private AppLogicManager appLogicManager;

    private final ObservableList<Customer> allCustomers;
    private final ObservableList<Document> allDocuments;
    private final ObservableList<LogIns> allLogIns;
    private final ObservableList<Order> allOrders;

    private final ObservableList<Project> allProjects;

    private final ObservableList<User> allUsers;

    private final ObservableList<User> allTech;

    private User loggedInUser;

    private Customer selectedCustomer;

    private Order selectedOrder;

<<<<<<< Updated upstream
=======
    private User selectedUser;

    private Project mostSelled;

>>>>>>> Stashed changes




    public MainModel(){
        this.appLogicManager = new AppLogicManager();
        this.allCustomers = FXCollections.observableArrayList();
        this.allDocuments=FXCollections.observableArrayList();
        this.allLogIns=FXCollections.observableArrayList();
        this.allOrders=FXCollections.observableArrayList();
        this.allProjects=FXCollections.observableArrayList();
        this.allUsers=FXCollections.observableArrayList();
        this.allTech=FXCollections.observableArrayList();
        this.loggedInUser = null;
        this.selectedCustomer = null;

    }

    public void loadCustomers() throws SQLException {
        this.allCustomers.clear();
        this.allCustomers.addAll(appLogicManager.getAllCustomersFromDatabase());

    }

    public void loadDocument() throws SQLException {
        this.allDocuments.clear();
        this.allDocuments.addAll(appLogicManager.getAllDocumentsFromDatabase());

    }

    public void loadLogIns() throws SQLException {
        this.allLogIns.clear();
        this.allLogIns.addAll(appLogicManager.getAllLogInsFromDatabase());

    }

    public void getTheMOstSelledProject() throws SQLException {
        this.mostSelled=this.appLogicManager.getMostSelledProject();
    }

    public void loadOrders() throws SQLException {
        this.allOrders.clear();
        this.allOrders.addAll(appLogicManager.getAllOrdersFromDatabas());

    }

    public void loadProjects() throws SQLException {
        this.allProjects.clear();
        this.allProjects.addAll(appLogicManager.getAllProjectsFromDatabase());

    }

    public void loadUsers() throws SQLException {
        this.allUsers.clear();
        this.allUsers.addAll(appLogicManager.getAllUsersFromDatabase());

    }

    public void loadTech() throws SQLException {
        this.allTech.clear();
        this.allTech.addAll(appLogicManager.getAllTechnicians());

    }

    public ObservableList<Customer> getAllCustomers(){
        return this.allCustomers;
    }

    public ObservableList<Order> getAllOrders(){
        return this.allOrders;
    }

    public ObservableList<Document> getAllDocuments(){
        return this.allDocuments;
    }

    public ObservableList<LogIns> getAllLogIns(){
        return this.allLogIns;
    }

    public ObservableList<Project> getAllProjects(){
        return this.allProjects;
    }

    public ObservableList<User> getAllUsers(){
        return this.allUsers;
    }

    public ObservableList<User> getAllTech(){
        return this.allTech;
    }

    public boolean checkIfUserExist(String username, String password){
        List<User> users = this.getAllUsers();
        for (User u: users ) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                loggedInUser = u;
                return true;
            }
        }
            return false;
    }
    public Customer getSelectedCustomer(){
        return selectedCustomer;
    }
    public void setSelectedCustomer(Customer customer){
        this.selectedCustomer = customer;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public User getUser(String username) throws Exception {
        List<User> allUsers = getAllUsers();
        for (User u:allUsers) {
            if (u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

}
