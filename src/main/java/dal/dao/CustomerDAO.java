package main.java.dal.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.Customer;
import main.java.dal.DataAccessManager;
import main.java.dal.interfaces.DAOInterface;

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
    public ObservableList<Customer> getAllFromDatabase() throws SQLException {
        String query="SELECT * FROM  [WUAV_Documentation_System].[dbo].[customer]";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<Customer>) this.getAllDataFromResultSet(resultSet);
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

        try {
            ResultSet resultSet = stmt.executeQuery();
            //System.out.println(resultSet.toString());
        }catch (RuntimeException e){
            System.out.println(e);
        }
        return "Work!";
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
    public String updateDatabase(Customer object) throws SQLException {

        String firstName=object.getFirstName();
        String lastName=object.getLastName();
        String email=object.getEmail();
        String address=object.getAddress();
        String address2=object.getAddress2();
        String phone=object.getPhone();
        int id=object.getConsumptionNumber();



        String query="UPDATE customer set first_name = ?,last_name = ?,email = ?,address = ?,address2 = ?,phone = ? WHERE id = ?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,firstName);
        stmt.setString(2,lastName);
        stmt.setString(3,email);
        stmt.setString(4,address);
        stmt.setString(5,address2);
        stmt.setString(6,phone);
        stmt.setInt(7,id);

        try {
            ResultSet resultSet = stmt.executeQuery();
            //System.out.println(resultSet.toString());
        }catch (RuntimeException e){
            System.out.println(e);
        }
        return "Work!";

    }

    @Override
    public Customer getDataFromResultSet(ResultSet resultSet) throws SQLException {
        int id=resultSet.getInt("id");
        String firstName=resultSet.getString("firstName");
        String lastName=resultSet.getString("lastName");
        String email=resultSet.getString("email");
        String address=resultSet.getString("address");
        String address2=resultSet.getString("address2");
        String phone=resultSet.getString("phone");
        int consumptionNumber=resultSet.getInt("consumptionNumber");
        return  new Customer(id,firstName,lastName,email,address,address2,phone,consumptionNumber);
    }

    @Override
    public ObservableList<Customer> getAllDataFromResultSet(ResultSet resultSet) throws SQLException {
        ObservableList<Customer> listOfCustomers= FXCollections.observableArrayList();

        while (resultSet.next()) {
            int id=resultSet.getInt("id");

            String firstName=resultSet.getString("first_name");
            String lastName=resultSet.getString("last_name");
            String email=resultSet.getString("email");
            String address=resultSet.getString("address");
            String address2=resultSet.getString("address2");
            String phone=resultSet.getString("phone");
            int consumptionNumber=resultSet.getInt("consumption_number");
            listOfCustomers.add(new Customer(id,firstName,lastName,email,address,address2,phone,consumptionNumber));
        }

        return listOfCustomers;
    }
}
