package main.java.dal.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.User;
import main.java.dal.DataAccessManager;
import main.java.dal.interfaces.DAOInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAOInterface<User> {
    DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public User getFromDatabase(int id) throws SQLException {
        String query="SELECT * FROM user WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

       return this.getDataFromResultSet(resultSet);
    }

    public User getTechnicianFromDatabase(int id) throws SQLException {
        String query="SELECT * FROM user WHERE id=? AND type=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        stmt.setString(2,"technician");

        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public ObservableList<User> getAllFromDatabase() throws SQLException {
        String query="SELECT * FROM user;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<User>) this.getAllDataFromResultSet(resultSet);

    }

    public ObservableList<User> getAllTechniciansFromDatabase() throws SQLException {
        String query="SELECT * FROM user WHERE type=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,"technician");
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<User>) this.getAllDataFromResultSet(resultSet);

    }

    @Override
    public String insertIntoDatabase(User object) throws SQLException {
        String username=object.getUsername();
        String firstName=object.getFirstName();
        String lastName=object.getLastName();
        String email=object.getEmail();
        String password= object.getPassword();
        String type=object.getType();

        String query="INSERT INTO user VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,username);
        stmt.setString(2,firstName);
        stmt.setString(3,lastName);
        stmt.setString(4,email);
        stmt.setString(5,password);
        stmt.setString(6,type);

        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();

    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {

        String query="DELETE FROM user WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();
        return resultSet.toString();
    }

    @Override
    public String updateDatabase(User object , int id) throws SQLException {
        String username=object.getUsername();
        String firstName=object.getFirstName();
        String lastName=object.getLastName();
        String email=object.getEmail();
        String password= object.getPassword();
        String type=object.getType();

        String query="INSERT INTO user VALUES (?, ?, ?, ?, ?, ?) WHERE id = ?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,username);
        stmt.setString(2,firstName);
        stmt.setString(3,lastName);
        stmt.setString(4,email);
        stmt.setString(5,password);
        stmt.setString(6,type);
        stmt.setInt(7,id);

        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public User getDataFromResultSet(ResultSet resultSet) throws SQLException {
        String username=resultSet.getString("username");
        String firstName=resultSet.getString("firstName");
        String lastName=resultSet.getString("lastName");
        String email=resultSet.getString("email");
        String password=resultSet.getString("password");
        String type=resultSet.getString("type");
        String customer=resultSet.getString("type");
        return  new User(username,firstName,lastName,email,password,type);

    }

    @Override
    public ObservableList<User> getAllDataFromResultSet(ResultSet resultSet) throws SQLException {

        ObservableList<User> listOfUsers= FXCollections.observableArrayList();

        while (resultSet.next()) {

            String username = resultSet.getString("username");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String type = resultSet.getString("type");
            listOfUsers.add(new User(username, firstName, lastName, email, password, type));
        }

        return listOfUsers;
    }



}
