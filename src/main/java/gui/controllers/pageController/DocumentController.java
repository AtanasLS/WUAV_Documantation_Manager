package main.java.gui.controllers.pageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.gui.controllers.createController.CreateDocumentController;
import main.java.gui.controllers.itemController.DocumentItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DocumentController implements Initializable {

    @FXML
    VBox pnItems = null;

    MainModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
    }
    public void setPnItems(String type){
        if (type.equals("Users")) {

            try {
                model.loadDocument();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //System.out.println(model.getAllCustomers());
            Node[] nodes = new Node[model.getAllDocuments().size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/DocumentItem.fxml"));
                    nodes[i] = loader.load();
                    DocumentItemController controller = loader.getController();
                    controller.setLabels(i);
                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if (type.equals("Technician")){

            try {
                model.loadEditedDocuments();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //System.out.println(model.getAllCustomers());
            Node[] nodes = new Node[model.getEditedDocuments().size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/DocumentItem.fxml"));
                    nodes[i] = loader.load();
                    DocumentItemController controller = loader.getController();
                    controller.setLabels(i);
                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createDocument(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/create/CreateDocumentView.fxml"));
        Parent root = loader.load();
        CreateDocumentController controller = loader.getController();
        controller.setModel(model);
        //controller.setInformation();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
