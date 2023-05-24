package main.java.dal.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import main.java.be.Document;
import main.java.dal.DataAccessManager;
import main.java.dal.interfaces.DAOInterface;

import java.io.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DocumentDAO implements DAOInterface<Document> {
    private final DataAccessManager dataAccessManager = new DataAccessManager();

    @Override
    public Document getFromDatabase(int id) throws SQLException, IOException {
        String query = "SELECT * FROM document WHERE id=?;";
        try (PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            return getDataFromResultSet(resultSet);
        }
    }

    @Override
    public ObservableList<Document> getAllFromDatabase() throws SQLException, IOException {
        String query = "SELECT * FROM [WUAV_Documentation_System].[dbo].[documentation];";
        try (PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            return getAllDataFromResultSet(resultSet);
        }
    }

    public ObservableList<Document> getEditedFromDatabase() throws SQLException, IOException {
        String query = "SELECT * FROM [WUAV_Documentation_System].[dbo].[documentation] WHERE [type]=1;";
        try (PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            return getAllDataFromResultSet(resultSet);
        }
    }

    @Override
    public String insertIntoDatabase(Document object) throws SQLException, FileNotFoundException {
        try (FileInputStream input = new FileInputStream(object.getLayoutDrawing());
             PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement("INSERT INTO documentation VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            stmt.setBinaryStream(1, input);
            stmt.setString(2, object.getDescription());
            stmt.setInt(3, object.getLoginId());
            stmt.setObject(4, object.getDate());
            stmt.setInt(5, object.getUser());
            stmt.setInt(6, object.getCustomer());
            stmt.setInt(7, object.getProject());
            stmt.setString(8, object.getName());
            stmt.setInt(9, object.getType());
            stmt.executeUpdate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Insertion successful.";
    }

    @Override
    public String deleteFromDatabase(int id) throws SQLException {
        try (PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement("DELETE FROM documentation WHERE id = ?")) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return "Rows affected: " + affectedRows;
        }
    }

    @Override
    public String updateDatabase(Document object) throws SQLException, FileNotFoundException {
        object.setLayoutDrawing("src/main/resources/images/" + object.getName() + ".png");
        try (FileInputStream input = new FileInputStream(object.getLayoutDrawing());
             PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement("UPDATE documentation SET layout_drawing = ?, description = ?, login_id = ?, [date] = ?, userId = ?, customerId = ?, projectId = ?, [name] = ?, [type] = ? WHERE id = ?")) {
            stmt.setBinaryStream(1, input);
            stmt.setString(2, object.getDescription());
            stmt.setInt(3, object.getLoginId());
            stmt.setObject(4, object.getDate());
            stmt.setInt(5, object.getUser());
            stmt.setInt(6, object.getCustomer());
            stmt.setInt(7, object.getProject());
            stmt.setString(8, object.getName());
            stmt.setInt(9, object.getType());
            stmt.setInt(10, object.getId());
            stmt.executeUpdate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Update successful.";
    }

    @Override
    public Document getDataFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        int id = resultSet.getInt("id");
        String description = resultSet.getString("description");
        int loginID = resultSet.getInt("login_id");
        LocalDate date = resultSet.getDate("date").toLocalDate();
        int userID = resultSet.getInt("userId");
        int customerID = resultSet.getInt("customerId");
        int projectID = resultSet.getInt("projectId");
        String name = resultSet.getString("name");
        int type = resultSet.getInt("type");

        File file = new File(name + ".png");
        String layoutDrawing = name + ".png";
        try (FileOutputStream output = new FileOutputStream(file);
             InputStream input = resultSet.getBinaryStream("layout_drawing")) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }

        return new Document(id, layoutDrawing, description, loginID, name, userID, customerID, projectID, date, type);
    }

    @Override
    public ObservableList<Document> getAllDataFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        ObservableList<Document> listOfDocuments = FXCollections.observableArrayList();
        while (resultSet.next()) {
            listOfDocuments.add(getDataFromResultSet(resultSet));
        }
        return listOfDocuments;
    }

    public String saveLayoutDrawing(Document document) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Layout Drawing");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            String layoutDrawingPath = file.getPath();
            document.setLayoutDrawing(layoutDrawingPath);
            try (FileInputStream input = new FileInputStream(document.getLayoutDrawing());
                 PreparedStatement stmt = dataAccessManager.getConnection().prepareStatement("UPDATE documentation SET layout_drawing = ? WHERE id = ?")) {
                stmt.setBinaryStream(1, input);
                stmt.setInt(2, document.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error occurred while saving the layout drawing.";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "Layout drawing saved successfully.";
        } else {
            return "Saving cancelled.";
        }
    }
}