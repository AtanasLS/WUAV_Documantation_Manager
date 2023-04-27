package main.java.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DocumentController implements Initializable {

    @FXML
    VBox pnItems = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/view/items/DocumentItem.fxml"));

                //give the items some effect TODO - NEED TO BE FIXED
                 /*

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : grey");
                });

                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #grey");
                });
                 */
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
