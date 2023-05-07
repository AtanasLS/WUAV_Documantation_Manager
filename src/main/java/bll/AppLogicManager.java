package main.java.bll;

import javafx.collections.ObservableList;
import main.java.be.*;
import main.java.dal.DataManagerFacade;
import main.java.dal.interfaces.DAOInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class AppLogicManager {


    public Object getFromDatabase(int id, String type){
        try {
            return DataManagerFacade.getInstance().getFromDatabase(id,type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            System.out.println(e);
            return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public String deleteFromDatabase (int id, String type){
        try {
            return  DataManagerFacade.getInstance().deleteFromDatabase(id, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String updateDatabase (Object object , String type){
        try {
            return DataManagerFacade.getInstance().updateDatabase(object, type);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } catch (FileNotFoundException e) {
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
        }
    }


    public ObservableList<Order> getAllOrdersFromDatabas() {
        try {
            return DataManagerFacade.getInstance().getAllOrderFromDatabase();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }


public Project getMostSelledProject() throws SQLException {
        return DataManagerFacade.getInstance().getTheMostSaledProject();
}}
