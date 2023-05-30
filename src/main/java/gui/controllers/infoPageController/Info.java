package main.java.gui.controllers.infoPageController;

import javafx.event.ActionEvent;
import main.java.gui.model.MainModel;

import java.sql.SQLException;
public interface Info {


    void setMainModel(MainModel mvm) throws SQLException;
    void setInfoLabels();
    String delete(int id);

}
