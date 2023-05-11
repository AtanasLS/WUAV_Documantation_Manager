package main.java.dal.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.User;
import main.java.dal.DataAccessManager;
import main.java.dal.interfaces.DAOInterface;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAOInterface<User> {
    DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public User getFromDatabase(int id) throws SQLException, IOException {
        String query="SELECT * FROM [WUAV_Documentation_System].[dbo].[users] WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        System.out.println(id);
        ResultSet resultSet =stmt.executeQuery();

       return this.getDataFromResultSet(resultSet);
    }

    public User getTechnicianFromDatabase(int id) throws SQLException, IOException {
        String query="SELECT * FROM users WHERE id=? AND type=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        stmt.setString(2,"technician");

        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public ObservableList<User> getAllFromDatabase() throws SQLException, IOException {
        String query="SELECT * FROM [WUAV_Documentation_System].[dbo].[users];";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<User>) this.getAllDataFromResultSet(resultSet);

    }

    public ObservableList<User> getAllTechniciansFromDatabase() throws SQLException, IOException {
        String query="SELECT * FROM [WUAV_Documentation_System].[dbo].[users] WHERE type=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,"Technician");
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<User>) this.getAllDataFromResultSet(resultSet);

    }

    @Override
    public String insertIntoDatabase(User object) throws SQLException, FileNotFoundException {

        String username=object.getUsername();
        String firstName=object.getFirstName();
        String lastName=object.getLastName();
        String email=object.getEmail();
        String password= object.getPassword();
        password=hashPassword(password);
        String type=object.getType();

        File file = new File(object.getImg());
        FileInputStream input = new FileInputStream(file);

        String query="INSERT INTO [WUAV_Documentation_System].[dbo].[users] VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,username);
        stmt.setString(2,firstName);
        stmt.setString(3,lastName);
        stmt.setString(4,email);
        stmt.setString(5,password);
        stmt.setString(6,type);
        stmt.setBinaryStream(7,input);


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

        String query="DELETE FROM user WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();
        return resultSet.toString();
    }

    @Override
    public String updateDatabase(User object) throws SQLException, FileNotFoundException {
        int id=object.getId();
        String username=object.getUsername();
        String firstName=object.getFirstName();
        String lastName=object.getLastName();
        String email=object.getEmail();
        String password= object.getPassword();
        password=hashPassword(password);

        String type=object.getType();
        File file = new File(object.getImg());
        FileInputStream input = new FileInputStream(file);


        System.out.println(password);
        System.out.println(id);

        String query="UPDATE users set username = ?,first_name = ?,last_name = ?,email = ?,password = ?,type = ?, img=? WHERE id = ?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,username);
        stmt.setString(2,firstName);
        stmt.setString(3,lastName);
        stmt.setString(4,email);
        stmt.setString(5,password);
        stmt.setString(6,type);
        stmt.setBinaryStream(7,input);

        stmt.setInt(8,id);

        try {
            ResultSet resultSet = stmt.executeQuery();
            //System.out.println(resultSet.toString());
        }catch (RuntimeException e){
            System.out.println(e);
        }
        return "Work!";
    }

    @Override
    public User getDataFromResultSet(ResultSet resultSet) throws SQLException, IOException {


        if (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString("id"));
            String username = resultSet.getString("username");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String type = resultSet.getString("type");

            File file = new File(username+ ".png");
            String img= username+ ".png";
            FileOutputStream output = new FileOutputStream(file);

            InputStream input = resultSet.getBinaryStream("img");
            byte[] buffer = new byte[1024];
            while (input.read(buffer) > 0) {
                output.write(buffer);
            }
            return new User(id, username, firstName, lastName, email, password, type,img);
        }
        return null;

    }

    @Override
    public ObservableList<User> getAllDataFromResultSet(ResultSet resultSet) throws SQLException, IOException {

        ObservableList<User> listOfUsers= FXCollections.observableArrayList();

        while (resultSet.next()) {
            int id=Integer.parseInt(resultSet.getString("id"));
            String username = resultSet.getString("username");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String type = resultSet.getString("type");


            File file = new File(username+ ".png");
            String img= username+ ".png";
            FileOutputStream output = new FileOutputStream(file);


            InputStream input = resultSet.getBinaryStream("img");
            if (input!=null) {
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
            }
            listOfUsers.add(new User(id,username, firstName, lastName, email, password, type, img));
        }

        return listOfUsers;
    }


    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }


}
