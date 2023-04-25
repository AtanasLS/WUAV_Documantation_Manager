package main.java.dal.dao;

import main.java.be.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T> {

    T getFromDatabase( int id) throws SQLException;
    List<T> getAllFromDatabase() throws SQLException;
    String insertIntoDatabase(T object) throws SQLException;
    String deleteFromDatabase(int id) throws SQLException;
    String updateDatabase(T object , int id) throws SQLException;

    T getDataFromResultSet(ResultSet resultSet) throws SQLException;

    List<T> getAllDataFromResultSet(ResultSet resultSet) throws SQLException;
}
