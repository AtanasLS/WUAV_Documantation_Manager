package main.java.gui.controllers.createController;

import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.sql.SQLException;

public interface CreateController {
    void setModel(MainModel model) throws SQLException;
    void handleSave(ActionEvent actionEvent) throws DocumentException, IOException;

    void handleCancel(ActionEvent actionEvent);

    void checkData();
}