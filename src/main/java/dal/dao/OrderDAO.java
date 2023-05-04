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
        String query="SELECT * from \"order\" LEFT JOIN customer ON customer.id=[order].customerId LEFT JOIN users ON users.id=[order].UserId WHERE order.id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public ObservableList<Order> getAllFromDatabase() throws SQLException {
        String query="SELECT * from \"order\" LEFT JOIN customer ON customer.id=[order].customerId LEFT JOIN users ON users.id=[order].UserId;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<Order>) this.getAllDataFromResultSet(resultSet);    }

    @Override
    public String insertIntoDatabase(Order object) throws SQLException {
        int userId=object.getUserID();
        int projectID=object.getProjectID();
        String name=object.getName();
        int customer=object.getCustomerID();
        Date date= Date.valueOf(object.getDate());
        Double price=object.getPrice();


        String query="INSERT INTO [order] VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,userId);
        stmt.setInt(2,projectID);
        stmt.setString(3,name);
        stmt.setInt(4,customer);
        stmt.setDate(5,date);
        stmt.setDouble(6,price);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {
        String query="DELETE FROM [order] WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();
        return resultSet.toString();
    }

    @Override
    public String updateDatabase(Order object) throws SQLException {
        int id=object.getId();
        int userId=object.getUserID();
        int projectID=object.getProjectID();
        String name=object.getName();
        int customer=object.getCustomerID();

        LocalDate date= object.getDate();
        Double price=object.getPrice();

        String query="INSERT INTO [order] VALUES (?, ?, ?, ?, ?, ?) WHERE id = ?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,userId);
        stmt.setInt(2,projectID);
        stmt.setString(3,name);
        stmt.setInt(4,customer);
        stmt.setDate(5, Date.valueOf(date));
        stmt.setDouble(6,price);
        stmt.setInt(7,id);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public Order getDataFromResultSet(ResultSet resultSet) throws SQLException {
        int id=resultSet.getInt("id");
        int userID=resultSet.getInt("UserId");
        int projectID=resultSet.getInt("ProjectId");
        String name=resultSet.getString("name");
        String user=resultSet.getString("username");
        String project=resultSet.getString("name");
        String customer=resultSet.getString("first_name");
        int customerId=resultSet.getInt("customerId");
        Date date=resultSet.getDate("date");
        double price=resultSet.getDouble("price");


        return new Order(userID,projectID,name,user,project,customer,customerId, date.toLocalDate(),price);


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
            String project=resultSet.getString("name");
            String customer=resultSet.getString("first_name");
            int customerId=resultSet.getInt("customerId");
            Date date=resultSet.getDate("date");
            double price=resultSet.getDouble("price");

            listOfOrders.add(new Order(userID,projectID,name,user,project,customer,customerId, date.toLocalDate(),price));
        }

        return listOfOrders;    }
}