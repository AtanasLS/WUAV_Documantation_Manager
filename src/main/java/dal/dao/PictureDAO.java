package main.java.dal.dao;

import main.java.be.Picture;
import main.java.be.User;
import main.java.dal.DataAccessManager;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PictureDAO implements DAOInterface<Picture> {

    DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public Picture getFromDatabase(int id) throws SQLException {
        String query="SELECT * FROM picture WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public List<Picture> getAllFromDatabase() throws SQLException {
        String query="SELECT * FROM picture;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return this.getAllDataFromResultSet(resultSet);

    }

    @Override
    public String insertIntoDatabase(Picture object) throws SQLException {
        String name=object.getName();
        Image installationPhoto=object.getInstallationPhoto();
        int documentationID=object.getDocumentationID();

        String query="INSERT INTO picture VALUES (?, ?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,name);
        stmt.setObject(2,installationPhoto);
        stmt.setInt(3,documentationID);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {
        String query="DELETE FROM picture WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();
        return resultSet.toString();
    }

    @Override
    public String updateDatabase(Picture object, int id) throws SQLException {
        String name=object.getName();
        Image installationPhoto=object.getInstallationPhoto();
        int documentationID=object.getDocumentationID();

        String query="INSERT INTO picture VALUES (?, ?, ?) WHERE id = ?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,name);
        stmt.setObject(2,installationPhoto);
        stmt.setInt(3,documentationID);
        stmt.setInt(4,id);

        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();    }

    @Override
    public Picture getDataFromResultSet(ResultSet resultSet) throws SQLException {
        String name=resultSet.getString("name");
        Image installationPhoto=resultSet.getObject("installationPhoto",Image.class);
        int documentationID=resultSet.getInt("documentationID");

        return  new Picture(name,installationPhoto,documentationID);
    }

    @Override
    public List<Picture> getAllDataFromResultSet(ResultSet resultSet) throws SQLException {
        List<Picture> listOfPicture=new ArrayList<>();

        while (resultSet.next()) {

            String name=resultSet.getString("name");
            Image installationPhoto=resultSet.getObject("installationPhoto",Image.class);
            int documentationID=resultSet.getInt("documentationID");
            listOfPicture.add(new Picture(name,installationPhoto,documentationID));
        }

        return listOfPicture;    }
}
