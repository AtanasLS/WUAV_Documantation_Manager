package main.java.gui.controllers.pageController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import main.java.gui.controllers.itemController.OrderItemController;
import main.java.gui.controllers.itemController.TechnicianItemController;
import main.java.gui.model.MainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TechniciansController implements Initializable {
    @FXML
    VBox pnItems = null;

    MainModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = new MainModel();
        try {
            model.loadTech();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Node[] nodes = new Node[model.getAllTech().size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/TechniciansItem.fxml"));
                nodes[i] = loader.load();
                TechnicianItemController controller = loader.getController();

               // controller.setLabels(i);

                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
