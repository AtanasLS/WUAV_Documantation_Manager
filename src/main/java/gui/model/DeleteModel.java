package main.java.gui.model;

import main.java.bll.AppLogicManager;

public class DeleteModel {

    private AppLogicManager appLogicManager;

    public DeleteModel() {

        this.appLogicManager = new AppLogicManager();
    }

    public String deleteFromDatabase(int index, String type){
        return this.appLogicManager.deleteFromDatabase(index,type);
    }
}
