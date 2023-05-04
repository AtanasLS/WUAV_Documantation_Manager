package main.java.gui.model;

import main.java.bll.AppLogicManager;

public class CreateModel {
    private AppLogicManager appLogicManager;
    private MainModel model;

    public CreateModel(MainModel model) {
        this.model = model;
        this.appLogicManager = new AppLogicManager();
    }

    public String createInDatabase(Object object, String type){
        model.addObject(object, type);
        return this.appLogicManager.insertIntoDatabase(object,type);
    }
}
