package main.java.dal.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import main.java.be.Customer;
import main.java.be.Document;
import main.java.dal.DataAccessManager;
import main.java.dal.interfaces.DAOInterface;

import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO implements DAOInterface<Document> {
    DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public Document getFromDatabase(int id) throws SQLException, IOException {
        String query="SELECT * FROM document WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public ObservableList<Document> getAllFromDatabase() throws SQLException, IOException {
        String query="SELECT * FROM document;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<Document>) this.getAllDataFromResultSet(resultSet);
    }

    @Override
    public String insertIntoDatabase(Document object) throws SQLException {
      //  String layoutDrawing=object.getLayoutDrawing();
        String description=object.getDescription();
        LocalDate date =  object.getDate();

        String query="INSERT INTO document VALUES (?, ?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
      //  stmt.setObject(1,layoutDrawing);
        stmt.setString(2,description);
        stmt.setObject(3,date);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {
        String query="DELETE FROM document WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();
        return resultSet.toString();
    }

    @Override
    public String updateDatabase(Document object) throws SQLException {

        int id=object.getId();
       // String layoutDrawing=object.getDrawing();
        String description=object.getDescription();
        LocalDate date=  object.getDate();

        String query="INSERT INTO document VALUES (?, ?, ?) WHERE id = ?;;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
       // stmt.setObject(1,layoutDrawing);
        stmt.setString(2,description);
        stmt.setObject(3,date);
        stmt.setInt(4,id);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }


    @Override
    public Document getDataFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        int id=resultSet.getInt("id");

        String layoutDrawing =  resultSet.getObject("layout_drawing").toString();
        String description = resultSet.getString("description");
        int loginID = resultSet.getInt("login_id");
        LocalDate date = resultSet.getDate("date").toLocalDate();
        int userID = resultSet.getInt("userId");
        int customerID = resultSet.getInt("customerId");
        int projectID = resultSet.getInt("projectId");
        String name = resultSet.getString("name");

        return new Document(id,layoutDrawing,description, loginID, name, userID, customerID, projectID, date);
    }

    @Override
    public ObservableList<Document> getAllDataFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        ObservableList<Document> listOfDocuments= FXCollections.observableArrayList();

        while (resultSet.next()) {

            /*
            javafx.scene.image.Image layoutDrawing= (javafx.scene.image.Image) resultSet.getObject("layout-drawing");
            String description=resultSet.getString("description");
            Date date=resultSet.getDate("date");
             */
            int id=resultSet.getInt("id");
            String layoutDrawing =  resultSet.getObject("layout_drawing").toString();
            String description = resultSet.getString("description");
            int loginID = resultSet.getInt("login_id");
            LocalDate date = resultSet.getDate("date").toLocalDate();
            int userID = resultSet.getInt("userId");
            int customerID = resultSet.getInt("customerId");
            int projectID = resultSet.getInt("projectId");
            String name = resultSet.getString("name");

            listOfDocuments.add(new Document(id,layoutDrawing,description, loginID, name, userID, customerID, projectID, date));
        }

        return listOfDocuments;
    }
}