package main.java.dal.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.LogIns;
import main.java.dal.DataAccessManager;
import main.java.dal.interfaces.DAOInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogInDAO implements DAOInterface<LogIns> {

    DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public LogIns getFromDatabase(int id) throws SQLException {
        String query="SELECT * FROM log_ins LEFT JOIN project ON  log_ins.projectId=project.id WHERE log_ins.id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public ObservableList<LogIns> getAllFromDatabase() throws SQLException {
        String query="SELECT * FROM log_ins LEFT JOIN project ON  log_ins.projectId=project.id;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<LogIns>) this.getAllDataFromResultSet(resultSet);
    }

    @Override
    public String insertIntoDatabase(LogIns object) throws SQLException {
        String username=object.getUsername();
        String password=object.getPassword();
        int project=object.getProjectId();


        String query="INSERT INTO log_ins VALUES (?, ?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,username);
        stmt.setString(2,password);
        stmt.setInt(3,project);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();

    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {
        String query="DELETE FROM log_ins WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();
        return resultSet.toString();    }

    @Override
    public String updateDatabase(LogIns object) throws SQLException {
       int id=object.getId();
        String username=object.getUsername();
        String password=object.getPassword();
        int projectId=object.getProjectId();

        String query="INSERT INTO log_ins VALUES (?, ?, ?) WHERE id = ?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,username);
        stmt.setString(2,password);
        stmt.setInt(3,projectId);
        stmt.setInt(4,id);

        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public LogIns getDataFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String project = resultSet.getString("type");
            int projectId = resultSet.getInt("projectId");

            return new LogIns(id, username, password, project, projectId);
        }
        return null;
    }

    @Override
    public ObservableList<LogIns> getAllDataFromResultSet(ResultSet resultSet) throws SQLException {
        ObservableList<LogIns> listOfLogIns= FXCollections.observableArrayList();

        while (resultSet.next()) {
            int id=resultSet.getInt("id");

            String username=resultSet.getString("username");
            String password=resultSet.getString("password");
            String project=resultSet.getString("type");
            int projectId=resultSet.getInt("projectId");




            listOfLogIns.add(new LogIns(id,username,password,project,projectId));
        }

        return listOfLogIns;
    }
}
