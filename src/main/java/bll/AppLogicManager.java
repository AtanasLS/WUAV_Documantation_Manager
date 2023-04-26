package main.java.bll;

import javafx.collections.ObservableList;
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
    public ObservableList<Object> getAllFromDatabase(String type){
        try {
            return DataManagerFacade.getInstance().getAllFromDatabase(type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String insertIntoDatabase(Object object, String type){
        try {
            return DataManagerFacade.getInstance().insertIntoDatabase(object, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String deleteFromDatabase (int id, String type){
        try {
            return  DataManagerFacade.getInstance().insertIntoDatabase(id, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String updateDatabase (Object object , int id, String type){
        try {
            return DataManagerFacade.getInstance().updateDatabase(object, id, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
