package main.java.gui.model;

import main.java.be.User;
import main.java.bll.AppLogicManager;

import java.sql.SQLException;

public class EditModel {
    private AppLogicManager appLogicManager;
    private MainModel model;
    public EditModel(MainModel model){
        this.model = model;
        this.appLogicManager=new AppLogicManager();
    }

    public String updateDatabaseElement(Object object,String id,String type) throws SQLException {
     //   model.updateUsers(object, id);
        return this.appLogicManager.updateDatabase(object,id,type);
    }
}
