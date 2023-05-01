package main.java.gui.model;

import main.java.bll.AppLogicManager;

public class EditModel {
    private AppLogicManager appLogicManager;
    private MainModel model;
    public EditModel(MainModel model){
        this.model = model;
        this.appLogicManager=new AppLogicManager();
    }

    public String updateDatabaseElement(Object object,String id,String type){
        return this.appLogicManager.updateDatabase(object,id,type);
    }
}
