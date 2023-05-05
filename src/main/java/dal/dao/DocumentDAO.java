package main.java.dal.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import main.java.be.Customer;
import main.java.be.Document;
import main.java.dal.DataAccessManager;
import main.java.dal.interfaces.DAOInterface;

import java.awt.*;
import java.io.*;
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
        String query="SELECT * FROM [WUAV_Documentation_System].[dbo].[documentation];";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<Document>) this.getAllDataFromResultSet(resultSet);
    }

    @Override
    public String insertIntoDatabase(Document object) throws SQLException, FileNotFoundException {
        File file = new File(object.getLayoutDrawing());
        FileInputStream input = new FileInputStream(file);
        String description=object.getDescription();
        LocalDate date =  object.getDate();

        String query="INSERT INTO document VALUES (?, ?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setBinaryStream(1,input);
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
    public String updateDatabase(Document object) throws SQLException, FileNotFoundException {
        File file = new File(object.getLayoutDrawing());
        FileInputStream input = new FileInputStream(file);
        String description=object.getDescription();
        LocalDate date=  object.getDate();
        int id = object.getId();

        String query="INSERT INTO document VALUES (?, ?, ?) WHERE id = ?;;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setBinaryStream(1,input);
        stmt.setString(2,description);
        stmt.setObject(3,date);
        stmt.setInt(4,id);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }


    @Override
    public Document getDataFromResultSet(ResultSet resultSet) throws SQLException, IOException {

        String description = resultSet.getString("description");
        int loginID = resultSet.getInt("login_id");
        LocalDate date = resultSet.getDate("date").toLocalDate();
        int userID = resultSet.getInt("userId");
        int customerID = resultSet.getInt("customerId");
        int projectID = resultSet.getInt("projectId");
        String name = resultSet.getString("name");

        File file = new File(name+ ".png");
        String layoutDrawing= name+ ".png";
        FileOutputStream output = new FileOutputStream(file);

        InputStream input = resultSet.getBinaryStream("layout_drawing");
        byte[] buffer = new byte[1024];
        while (input.read(buffer) > 0) {
            output.write(buffer);
        }

        return new Document(layoutDrawing,description, loginID, name, userID, customerID, projectID, date);
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
            String description = resultSet.getString("description");
            int loginID = resultSet.getInt("login_id");
            LocalDate date = resultSet.getDate("date").toLocalDate();
            int userID = resultSet.getInt("userId");
            int customerID = resultSet.getInt("customerId");
            int projectID = resultSet.getInt("projectId");
            String name = resultSet.getString("name");
            File file = new File(name+ ".png");
            String layoutDrawing= name+ ".png";
            FileOutputStream output = new FileOutputStream(file);

            InputStream input = resultSet.getBinaryStream("layout_drawing");
            byte[] buffer = new byte[1024];
            while (input.read(buffer) > 0) {
                output.write(buffer);
            }

            listOfDocuments.add(new Document(layoutDrawing,description, loginID, name, userID, customerID, projectID, date));
        }

        return listOfDocuments;
    }
}
