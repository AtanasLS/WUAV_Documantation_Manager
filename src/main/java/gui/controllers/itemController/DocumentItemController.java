package main.java.gui.controllers.itemController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.be.Project;
import main.java.be.User;
import main.java.gui.controllers.editController.DocumentEditController;
import main.java.gui.controllers.infoPageController.UserInfoController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DocumentItemController implements Initializable {
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


    public void setLabels(int numberOfElement) {

        model.setSelectedDocument(model.getAllDocuments().get(numberOfElement));

        User selectedUser = (User) model.getSelectedObject(model.getAllDocuments().get(numberOfElement).getUser(), "User");
        Project selectedProject = (Project) model.getSelectedObject(model.getAllDocuments().get(numberOfElement).getProject(), "Project");
        Customer selectedCustomer = (Customer) model.getSelectedObject(model.getAllDocuments().get(numberOfElement).getCustomer(), "Customer");
        documentName.setText(this.model.getAllDocuments().get(numberOfElement).getName());
        creator.setText(selectedUser.getFirstName() + " " + selectedUser.getLastName());
        date.setText(this.model.getAllDocuments().get(numberOfElement ).getDate().toString());
        project.setText(selectedProject.toString());
        customer.setText(selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());

    }
    public void infoBtnHandle(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit/DocumentEdit.fxml"));
        Parent root = loader.load();
        DocumentEditController controller = loader.getController();
        controller.setMainModel(this.model);
      //  controller.setInfoLabels();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.show();
    }
}
