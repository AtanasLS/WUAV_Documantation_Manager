package main.java.dal.dao;

import main.java.be.Customer;
import main.java.be.User;
import main.java.dal.DataAccessManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements DAOInterface<Customer> {
    DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public Customer getFromDatabase(int id) throws SQLException {
        String query="SELECT * FROM customer WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public List<Customer> getAllFromDatabase() throws SQLException {
        String query="SELECT * FROM customer;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return this.getAllDataFromResultSet(resultSet);
    }

    @Override
    public String insertIntoDatabase(Customer object) throws SQLException {
        String firstName=object.getFirstName();
        String lastName=object.getLastName();
        String email=object.getEmail();
        String address=object.getAddress();
        String address2=object.getAddress2();
        String phone=object.getPhone();
        int consumptionNumber=object.getConsumptionNumber();

        String query="INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,firstName);
        stmt.setString(2,lastName);
        stmt.setString(3,email);
        stmt.setString(4,address);
        stmt.setString(5,address2);
        stmt.setString(6,phone);
        stmt.setInt(7,consumptionNumber);

        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {

        String query="DELETE FROM customer WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();
        return resultSet.toString();
    }

    @Override
    public String updateDatabase(Customer object, int id) throws SQLException {

        String firstName=object.getFirstName();
        String lastName=object.getLastName();
        String email=object.getEmail();
        String address=object.getAddress();
        String address2=object.getAddress2();
        String phone=object.getPhone();
        int consumptionNumber=object.getConsumptionNumber();



        String query="INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?) WHERE id = ?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,firstName);
        stmt.setString(2,lastName);
        stmt.setString(3,email);
        stmt.setString(4,address);
        stmt.setString(5,address2);
        stmt.setString(6,phone);
        stmt.setInt(7,consumptionNumber);
        stmt.setInt(8,id);

        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public Customer getDataFromResultSet(ResultSet resultSet) throws SQLException {
        String firstName=resultSet.getString("firstName");
        String lastName=resultSet.getString("lastName");
        String email=resultSet.getString("email");
        String address=resultSet.getString("address");
        String address2=resultSet.getString("address2");
        String phone=resultSet.getString("phone");
        int consumptionNumber=resultSet.getInt("consumptionNumber");
        return  new Customer(firstName,lastName,email,address,address2,phone,consumptionNumber);
    }

    @Override
    public List<Customer> getAllDataFromResultSet(ResultSet resultSet) throws SQLException {
        List<Customer> listOfCustomers=new ArrayList<>();

        while (resultSet.next()) {

            String firstName=resultSet.getString("firstName");
            String lastName=resultSet.getString("lastName");
            String email=resultSet.getString("email");
            String address=resultSet.getString("address");
            String address2=resultSet.getString("address2");
            String phone=resultSet.getString("phone");
            int consumptionNumber=resultSet.getInt("consumptionNumber");
            listOfCustomers.add(new Customer(firstName,lastName,email,address,address2,phone,consumptionNumber));
        }

        return listOfCustomers;
    }
}