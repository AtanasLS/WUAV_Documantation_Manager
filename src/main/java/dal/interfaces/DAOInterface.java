package main.java.dal.interfaces;

import javafx.collections.ObservableList;
import main.java.be.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T> {

    T getFromDatabase( int id) throws SQLException, IOException;
    ObservableList<T> getAllFromDatabase() throws SQLException, IOException;
    String insertIntoDatabase(T object) throws SQLException;
    String deleteFromDatabase(int id) throws SQLException;
    String updateDatabase(T object) throws SQLException;

    T getDataFromResultSet(ResultSet resultSet) throws SQLException, IOException;

    ObservableList<T> getAllDataFromResultSet(ResultSet resultSet) throws SQLException, IOException;
}
