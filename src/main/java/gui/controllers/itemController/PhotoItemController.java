package main.java.gui.controllers.itemController;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PhotoItemController implements Initializable {

    public ImageView selectedPhoto;
    public Label nameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setItems(Image image, String fileName){
        selectedPhoto.setImage(image);
        nameLabel.setText(fileName);
    }
}
