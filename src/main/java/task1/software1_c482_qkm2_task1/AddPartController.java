package task1.software1_c482_qkm2_task1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.io.IOException;
import java.util.*;

public class AddPartController implements Initializable {
    public ToggleGroup group1;
    public RadioButton inHouseSelect_addPart;
    public RadioButton outSourcedSelect_addPart;
    public TextField ID_addPart;
    public TextField inv_addPart;
    public TextField name_addPart;
    public TextField priceCost_addPart;
    public TextField max_addPart;
    public TextField min_addPart;
    public TextField flexField_addPart;
    public Text flexLabel_addPart;
    public Button save_addPart;
    public Button cancel_addPart;


    public void  initialize(URL url, ResourceBundle resourceBundle){

    }

    public void OnRadioClick(ActionEvent actionEvent) {

        if (inHouseSelect_addPart.isSelected()){
            flexLabel_addPart.setText("Machine ID");
            flexField_addPart.setText("");
        } else if (outSourcedSelect_addPart.isSelected()) {
            flexLabel_addPart.setText("Company Name");
            flexField_addPart.setText("");
        }
    }


    public void onCancelClick(ActionEvent actionEvent) throws IOException {

        boolean response;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Do you wish to exit without saving?");
        alert.setContentText("Choose Ok to continue or Cancel to continue filling out the part info.");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        response = result == ButtonType.OK;

        if (response) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene cancel = new Scene(root, 1000, 400);
                stage.setTitle("");
                stage.setScene(cancel);
                stage.show();
            }
    }

    public void onSaveClick(ActionEvent actionEvent) throws IOException {

        boolean textError = false;
        boolean errorFree = true;
        boolean ParseError = false;
        String parseErrorText = "";

        String partName = null;
        double price = 0;
        int stock = 0;
        int min = 0;
        int max = 0;
        int machID = 0;
        String compName = null;


        if (name_addPart.getText().isEmpty() || name_addPart.getText().isEmpty() || name_addPart.getText() == null){
            textError = true;
        } else if (inv_addPart.getText().isEmpty() || inv_addPart.getText().isEmpty() || inv_addPart.getText() == null) {
            textError = true;
        } else if (priceCost_addPart.getText().isEmpty() || priceCost_addPart.getText().isEmpty() || priceCost_addPart.getText() == null) {
            textError = true;
        } else if (max_addPart.getText().isEmpty() || max_addPart.getText().isEmpty() || max_addPart.getText() == null) {
            textError = true;
        } else if (min_addPart.getText().isEmpty() || min_addPart.getText().isEmpty() || min_addPart.getText() == null) {
            textError = true;
        } else if (flexField_addPart.getText().isEmpty() || flexField_addPart.getText().isEmpty() || flexField_addPart.getText() == null) {
            textError = true;
        }


        if (textError) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please populate a value in each field to continue.");
            alert.showAndWait();
        }else {

            try {
                try {
                    if (Integer.parseInt(name_addPart.getText()) >= 0){
                        ParseError = true;
                        parseErrorText = "Name is not equal to a String. ";
                    }
                }catch (Exception convertToString){
                    partName = name_addPart.getText();
                }

                try {
                    if (Double.parseDouble(name_addPart.getText()) >= 0.00){
                        ParseError = true;
                        parseErrorText = "Name is not equal to a String. ";
                    }
                }catch (Exception convertToString){
                    partName = name_addPart.getText();
                }

            }catch (Exception cannotConvertString){

            }

            try {
                price = Double.parseDouble(priceCost_addPart.getText());
            }catch (Exception cannotConvertDouble){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert price to double. ";
            }

            try {
                stock = Integer.parseInt(inv_addPart.getText());
            }catch (Exception cannotConvertNum){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert Inv to a Integer. ";
            }

            try {
                min = Integer.parseInt(min_addPart.getText());
            }catch (Exception cannotConvertNum){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert min to a Integer. ";
            }

            try {
                max = Integer.parseInt(max_addPart.getText());
            }catch (Exception cannotConvertNum){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert max to a Integer. ";
            }

            if (inHouseSelect_addPart.isSelected()){
                try {
                    machID = Integer.parseInt(flexField_addPart.getText());
                }catch (Exception cannotConvertNum){
                    ParseError = true;
                    parseErrorText = parseErrorText + "Cannot convert machine ID to a Integer. ";
                }
            }else {
                try {
                    try {
                        if (Integer.parseInt(flexField_addPart.getText()) >= 0){
                            ParseError = true;
                            parseErrorText = "Name is not equal to a String. ";
                        }
                    }catch (Exception convertToString){
                        compName = flexField_addPart.getText();
                    }

                    try {
                        if (Double.parseDouble(flexField_addPart.getText()) >= 0.00){
                            ParseError = true;
                            parseErrorText = "Name is not equal to a String. ";
                        }
                    }catch (Exception convertToString){
                        compName = flexField_addPart.getText();
                    }

                }catch (Exception cannotConvertString){

                }
            }

            if (ParseError){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(parseErrorText);
                alert.showAndWait();
            }else {

                if (min > max) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setContentText("The minimum value cannot be greater than the max value.");
                    alert1.showAndWait();
                    min_addPart.setText("");
                    errorFree = false;
                } else if (stock > max || stock < min) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setContentText("The Inv value cannot be greater than the max value or less than min value.");
                    alert1.showAndWait();
                    inv_addPart.setText("");
                    errorFree = false;
                }


                if (inHouseSelect_addPart.isSelected() && errorFree) {
                    int id = Main.UniqueIDs.setID();

                    InHouse inHouse = new InHouse(id, partName, price, stock, min, max, machID);
                    Inventory.addPart(inHouse);
                }

                if (outSourcedSelect_addPart.isSelected() && errorFree) {
                    int id = Main.UniqueIDs.setID();

                    Outsourced outsourced = new Outsourced(id, partName, price, stock, min, max, compName);
                    Inventory.addPart(outsourced);
                }


                if (errorFree) {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));
                    Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    Scene cancel = new Scene(root, 1000, 400);
                    stage.setTitle("");
                    stage.setScene(cancel);
                    stage.show();
                }
            }
        }

    }
}
