package main.java.gui.controllers.infoPageController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderINfoController implements Initializable,Info
{

    public Label name,user,project,customer,date,price;
    private MainModel model;
    private DeleteModel deleteModel;
    private EditModel editModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setMainModel(MainModel mvm) {
       this.model=mvm;
       this.editModel=new EditModel();
       this.deleteModel=new DeleteModel();

    }

    @Override
    public void setInfoLabels() {
        this.customer.setText(model.getSelectedOrder().getCustomer());
        this.name.setText(model.getSelectedOrder().getName());
        this.user.setText(model.getSelectedOrder().getUserName());
        this.project.setText(model.getSelectedOrder().getProject());
        this.price.setText(String.valueOf(model.getSelectedOrder().getPrice()));
        this.date.setText(String.valueOf(model.getSelectedOrder().getDate()));


    }

    @Override
    public String delete(String id) {
        return this.deleteModel.deleteFromDatabase(id,"Order");
    }

    @Override
    public void edit() {

    }
}
