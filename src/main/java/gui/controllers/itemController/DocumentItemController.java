package main.java.gui.controllers.itemController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.gui.model.MainModel;

import java.net.URL;
import java.util.ResourceBundle;

public class DocumentItemController implements Initializable,Items {
    public Label documentName, creator, date, project, customer;
    private MainModel model ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        try {
            this.model.loadDocument();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setLabels(int numberOfElement, MainModel model) {
        documentName.setText(this.model.getAllDocuments().get(numberOfElement).getName());
        creator.setText(this.model.getAllDocuments().get(numberOfElement).getCreator());
        date.setText(this.model.getAllDocuments().get(numberOfElement ).getDate().toString());
        project.setText(this.model.getAllDocuments().get(numberOfElement).getProject());
        customer.setText(this.model.getAllDocuments().get(numberOfElement ).getCustomer());

    }
}
