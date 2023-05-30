package main.test;

import javafx.collections.ObservableList;
import main.java.be.Document;
import main.java.dal.dao.DocumentDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

class DocumentDAOTest {
    private DocumentDAO documentDAO;

    @BeforeEach
    void setUp() {
        documentDAO = new DocumentDAO();
    }

    @Test
    void testGetFromDatabase() {
        int documentId = 28; // Provide an existing document ID from the database
        try {
            Document document = documentDAO.getFromDatabase(documentId);
            Assertions.assertNotNull(document);
            Assertions.assertEquals(documentId, document.getId(), "Document ID should match");
            Assertions.assertNotNull(document.getName(), "Document name should not be null");
            Assertions.assertNotNull(document.getDescription(), "Document description should not be null");
            Assertions.assertNotNull(document.getDate(), "Document date should not be null");

        } catch (SQLException | IOException e) {
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testGetAllFromDatabase() {
        try {
            ObservableList<Document> documents = documentDAO.getAllFromDatabase();
            Assertions.assertNotNull(documents);
            // Add additional assertions based on your expected results
        } catch (SQLException | IOException e) {
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }
    @Test
    void testGetEditedFromDatabase() {
        try {
            ObservableList<Document> editedDocuments = documentDAO.getEditedFromDatabase();
            Assertions.assertNotNull(editedDocuments);

        } catch (SQLException | IOException e) {
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testInsertIntoDatabase() {
        Document document = new Document(
                "Pizza Installation.png",
                "Description",
                5,
                "Document Name",
                23,
                20,
                11,
                LocalDate.now(),
                0
        );
        try {
            String result = documentDAO.insertIntoDatabase(document);
            Assertions.assertNotNull(result);
            Assertions.assertEquals("Success", result);
        } catch (SQLException | FileNotFoundException e) {
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }
    @Test
    void testDeleteFromDatabase() {
        int documentId = 32; // Provide an existing document ID from the database
        try {
            String result = documentDAO.deleteFromDatabase(documentId);
            Assertions.assertNotNull(result);
            // Assert that the result contains the expected success message
            Assertions.assertTrue(result.contains("Rows affected"), "Delete operation should have affected rows");

        } catch (SQLException e) {
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }
    @Test
    void testUpdateDatabase() throws SQLException, IOException {
        Document document = documentDAO.getFromDatabase(22);
        try {
            String result = documentDAO.updateDatabase(document);
            Assertions.assertNotNull(result);
            // Add additional assertions based on your expected results
        } catch (SQLException | FileNotFoundException e) {
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }



}