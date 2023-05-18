package main.java.gui.controllers.createController;

import javafx.event.ActionEvent;
import main.java.gui.model.MainModel;

import java.sql.SQLException;

public interface CreateController {
    void setModel(MainModel model) throws SQLException;
    void handleSave(ActionEvent actionEvent);

    void handleCancel(ActionEvent actionEvent);

    void checkData();
}