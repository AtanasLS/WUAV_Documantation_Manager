package main.java.dal.dao;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.ProjectToUser;
import main.java.dal.DataAccessManager;
import main.java.dal.interfaces.DAOInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectToUserDAO implements DAOInterface<ProjectToUser> {
    private final DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public ProjectToUser getFromDatabase(int id) throws SQLException {
        String query = "SELECT * FROM projectToUser WHERE id = ?";
        PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        return getDataFromResultSet(resultSet);
    }

    @Override
    public ObservableList<ProjectToUser> getAllFromDatabase() throws SQLException {
        String query = "SELECT * FROM projectToUser";
        PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();

        return getAllDataFromResultSet(resultSet);
    }

    @Override
    public String insertIntoDatabase(ProjectToUser object) throws SQLException {
        int projectId = object.getProjectId();
        int userId = object.getUserId();

        String query = "INSERT INTO projectToUser (projectId, userId) VALUES (?, ?)";
        PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1, projectId);
        stmt.setInt(2, userId);
        int rowsAffected = stmt.executeUpdate();

        return rowsAffected + " row(s) inserted.";
    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {
        String query = "DELETE FROM projectToUser WHERE id = ?";
        PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1, id);
        int rowsAffected = stmt.executeUpdate();

        return rowsAffected + " row(s) deleted.";
    }

    @Override
    public String updateDatabase(ProjectToUser object) throws SQLException {
        int id = object.getId();
        int projectId = object.getProjectId();
        int userId = object.getUserId();

        String query = "UPDATE projectToUser SET projectId = ?, userId = ? WHERE id = ?";
        PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1, projectId);
        stmt.setInt(2, userId);
        stmt.setInt(3, id);
        int rowsAffected = stmt.executeUpdate();

        return rowsAffected + " row(s) updated.";
    }

    @Override
    public ProjectToUser getDataFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int projectId = resultSet.getInt("projectId");
        int userId = resultSet.getInt("userId");

        ProjectToUser projectToUser = new ProjectToUser(projectId, userId);
        projectToUser.setId(id);

        return projectToUser;
    }

    @Override
    public ObservableList<ProjectToUser> getAllDataFromResultSet(ResultSet resultSet) throws SQLException {
        ObservableList<ProjectToUser> projectToUsers = FXCollections.observableArrayList();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int projectId = resultSet.getInt("projectId");
            int userId = resultSet.getInt("userId");

            ProjectToUser projectToUser = new ProjectToUser(projectId, userId);
            projectToUser.setId(id);

            projectToUsers.add(projectToUser);
        }

        return projectToUsers;
    }
}