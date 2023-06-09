package main.java.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.*;
import main.java.bll.AppLogicManager;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.mindrot.jbcrypt.BCrypt.checkpw;

public class MainModel {


    private AppLogicManager appLogicManager;

    private final ObservableList<Customer> allCustomers;
    private final ObservableList<Document> allDocuments;
    private final ObservableList<Document> editedDocuments;
    private final ObservableList<LogIns> allLogIns;
    private final ObservableList<Order> allOrders;

    private final ObservableList<Project> allProjects;

    private final ObservableList<User> allUsers;

    private final ObservableList<User> allTech;

    private User loggedInUser;

    private Customer selectedCustomer;
    private Document selectedDocument;

    private Project selectedProject;

    private Order selectedOrder;


    private User selectedUser;

    private Project mostSelled;
    private final ObservableList<Project> projectToUserList;
    private final  ObservableList<ProjectToUser> projectToUsers;






    public MainModel(){
        this.appLogicManager = new AppLogicManager();
        this.allCustomers = FXCollections.observableArrayList();
        this.allDocuments=FXCollections.observableArrayList();
        this.allLogIns=FXCollections.observableArrayList();
        this.allOrders=FXCollections.observableArrayList();
        this.allProjects=FXCollections.observableArrayList();
        this.allUsers=FXCollections.observableArrayList();
        this.allTech=FXCollections.observableArrayList();
        this.editedDocuments = FXCollections.observableArrayList();
        this.projectToUserList = FXCollections.observableArrayList();
        this.projectToUsers = FXCollections.observableArrayList();
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

    public ObservableList<Document> getEditedDocuments() {
        return editedDocuments;
    }

    public void loadEditedDocuments() throws SQLException, IOException {
        this.editedDocuments.clear();
        this.editedDocuments.addAll(appLogicManager.getEditedFromDatabase());
    }

    public Project getTheMOstSelledProject() throws SQLException {
       return this.mostSelled=this.appLogicManager.getMostSelledProject();
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
    public void loadProjectToUser() throws SQLException{
        this.projectToUsers.clear();
        this.projectToUsers.addAll(appLogicManager.getAllProjectToUsers());
    }
    public ObservableList<ProjectToUser> getAllProjectToUser(){
        return projectToUsers;
    }
    public ObservableList<Project> getAllProjectToUser(User selectedUser) {
        this.projectToUserList.clear();
        for (Project project : getAllProjects()) {
            for ( ProjectToUser projectToUser : getAllProjectToUser()) {
                if (selectedUser.getId() == projectToUser.getUserId() && project.getProjectId() == projectToUser.getProjectId()) {
                    this.projectToUserList.add(project);
                }
            }
        }

        return this.projectToUserList;
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
            if (u.getUsername().equals(username)  &&   this.checkPass(password,u.getPassword()  )

            ){
                u.setPassword(password);
                loggedInUser = u;
                return true;
            }
        }
            return false;
    }
    public void addUser(User userToAdd){
        allUsers.add(userToAdd);

    }
    private boolean checkPass(String plainPassword, String hashedPassword) {

        if (checkpw(plainPassword, hashedPassword)){
            return true;
        } else {
            return false;
        }

    }
    public Customer getSelectedCustomer(){
        return selectedCustomer;
    }

    public void updateUsers(Object selectedObject,int id)  {
    /*
        User userToRemove = null;
        for (User u: getAllUsers()) {
            if (u.getId()==id){
                userToRemove = u;
            }
        }
        this.allUsers.remove(userToRemove);
        this.allUsers.add((User) selectedObject);

     */
    }
    public void addObject(Object selectedObject, String type) {
        switch (type.toLowerCase()) {
            case "user" -> this.allUsers.add((User) selectedObject);
            case "customer" -> this.allCustomers.add((Customer) selectedObject);
            case "project" -> this.allProjects.add((Project) selectedObject);
            case "order" -> this.allOrders.add((Order) selectedObject);
            case "document" -> this.allDocuments.add((Document) selectedObject);
            case "login" -> this.allLogIns.add((LogIns) selectedObject);
        }

    }
    public Object getSelectedObject(int id, String type) {
        return appLogicManager.getFromDatabase(id, type);
    }

    public ObservableList<Picture> getAllPhotosForSelectedDocument(int documentId) throws SQLException, IOException {
        return appLogicManager.getAllPicturesForProject(documentId);
    }

    public void setSelectedCustomer(Customer customer){
        this.selectedCustomer = customer;
    }
    public void setSelectedDocument(Document document){
        this.selectedDocument = document;
    }
    public Document getSelectedDocument(){
        return selectedDocument;
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

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void setUser(String text) throws Exception {
        this.loggedInUser=this.getUser(text);
    }

    public User getLogInUser(){
        return this.loggedInUser;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }
}
