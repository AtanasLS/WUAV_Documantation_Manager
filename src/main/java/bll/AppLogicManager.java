package main.java.bll;

import javafx.collections.ObservableList;
import main.java.be.*;
import main.java.dal.DataManagerFacade;
import main.java.dal.interfaces.DAOInterface;

import java.sql.SQLException;

public class AppLogicManager {


    public Object getFromDatabase(int id, String type){
        try {
            return DataManagerFacade.getInstance().getFromDatabase(id,type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //getting all the customers
    public ObservableList<Customer> getAllCustomersFromDatabase(){
        try {
            return DataManagerFacade.getInstance().getAllCustomersFromDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //getting all the users
    public ObservableList<User> getAllUsersFromDatabase() throws SQLException {
        try {
            return DataManagerFacade.getInstance().getAllUsersFromDatabase();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    //getting all the documents
    public ObservableList<Document> getAllDocumentsFromDatabase() throws SQLException {
        try {
            return DataManagerFacade.getInstance().getAllDocumentsFromDatabase();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    //getting all the logIns
    public ObservableList<LogIns> getAllLogInsFromDatabase() throws SQLException {
        try {
            return DataManagerFacade.getInstance().getAllLogInsFromDatabase();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    //getting all the pictures
    public ObservableList<Picture> getAllPicturesFromDatabase() throws SQLException {
        try {
            return DataManagerFacade.getInstance().getAllPicturesFromDatabase();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    //getting all the projects
    public ObservableList<Project> getAllProjectsFromDatabase() throws SQLException {
        try {
            return DataManagerFacade.getInstance().getAllProjectsFromDatabase();
        }catch (SQLException e){
            throw new RuntimeException();
        }

    }

    public String insertIntoDatabase(Object object, String type){
        try {
            return DataManagerFacade.getInstance().insertIntoDatabase(object, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String deleteFromDatabase (String id, String type){
        try {
            return  DataManagerFacade.getInstance().deleteFromDatabase(id, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String updateDatabase (Object object , String id, String type){
        try {
            return DataManagerFacade.getInstance().updateDatabase(object, id, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<User> getAllTechnicians() {
        try {
            return DataManagerFacade.getInstance().getAllTechniciansFromDatabase();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public User getTechnician(int id) {
        try {
            return DataManagerFacade.getInstance().getTechnicianFromDatabase(id);
        }catch (SQLException e){
            throw new RuntimeException();
        }    }


    public ObservableList<Order> getAllOrdersFromDatabas() {
        return null;
    }


public Project getMostSelledProject() throws SQLException {
        return DataManagerFacade.getInstance().getTheMostSaledProject();
}}
