package task1.software1_c482_qkm2_task1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This class runs all necessary steps to modify a part. It will also load the part on initialization.
 */
public class ModifyPartController implements Initializable {
    public ToggleGroup group1;
    public RadioButton inHouseSelect_modifyPart;
    public RadioButton outSourcedSelect_modifyPart;
    public TextField ID_modifyPart;
    public TextField inv_modifyPart;
    public TextField name_modifyPart;
    public TextField priceCost_modifyPart;
    public TextField max_modifyPart;
    public TextField min_modifyPart;
    public TextField flexField_modifyPart;
    public Text flexLabel_modifyPart;
    public Button save_modifyPart;
    public Button cancel_modifyPart;

    /**
     * When InHouse is selected it modifies the flexLabel to display "Machine ID", and when Outsourced is selected it will display "Company Name".
     * @param actionEvent
     */
    @FXML
    public void OnRadioClick(ActionEvent actionEvent) {

        if (inHouseSelect_modifyPart.isSelected()){
            flexLabel_modifyPart.setText("Machine ID");
            flexField_modifyPart.setText("");
        } else if (outSourcedSelect_modifyPart.isSelected()) {
            flexLabel_modifyPart.setText("Company Name");
            flexField_modifyPart.setText("");
        }
    }

    /**
     * When the Cancel Button is selected it will ask the user if they would like to cancel if they choose "Ok" it will take them to the Main menu.<br>
     * If they select "cancel" it will take them back to the current screen.
     * @param actionEvent
     * @throws IOException
     */
    public void onCancelClick(ActionEvent actionEvent) throws IOException {


        boolean response;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Do you wish to exit without saving?");
        alert.setContentText("Choose Ok to continue or Cancel to continue modifying part info.");
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

    /**
     * When the save button is clicked it will check to see if the part can be submitted, and after the logical test passes it will modify the part.
     * @param actionEvent
     * @throws IOException
     */
    public void onSaveClick(ActionEvent actionEvent) throws IOException {

        Part selectedPart = MainController.getSelectedPart();
        int index = Inventory.getAllParts().indexOf(selectedPart);


        boolean textError = false;
        boolean errorFree = true;
        boolean ParseError = false;
        String parseErrorText = "";

        String partName = null;
        double price = 0;
        int stock = 0;
        int min = 0;
        int max = 0;
        int machineID = 0;
        String company = null;

        if (name_modifyPart.getText().isEmpty() || name_modifyPart.getText().isEmpty() || name_modifyPart.getText() == null){
            textError = true;
        } else if (inv_modifyPart.getText().isEmpty() || inv_modifyPart.getText().isEmpty() || inv_modifyPart.getText() == null) {
            textError = true;
        } else if (priceCost_modifyPart.getText().isEmpty() || priceCost_modifyPart.getText().isEmpty() || priceCost_modifyPart.getText() == null) {
            textError = true;
        } else if (max_modifyPart.getText().isEmpty() || max_modifyPart.getText().isEmpty() || max_modifyPart.getText() == null) {
            textError = true;
        } else if (min_modifyPart.getText().isEmpty() || min_modifyPart.getText().isEmpty() || min_modifyPart.getText() == null) {
            textError = true;
        } else if (flexField_modifyPart.getText().isEmpty() || flexField_modifyPart.getText().isEmpty() || flexField_modifyPart.getText() == null) {
            textError = true;
        }


        if (textError) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please populate a value in each field to continue.");
            alert.showAndWait();
        }else {

            int id = Integer.parseInt(ID_modifyPart.getText());

            try {
                try {
                    if (Integer.parseInt(name_modifyPart.getText()) >= 0){
                        ParseError = true;
                        parseErrorText = "Name is not equal to a String. ";
                    }
                }catch (Exception convertToString){
                    partName = name_modifyPart.getText();
                }

                try {
                    if (Double.parseDouble(name_modifyPart.getText()) >= 0.00){
                        ParseError = true;
                        parseErrorText = "Name is not equal to a String. ";
                    }
                }catch (Exception convertToString){
                    partName = name_modifyPart.getText();
                }

            }catch (Exception cannotConvertString){

            }

            try {
                price = Double.parseDouble(priceCost_modifyPart.getText());
            }catch (Exception cannotConvertDouble){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert price to double. ";
            }

            try {
                stock = Integer.parseInt(inv_modifyPart.getText());
            }catch (Exception cannotConvertNum){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert Inv to a Integer. ";
            }

            try {
                min = Integer.parseInt(min_modifyPart.getText());
            }catch (Exception cannotConvertNum){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert min to a Integer. ";
            }

            try {
                max = Integer.parseInt(max_modifyPart.getText());
            }catch (Exception cannotConvertNum){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert max to a Integer. ";
            }

            if (inHouseSelect_modifyPart.isSelected()){
                try {
                    machineID = Integer.parseInt(flexField_modifyPart.getText());
                }catch (Exception cannotConvertNum){
                    ParseError = true;
                    parseErrorText = parseErrorText + "Cannot convert machine ID to a Integer. ";
                }
            }else {
                try {
                    try {
                        if (Integer.parseInt(flexField_modifyPart.getText()) >= 0){
                            ParseError = true;
                            parseErrorText = "Name is not equal to a String. ";
                        }
                    }catch (Exception convertToString){
                        company = flexField_modifyPart.getText();
                    }

                    try {
                        if (Double.parseDouble(flexField_modifyPart.getText()) >= 0.00){
                            ParseError = true;
                            parseErrorText = "Name is not equal to a String. ";
                        }
                    }catch (Exception convertToString){
                        company = flexField_modifyPart.getText();
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
                    min_modifyPart.setText("");
                    errorFree = false;
                } else if (stock > max || stock < min) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setContentText("The Inv value cannot be greater than the max value or less than the min value.");
                    alert1.showAndWait();
                    inv_modifyPart.setText("");
                    errorFree = false;
                }

                if (inHouseSelect_modifyPart.isSelected() && errorFree) {

                    InHouse updatedPart = new InHouse(id, partName, price, stock, min, max, machineID);
                    Inventory.updatePart(index, updatedPart);
                }


                if (outSourcedSelect_modifyPart.isSelected() && errorFree) {

                    Outsourced updatedPart = new Outsourced(id, partName, price, stock, min, max, company);
                    Inventory.updatePart(index, updatedPart);
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

    /**
     * when the form is first started it will load the selected part information in the appropriate fields.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Part selectedPart = MainController.getSelectedPart();

        if (selectedPart instanceof InHouse){
            group1.selectToggle(inHouseSelect_modifyPart);
        }else {
            group1.selectToggle(outSourcedSelect_modifyPart);
            flexLabel_modifyPart.setText("Company Name");
        }


        ID_modifyPart.setText(String.valueOf(selectedPart.getId()));
        name_modifyPart.setText(selectedPart.getName());
        inv_modifyPart.setText(String.valueOf(selectedPart.getStock()));
        priceCost_modifyPart.setText(String.valueOf(selectedPart.getPrice()));
        min_modifyPart.setText(String.valueOf(selectedPart.getMin()));
        max_modifyPart.setText(String.valueOf(selectedPart.getMax()));

        if (inHouseSelect_modifyPart.isSelected()){
            flexField_modifyPart.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }else {
            flexField_modifyPart.setText(((Outsourced) selectedPart).getCompanyName());
        }


    }
}
