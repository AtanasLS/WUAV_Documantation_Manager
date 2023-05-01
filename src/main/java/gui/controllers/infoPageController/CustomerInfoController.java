package main.java.gui.controllers.infoPageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.gui.controllers.CustomerEditController;
import main.java.gui.controllers.LoginPageController;
import main.java.gui.model.DeleteModel;
import main.java.gui.model.EditModel;
import main.java.gui.model.MainModel;

import java.awt.*;
import java.io.IOException;
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
        this.editModel=new EditModel(model);
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


    public void handleEdit(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit/CustomerEdit.fxml"));
        Parent root = loader.load();
        CustomerEditController controller = loader.getController();
        controller.setMainModel(model);
        //controller.setInformation();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) ((javafx.scene.control.Button) actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void handleDelete(ActionEvent actionEvent) {
    }

    @Override
    public String delete(String id) {
        return this.deleteModel.deleteFromDatabase(id,"Customer");
    }

    @Override
    public void edit(java.awt.event.ActionEvent actionEvent) {

    }


}
