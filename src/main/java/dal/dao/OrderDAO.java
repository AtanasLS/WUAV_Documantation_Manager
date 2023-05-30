package main.java.dal.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.Order;
import main.java.be.Project;
import main.java.dal.DataAccessManager;
import main.java.dal.interfaces.DAOInterface;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDAO implements DAOInterface<Order> {
    DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public Order getFromDatabase(int id) throws SQLException {
        String query="SELECT * from [order] LEFT JOIN customer ON customer.id=[order].customerId LEFT JOIN users ON users.id=[order].UserId LEFT JOIN project ON " +
                "project.id=[order].ProjectId WHERE order.id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public ObservableList<Order> getAllFromDatabase() throws SQLException {
        String query="SELECT * from [order] LEFT JOIN customer ON customer.id=[order].customerId LEFT JOIN users ON users.id=[order].UserId LEFT JOIN project ON project.id=[order].ProjectId;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<Order>) this.getAllDataFromResultSet(resultSet);
    }

    @Override
    public String insertIntoDatabase(Order object) throws SQLException {
        int userId=object.getUserID();
        int projectID=object.getProjectID();
        String name=object.getName();
        int customer=object.getCustomerID();
        Date date= (Date) object.getDate();



        String query="INSERT INTO [order] VALUES (?, ?, ?, ?, ? );";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,userId);
        stmt.setInt(2,projectID);
        stmt.setString(3,name);
        stmt.setInt(4,customer);
        stmt.setDate(5,date);
        System.out.println(date);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {
        String query = "DELETE FROM [order] WHERE id = ?";
        PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1, id);
        int affectedRows = stmt.executeUpdate();
        System.out.println("Rows affected: " + affectedRows);
        return "Rows affected: " + affectedRows;
    }

    @Override
    public String updateDatabase(Order object) throws SQLException {
        int id = object.getId();
        int userId = object.getUserID();
        int projectID = object.getProjectID();
        String name = object.getName();
        int customer = object.getCustomerID();
        Date date = object.getDate();
        System.out.println(object.getDate());

        String query = "UPDATE [order] SET UserId = ?, ProjectId = ?, name = ?, customerId = ?, date = ? WHERE id = ?;";

        PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.setInt(2, projectID);
        stmt.setString(3, name);
        stmt.setInt(4, customer);
        stmt.setDate(5, date);
        stmt.setInt(6, id);

        try {
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
        return "Work!";
    }

    @Override
    public Order getDataFromResultSet(ResultSet resultSet) throws SQLException {
        int id=resultSet.getInt("id");
        int userID=resultSet.getInt("UserId");
        int projectID=resultSet.getInt("ProjectId");
        String name=resultSet.getString("name");
        String user=resultSet.getString("username");
        String project=resultSet.getString("project_type");
        String customer=resultSet.getString("first_name");
        int customerId=resultSet.getInt("customerId");
        Date date=resultSet.getDate("date");


        return new Order(id,userID,projectID,name,user,project,customer,customerId, date);


    }

    @Override
    public ObservableList<Order> getAllDataFromResultSet(ResultSet resultSet) throws SQLException {
        ObservableList<Order> listOfOrders= FXCollections.observableArrayList();

        while (resultSet.next()) {

            int id=resultSet.getInt("id");
            int userID=resultSet.getInt("UserId");
            int projectID=resultSet.getInt("ProjectId");
            String name=resultSet.getString("name");
            String user=resultSet.getString("username");
            String project=resultSet.getString("project_type");
            String customer=resultSet.getString("first_name");
            int customerId=resultSet.getInt("customerId");
            Date date=resultSet.getDate("date");

            listOfOrders.add(new Order(id,userID,projectID,name,user,project,customer,customerId, date));
        }

        return listOfOrders;
    }
}