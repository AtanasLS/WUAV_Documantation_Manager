package main.java.gui.controllers.createController;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import main.java.be.Customer;
import main.java.gui.model.CreateModel;
import main.java.gui.model.MainModel;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CreateCustomerController implements Initializable, CreateController {
    public TextField firstName , lastName, email, phoneNum, address1, address2;
    public Button saveBtn, cancelBtn;


    private CreateModel createModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.checkData();
    }

    public void setModel(MainModel model){
        this.createModel = new CreateModel(model);
    }

    public void handleSave(ActionEvent actionEvent) {
        Customer newCustomer = new Customer(firstName.getText(), lastName.getText(),email.getText(),address1.getText()
                , address2.getText(),phoneNum.getText(), 1);
        createModel.createInDatabase(newCustomer, "Customer");

        Stage currentStage = (Stage) saveBtn.getScene().getWindow();
        currentStage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage currentStage = (Stage) cancelBtn.getScene().getWindow();
        currentStage.close();
    }

    public void checkData(){
        Pattern name = Pattern.compile("[A-Za-z\\s]{1,}");
        TextFormatter<String> formatter = new TextFormatter<>( change -> {
            System.out.println(change.getControlNewText().matches("[A-Za-z\\s]{1,}"));
            if (change.getControlNewText().matches(name.pattern())) {
                System.out.println("work");
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        TextFormatter<String> formatter1 = new TextFormatter<>(change -> {
            System.out.println(change);
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        TextFormatter<String> formatter2 = new TextFormatter<>(change -> {
            if (name.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern mail = Pattern.compile("[a-zA-Z0-9._%+-@]{1,}");
        TextFormatter<String> formatterMail = new TextFormatter<>(change -> {
            System.out.println(change.getControlNewText().matches(mail.pattern()));
            if (mail.matcher(change.getControlNewText()).matches()) {
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern phone = Pattern.compile("[\\d-\\s]{1,}");
        TextFormatter<Integer> formatterPhone = new TextFormatter<>(change -> {
            System.out.println(change.getControlNewText().matches(phone.pattern()));
            if (phone.matcher(change.getControlNewText()).matches()) {
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern address = Pattern.compile("[A-Za-z0-9\\s,.]+");
        TextFormatter<String> formatterAddress = new TextFormatter<>(change -> {
            if (address.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });

        Pattern pass = Pattern.compile("[A-Za-z\\s1-9\\s]{1,}");
        TextFormatter<String> formatterPass = new TextFormatter<>(change -> {
            if (pass.matcher(change.getControlNewText()).matches()) {
                // todo: remove error message/markup
                return change; // allow this change to happen
            } else {
                return null; // prevent change
            }
        });


        email.setTextFormatter(formatterMail);
        firstName.setTextFormatter(formatter);
        lastName.setTextFormatter(formatter1);
        this.phoneNum.setTextFormatter(formatterPhone);
        this.address1.setTextFormatter(formatterAddress);
        this.address2.setTextFormatter(formatter2);
        this.email.setTextFormatter(formatterMail);



    }

}
