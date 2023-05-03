package main.java.gui.controllers.createController;

import javafx.event.ActionEvent;
import main.java.gui.model.MainModel;

public interface CreateController {
    void setModel(MainModel model);
     void handleSave(ActionEvent actionEvent);

    void handleCancel(ActionEvent actionEvent);
}
