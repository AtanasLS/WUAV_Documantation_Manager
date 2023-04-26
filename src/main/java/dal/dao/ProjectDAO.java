package main.java.dal.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.Project;
import main.java.dal.DataAccessManager;
import main.java.dal.interfaces.DAOInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements DAOInterface<Project> {
    DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public Project getFromDatabase(int id) throws SQLException {
        String query="SELECT * FROM project WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public ObservableList<Project> getAllFromDatabase() throws SQLException {
        String query="SELECT * FROM project;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<Project>) this.getAllDataFromResultSet(resultSet);
    }

    @Override
    public String insertIntoDatabase(Project object) throws SQLException {
        String type=object.getType();
        double price=object.getPrice();

        String query="INSERT INTO project VALUES (?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,type);
        stmt.setDouble(2,price);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {
        String query="DELETE FROM project WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();
        return resultSet.toString();
    }

    @Override
    public String updateDatabase(Project object, int id) throws SQLException {
        String type=object.getType();
        double price=object.getPrice();

        String query="INSERT INTO project VALUES (?, ?) WHERE id = ?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,type);
        stmt.setDouble(2,price);
        stmt.setInt(3,id);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public Project getDataFromResultSet(ResultSet resultSet) throws SQLException {
        String type=resultSet.getString("type");
        double price=resultSet.getDouble("price");
        return  new Project(type,price);

    }

    @Override
    public ObservableList<Project> getAllDataFromResultSet(ResultSet resultSet) throws SQLException {
        ObservableList<Project> listOfProjects= FXCollections.observableArrayList();

        while (resultSet.next()) {

            String type=resultSet.getString("type");
            double price=resultSet.getDouble("price");
            listOfProjects.add(new Project(type,price));
        }

        return listOfProjects;    }
}
