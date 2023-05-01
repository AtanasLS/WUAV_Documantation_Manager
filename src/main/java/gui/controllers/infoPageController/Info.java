package main.java.gui.controllers.infoPageController;

import main.java.gui.model.MainModel;

public interface Info {


    public void setMainModel(MainModel mvm);
    public void setInfoLabels();

    public String delete(String id);

    public void edit();

}
