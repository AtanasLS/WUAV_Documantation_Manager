package main.java.dal.dao;

import com.microsoft.sqlserver.jdbc.SQLServerException;
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
        String query="SELECT * from project LEFT JOIN customer ON customer.id=project.customerId WHERE project.id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }


    public Project getProjectWithMostSales() throws SQLException {
        String query= "  SELECT top 1 project.type  , COUNT(project.type) AS salesCount FROM project GROUP BY type ORDER BY salesCount DESC;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        if (resultSet.next()){
        return new Project(resultSet.getString("type"),resultSet.getInt("salesCount"));}
        return null;
    }

    @Override
    public ObservableList<Project> getAllFromDatabase() throws SQLException {
        String query="SELECT * from project LEFT JOIN customer ON customer.id=project.customerId;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<Project>) this.getAllDataFromResultSet(resultSet);
    }

    @Override
    public String insertIntoDatabase(Project object) throws SQLException {

        String type=object.getType();
        int customerId=object.getCustomerId();


        String query="INSERT INTO project VALUES (?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,type);
        stmt.setInt(2,customerId);



        ResultSet resultSet =stmt.executeQuery();
        this.addProjectToUser(object.getProjectId(),customerId);

        return resultSet.toString();
    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {
        String query="DELETE FROM project WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();
        this.deleteProjectToUser(id);
        return resultSet.toString();
    }

    @Override
    public String updateDatabase(Project object) throws SQLException {
        int id=object.getProjectId();
        String type=object.getType();
        int customerId=object.getCustomerId();

        String query="INSERT INTO project VALUES (?, ?) WHERE id = ?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,type);
        stmt.setInt(2,customerId);
        stmt.setInt(3,id);


        ResultSet resultSet =stmt.executeQuery();

        this.addProjectToUser(id,customerId);

        return resultSet.toString();
    }

    @Override
    public Project getDataFromResultSet(ResultSet resultSet) throws SQLException {

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String type = resultSet.getString("project_type");
            String customer = resultSet.getString("first_name");
            int customerId = resultSet.getInt("id");

            return new Project(id, type, customer, customerId);
        }
        return null;

    }

    @Override
    public ObservableList<Project> getAllDataFromResultSet(ResultSet resultSet) throws SQLException {
        ObservableList<Project> listOfProjects= FXCollections.observableArrayList();

        while (resultSet.next()) {

            int id=resultSet.getInt("id");
            String type=resultSet.getString("project_type");
            String customer=resultSet.getString("first_name");
            int customerId=resultSet.getInt("customerId");

            listOfProjects.add(new Project(id,type,customer,customerId));
        }

        return listOfProjects;    }


    private void addProjectToUser(int projectId, int userId) throws SQLException {

        String query="INSERT INTO projectToUser VALUES (?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,projectId);
        stmt.setInt(2,userId);
        ResultSet resultSet =stmt.executeQuery();


    }

    private void deleteProjectToUser(int projectId) throws SQLException {
        String query="DELETE FROM projectToUser WHERE projectId=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,projectId);
        ResultSet resultSet =stmt.executeQuery();
    }
}
