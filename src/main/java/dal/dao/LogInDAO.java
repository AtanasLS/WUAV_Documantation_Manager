package main.java.dal.dao;

import main.java.be.LogIns;
import main.java.be.User;
import main.java.dal.DataAccessManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogInDAO implements DAOInterface<LogIns> {

    DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public LogIns getFromDatabase(int id) throws SQLException {
        String query="SELECT * FROM logIns WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public List<LogIns> getAllFromDatabase() throws SQLException {
        String query="SELECT * FROM logIns;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return this.getAllDataFromResultSet(resultSet);
    }

    @Override
    public String insertIntoDatabase(LogIns object) throws SQLException {
        String username=object.getUsername();
        String password=object.getPassword();

        String query="INSERT INTO logIns VALUES (?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,username);
        stmt.setString(2,password);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();

    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {
        String query="DELETE FROM logIns WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();
        return resultSet.toString();    }

    @Override
    public String updateDatabase(LogIns object, int id) throws SQLException {
        String username=object.getUsername();
        String password=object.getPassword();

        String query="INSERT INTO logIns VALUES (?, ?) WHERE id = ?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,username);
        stmt.setString(2,password);
        stmt.setInt(3,id);

        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public LogIns getDataFromResultSet(ResultSet resultSet) throws SQLException {
        String username=resultSet.getString("username");
        String password=resultSet.getString("password");
        return  new LogIns(username,password);
    }

    @Override
    public List<LogIns> getAllDataFromResultSet(ResultSet resultSet) throws SQLException {
        List<LogIns> listOfLogIns=new ArrayList<>();

        while (resultSet.next()) {

            String username=resultSet.getString("username");
            String password=resultSet.getString("password");

            listOfLogIns.add(new LogIns(username,password));
        }

        return listOfLogIns;
    }
}
