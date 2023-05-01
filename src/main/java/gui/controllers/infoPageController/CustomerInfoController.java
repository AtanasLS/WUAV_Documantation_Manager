package main.java.gui.controllers.infoPageController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerInfoController implements Initializable , Info{
    @FXML
    public Label infoFirstNameLabel,getInfoLastNameLabel,infoPhoneNumLabel1,infoEmailLabel,infoFirstAddrssLabel,infoAddrssLabel,infoCosnuptionLabel;
    private MainModel model;
    private DeleteModel deleteModel;
    private EditModel editModel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.editModel=new EditModel();
        this.deleteModel=new DeleteModel();

    }
    public void setMainModel(MainModel mvm){
        model = mvm ;
    }
    public void setInfoLabels(){
        infoFirstNameLabel.setText(model.getSelectedCustomer().getFirstName());
        getInfoLastNameLabel.setText(model.getSelectedCustomer().getLastName());
        infoPhoneNumLabel1.setText((model.getSelectedCustomer().getPhone()));
        infoEmailLabel.setText(model.getSelectedCustomer().getEmail());
        infoFirstAddrssLabel.setText(model.getSelectedCustomer().getAddress());
        infoAddrssLabel.setText(model.getSelectedCustomer().getAddress2());
        infoCosnuptionLabel.setText(String.valueOf(model.getSelectedCustomer().getConsumptionNumber()));

    }

    @Override
    public String delete(String id) {
        return this.deleteModel.deleteFromDatabase(id,"Customer");
    }

    @Override
    public void edit() {

    }
}
