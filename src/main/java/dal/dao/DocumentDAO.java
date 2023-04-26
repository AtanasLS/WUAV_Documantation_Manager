package main.java.dal.dao;

import main.java.be.Document;
import main.java.be.User;
import main.java.dal.DataAccessManager;

import java.awt.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO implements DAOInterface<Document> {
    DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public Document getFromDatabase(int id) throws SQLException {
        String query="SELECT * FROM document WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public List<Document> getAllFromDatabase() throws SQLException {
        String query="SELECT * FROM document;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return this.getAllDataFromResultSet(resultSet);
    }

    @Override
    public String insertIntoDatabase(Document object) throws SQLException {
        Image layoutDrawing=object.getLayoutDrawing();
        String description=object.getDescription();
        Date date= (Date) object.getDate();

        String query="INSERT INTO document VALUES (?, ?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setObject(1,layoutDrawing);
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
    public String updateDatabase(Document object, int id) throws SQLException {

        Image layoutDrawing=object.getLayoutDrawing();
        String description=object.getDescription();
        Date date= (Date) object.getDate();

        String query="INSERT INTO document VALUES (?, ?, ?) WHERE id = ?;;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setObject(1,layoutDrawing);
        stmt.setString(2,description);
        stmt.setObject(3,date);
        stmt.setInt(4,id);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }


    @Override
    public Document getDataFromResultSet(ResultSet resultSet) throws SQLException {
        Image layoutDrawing=resultSet.getObject("layoutDrawing", Image.class);
        String description=resultSet.getString("description");
        Date date=resultSet.getDate("date");

        return  new Document(layoutDrawing,description,date);
    }

    @Override
    public List<Document> getAllDataFromResultSet(ResultSet resultSet) throws SQLException {
        List<Document> listOfDocuments=new ArrayList<>();

        while (resultSet.next()) {

            Image layoutDrawing=resultSet.getObject("layoutDrawing", Image.class);
            String description=resultSet.getString("description");
            Date date=resultSet.getDate("date");
            listOfDocuments.add(new Document(layoutDrawing,description,date));
        }

        return listOfDocuments;    }
}
