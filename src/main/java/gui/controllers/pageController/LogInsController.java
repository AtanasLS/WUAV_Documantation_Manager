package main.java.gui.controllers.pageController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.java.be.LogIns;
import main.java.bll.Filter;
import main.java.gui.controllers.itemController.LogInsItemController;
import main.java.gui.controllers.itemController.OrderItemController;
import main.java.gui.model.MainModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInsController implements Initializable {
    public TextField searchBar;
    @FXML
    VBox pnItems = null;



        MainModel model;
        private Filter filter;
        private ObservableList<LogIns> allLogins;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            this.model = new MainModel();
            this.filter = new Filter();
            this.allLogins = FXCollections.observableArrayList();

            searchBar.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    allLogins.clear();
                    try {
                        allLogins.addAll(filter.searchLogIns(newValue));
                        setPnItems(allLogins);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        }
        public void setPnItems(ObservableList<LogIns> searchedLogIns){
            pnItems.getChildren().clear();

            Node[] nodes = new Node[searchedLogIns.size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/LogIns.fxml"));
                    nodes[i] = loader.load();
                    LogInsItemController controller = loader.getController();
                    controller.setSearchedItemLabel(i, searchedLogIns);

                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        public void setModel(){
            try {
                model.loadLogIns();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Node[] nodes = new Node[model.getAllLogIns().size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/items/LogIns.fxml"));
                    nodes[i] = loader.load();
                    LogInsItemController controller = loader.getController();
                    controller.setLabels(i, model);

                    pnItems.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
