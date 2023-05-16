package main.java.gui.controllers.infoPageController;

import main.java.gui.model.MainModel;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

public interface Info {


    public void setMainModel(MainModel mvm) throws SQLException;
    public void setInfoLabels();

    public String delete(int id);

    public void edit(ActionEvent actionEvent);

}
