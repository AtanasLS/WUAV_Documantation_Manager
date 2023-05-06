package main.java.dal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.*;
import main.java.dal.dao.*;

import java.sql.SQLException;

public class DataManagerFacade {

    private static DataManagerFacade instance; //Singleton instance

    CustomerDAO customerDAO;
    DocumentDAO documentDAO;
    LogInDAO logInDAO;
    PictureDAO pictureDAO;
    ProjectDAO projectDAO;
    UserDAO userDAO;

    private DataManagerFacade() {
        userDAO = new UserDAO();
        customerDAO = new CustomerDAO();
        documentDAO = new DocumentDAO();
        logInDAO = new LogInDAO();
        pictureDAO = new PictureDAO();
        projectDAO = new ProjectDAO();
    }

    public static DataManagerFacade getInstance() {
        return instance == null ? instance = new DataManagerFacade() : instance;
    }


    public Object getFromDatabase(int id, String type) throws SQLException {
        Object o = null;

        switch (type) {
            case "User":
                o = this.userDAO.getFromDatabase(id);
                break;
            case "Customer":
                o = this.customerDAO.getFromDatabase(id);
                break;
            case "Document":
                o = this.documentDAO.getFromDatabase(id);
                break;
            case "LogIn":
                o = this.logInDAO.getFromDatabase(id);
                break;
            case "Picture":
                o = this.pictureDAO.deleteFromDatabase(id);
                break;
            case "Project":
                o = this.projectDAO.deleteFromDatabase(id);
                break;
        }

        return o;
    }
    //getting all the customers
    public ObservableList<Customer> getAllCustomersFromDatabase() throws SQLException {
        return customerDAO.getAllFromDatabase();

    }
    //getting all the users
    public ObservableList<User> getAllUsersFromDatabase() throws SQLException {
        return userDAO.getAllFromDatabase();
    }

    public ObservableList<User> getAllTechniciansFromDatabase() throws SQLException {
        return userDAO.getAllTechniciansFromDatabase();
    }

    public User getTechnicianFromDatabase(int id) throws SQLException {
        return userDAO.getTechnicianFromDatabase(id);
    }
    //getting all the documents
    public ObservableList<Document> getAllDocumentsFromDatabase() throws SQLException {
        return documentDAO.getAllFromDatabase();
    }
    //getting all the logIns
    public ObservableList<LogIns> getAllLogInsFromDatabase() throws SQLException {
        return logInDAO.getAllFromDatabase();
    }
    //getting all the pictures
    public ObservableList<Picture> getAllPicturesFromDatabase() throws SQLException {
        return pictureDAO.getAllFromDatabase();
    }
    //getting all the projects
    public ObservableList<Project> getAllProjectsFromDatabase() throws SQLException {
        return projectDAO.getAllFromDatabase();

    }


    public String insertIntoDatabase(Object object, String type) throws SQLException {
        String output = "";
        switch (type) {
            case "User":
                output = userDAO.insertIntoDatabase((User) object);
            break;
            case "Customer":
                output = customerDAO.insertIntoDatabase((Customer) object);
            break;
            case "Document":
                output = documentDAO.insertIntoDatabase((Document) object);
            break;
            case "LogIn":
                output = logInDAO.insertIntoDatabase((LogIns) object);
            break;
            case "Picture":
                output = pictureDAO.insertIntoDatabase((Picture) object);
            break;
            case "Project":
                output = projectDAO.insertIntoDatabase((Project) object);
            break;
        }
        return output;
    }

<<<<<<< Updated upstream
    public String deleteFromDatabase(String id, String type) throws SQLException{
=======
    public  Project getTheMostSaledProject() throws SQLException {
        return this.projectDAO.getProjectWithMostSales();
    }


    public String deleteFromDatabase(int id, String type) throws SQLException{
>>>>>>> Stashed changes
        String output = "";

        switch (type){
            case "User":
                output = userDAO.deleteFromDatabase(id);
                break;
            case "Customer":
                output = customerDAO.deleteFromDatabase(id);
                break;
            case "Document":
                output = documentDAO.deleteFromDatabase(id);
                break;
            case "LogIn":
                output = logInDAO.deleteFromDatabase(id);
                break;
            case "Picture":
                output = pictureDAO.deleteFromDatabase(id);
                break;
            case "Project":
                output = projectDAO.deleteFromDatabase(id);
                break;
        }
        return output;
    }
    public String updateDatabase(Object object , String id, String type) throws SQLException{
        String output = "";


        switch (type){
            case "User":
                output = userDAO.updateDatabase((User) object, id);
                break;
            case "Customer":
                output = customerDAO.updateDatabase((Customer) object,id);
                break;
            case "Document":
                output = documentDAO.updateDatabase((Document) object,id);
                break;
            case "LogIn":
                output = logInDAO.updateDatabase((LogIns) object,id);
                break;
            case "Picture":
                output = pictureDAO.updateDatabase((Picture) object,id);
                break;
            case "Project":
                output = projectDAO.updateDatabase((Project) object,id);
                break;
        }
        return output;
    }
}
