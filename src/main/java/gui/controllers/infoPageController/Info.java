package main.java.gui.controllers.infoPageController;

import main.java.gui.model.MainModel;

import java.awt.event.ActionEvent;

public interface Info {


    public void setMainModel(MainModel mvm);
    public void setInfoLabels();

    public String delete(String id);

    public void edit(ActionEvent actionEvent);

}
