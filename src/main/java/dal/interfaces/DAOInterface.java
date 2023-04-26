package main.java.dal.interfaces;

import javafx.collections.ObservableList;
import main.java.be.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T> {

    T getFromDatabase( int id) throws SQLException;
    ObservableList<T> getAllFromDatabase() throws SQLException;
    String insertIntoDatabase(T object) throws SQLException;
    String deleteFromDatabase(int id) throws SQLException;
    String updateDatabase(T object , int id) throws SQLException;

    T getDataFromResultSet(ResultSet resultSet) throws SQLException;

    ObservableList<T> getAllDataFromResultSet(ResultSet resultSet) throws SQLException;
}
