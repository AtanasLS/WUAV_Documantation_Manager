package main.java.bll;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import main.java.be.*;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.atomic.AtomicReference;

public class Filter {

    private AppLogicManager appLogicManager;

    public ObservableList<User> searchUsers(String input,String type) throws SQLException {
        appLogicManager = new AppLogicManager();
        ObservableList<User> matchingUsers = FXCollections.observableArrayList();
        ObservableList<User> allUsers = appLogicManager.getAllUsersFromDatabase();
        for (User u: allUsers) {
            switch (type.toLowerCase()){
            case "username":
                    if (u.getUsername().toLowerCase().contains(input.toLowerCase())){
                        matchingUsers.add(u);
                    }
                break;
            case "first name":
                if (u.getFirstName().toLowerCase().contains(input.toLowerCase())){
                    matchingUsers.add(u);
                }
                break;
            }
        }

        return matchingUsers;
    }
    public ObservableList<Document> searchDocument(String input, String type) throws SQLException{
        appLogicManager = new AppLogicManager();
        ObservableList<Document> matchingDocuments = FXCollections.observableArrayList();
        ObservableList<Document> allDocuments = appLogicManager.getAllDocumentsFromDatabase();

        for (Document d: allDocuments) {

            switch (type.toLowerCase()){
                case "name":
                    if (d.getName().toLowerCase().contains(input.toLowerCase())){
                        matchingDocuments.add(d);
                    }
                    break;
                case "project":
                    Project selectedProject = getProject(d.getProject());
                    if (selectedProject.getType().toLowerCase().contains(input)){
                        matchingDocuments.add(d);
                    }
            }
        }
        return matchingDocuments;
    }
    public ObservableList<Customer> searchCustomers(String input, String type) throws SQLException{
        appLogicManager = new AppLogicManager();
        ObservableList<Customer> matchingCustomers = FXCollections.observableArrayList();
        ObservableList<Customer> allCustomers = appLogicManager.getAllCustomersFromDatabase();

        for (Customer c: allCustomers) {
            switch (type.toLowerCase()) {
                case "first name":
                if (c.getFirstName().toLowerCase().contains(input.toLowerCase())) {
                    matchingCustomers.add(c);
                }
                break;
                case "last name":
                    if (c.getLastName().toLowerCase().contains(input.toLowerCase())){
                        matchingCustomers.add(c);
                    }
                break;
                case "email":
                    if (c.getEmail().toLowerCase().contains(input.toLowerCase())){
                        matchingCustomers.add(c);
                    }
                break;
                case "address":
                    if (c.getAddress().toLowerCase().contains(input.toLowerCase())){
                        matchingCustomers.add(c);
                    }
                break;
            }
        }
        return matchingCustomers;
    }
    public ObservableList<LogIns> searchLogIns(String input, String type) throws SQLException{
        appLogicManager = new AppLogicManager();
        ObservableList<LogIns> matchingLogins = FXCollections.observableArrayList();
        ObservableList<LogIns> allLogIns = appLogicManager.getAllLogInsFromDatabase();

        for (LogIns logIns: allLogIns) {
            switch (type.toLowerCase()){
                case "username":
                    if (logIns.getUsername().toLowerCase().contains(input.toLowerCase())){
                        matchingLogins.add(logIns);
                    }
                break;
                case "password":
                    if (logIns.getPassword().toLowerCase().contains(input.toLowerCase())){
                        matchingLogins.add(logIns);
                    }
                break;
                case "project":
                    if (logIns.getProject().toLowerCase().contains(input.toLowerCase())){
                        matchingLogins.add(logIns);
                    }
                break;
            }

        }
        return matchingLogins;
    }
    public ObservableList<Order> searchOrder(String input, String type) throws SQLException{
        appLogicManager = new AppLogicManager();
        ObservableList<Order> matchingOrders = FXCollections.observableArrayList();
        ObservableList<Order> allOrders = appLogicManager.getAllOrdersFromDatabas();

        for (Order o: allOrders) {
            switch (type.toLowerCase()){
                case "name":
                    if (o.getName().toLowerCase().contains(input.toLowerCase())){
                        matchingOrders.add(o);
                    }
                break;
                case "project":
                    if (o.getProject().toLowerCase().contains(input.toLowerCase())){
                        matchingOrders.add(o);
                    }
                break;
                case "customer":
                    if (o.getCustomer().toLowerCase().contains(input.toLowerCase())){
                        matchingOrders.add(o);
                    }
                break;
            }

        }
        return matchingOrders;
    }
    public ObservableList<Project> searchProject(String input, String type) throws SQLException{
        appLogicManager = new AppLogicManager();
        ObservableList<Project> matchingProjects = FXCollections.observableArrayList();
        ObservableList<Project> allProjects = appLogicManager.getAllProjectsFromDatabase();

        for (Project p: allProjects) {
            switch (type.toLowerCase()){
                case "type":
                    if (p.getType().toLowerCase().contains(input.toLowerCase())){
                        matchingProjects.add(p);
                    }
                break;
                case "customer":
                    if (p.getCustomer().toLowerCase().contains(input.toLowerCase())){
                        matchingProjects.add(p);
                    }
            }
        }
        return matchingProjects;
    }

    private Project getProject(int projectId) {
        CompletableFuture<Project> future = new CompletableFuture<>();

        Task<Project> projectTask = new Task<Project>() {
            @Override
            protected Project call() throws Exception {
                return (Project) appLogicManager.getFromDatabase(projectId, "Project");
            }
        };

        projectTask.setOnSucceeded(event -> {
            Project selectedProject = projectTask.getValue();
            future.complete(selectedProject);
        });

        Thread projectThread = new Thread(projectTask);
        projectThread.start();

        try {
            // Wait for the future to complete and return the project
            return future.join();
        } catch (CompletionException e) {
            e.printStackTrace();
            // Handle any exceptions that occurred during the task execution
            return null; // or throw an exception, depending on your requirement
        }
    }
}
