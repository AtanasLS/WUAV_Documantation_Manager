package main.java.dal.dao;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.be.Picture;
import main.java.dal.DataAccessManager;
import main.java.dal.interfaces.DAOInterface;

import java.awt.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PictureDAO implements DAOInterface<Picture> {

    DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public Picture getFromDatabase(int id) throws SQLException, IOException {
        String query="SELECT * FROM picture WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();

        return this.getDataFromResultSet(resultSet);
    }

    @Override
    public ObservableList<Picture> getAllFromDatabase() throws SQLException, IOException {
        String query="SELECT * FROM pictures;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<Picture>) this.getAllDataFromResultSet(resultSet);

    }


    public ObservableList<Picture> getAllPhotosForProject(int id) throws SQLException, IOException {
        String query="SELECT * FROM pictures where documentation_id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);

        ResultSet resultSet =stmt.executeQuery();

        return (ObservableList<Picture>) this.getAllDataFromResultSet(resultSet);
    }

    @Override
    public String insertIntoDatabase(Picture object) throws SQLException, FileNotFoundException {
        String name=object.getName();
        File file = new File(object.getInstallationPhoto());
        FileInputStream installationPhoto = new FileInputStream(file);
        int documentationID=object.getDocumentationID();

        String query="INSERT INTO pictures VALUES (?, ?, ?);";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,name);
        stmt.setBinaryStream(2,installationPhoto);
        stmt.setInt(3,documentationID);


        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();
    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {
        String query="DELETE FROM pictures WHERE id=?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet resultSet =stmt.executeQuery();
        return resultSet.toString();
    }

    @Override
    public String updateDatabase(Picture object) throws SQLException, FileNotFoundException {
        String name=object.getName();
        File file = new File(object.getInstallationPhoto());
        FileInputStream installationPhoto = new FileInputStream(file);
        int documentationID=object.getDocumentationID();
        int id = object.getId();

        String query="INSERT INTO pictures VALUES (?, ?, ?) WHERE id = ?;";
        PreparedStatement stmt=dataAccessManager.getConnection().prepareStatement(query);
        stmt.setString(1,name);
        stmt.setBinaryStream(2,installationPhoto);
        stmt.setInt(3,documentationID);
        stmt.setInt(4,id);

        ResultSet resultSet =stmt.executeQuery();

        return resultSet.toString();    }

    @Override
    public Picture getDataFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        int id=resultSet.getInt("id");
        String name=resultSet.getString("name");
        int documentationID=resultSet.getInt("documentationID");
        File file = new File("src/main/resources/images/"+name);
        String installationPhoto= name;
        FileOutputStream output = new FileOutputStream(file);

        InputStream input = resultSet.getBinaryStream("img");
        byte[] buffer = new byte[1024];
        while (input.read(buffer) > 0) {
            output.write(buffer);
        }

        return  new Picture(id,name,installationPhoto,documentationID);
    }

    @Override
    public ObservableList<Picture> getAllDataFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        ObservableList<Picture> listOfPicture= FXCollections.observableArrayList();

        while (resultSet.next()) {
            int id=resultSet.getInt("id");

            String name=resultSet.getString("name");
            int documentationID=resultSet.getInt("documentation_id");
            File file = new File("src/main/resources/images/"+name+ ".png");
            String installationPhoto= name;
            FileOutputStream output = new FileOutputStream(file);

            InputStream input = resultSet.getBinaryStream("img");
            byte[] buffer = new byte[1024];
            while (input.read(buffer) > 0) {
                output.write(buffer);
            }
            listOfPicture.add(new Picture(id,name,installationPhoto,documentationID));
        }

        return listOfPicture;
    }
}
