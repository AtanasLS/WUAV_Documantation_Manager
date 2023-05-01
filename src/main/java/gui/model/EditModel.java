package main.java.gui.model;

import main.java.bll.AppLogicManager;

public class EditModel {
    private AppLogicManager appLogicManager;

    public EditModel(){
        this.appLogicManager=new AppLogicManager();
    }

    public String updateDatabaseElement(Object object,String id,String type){
        return this.appLogicManager.updateDatabase(object,id,type);
    }
}
