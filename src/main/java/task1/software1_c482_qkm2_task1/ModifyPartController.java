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

    public void onSaveClick(ActionEvent actionEvent) throws IOException {

        Part selectedPart = MainController.getSelectedPart();
        int index = Inventory.getAllParts().indexOf(selectedPart);


        boolean textError = false;
        boolean errorFree = true;

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
            String partName = name_modifyPart.getText();
            double price = Double.parseDouble(priceCost_modifyPart.getText());
            int stock = Integer.parseInt(inv_modifyPart.getText());
            int min = Integer.parseInt(min_modifyPart.getText());
            int max = Integer.parseInt(max_modifyPart.getText());

            if (min > max) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("The minimum value cannot be greater than the max value.");
                alert1.showAndWait();
                min_modifyPart.setText("");
                errorFree = false;
            } else if (stock > max || stock < min) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("The Inv value cannot be greater than the max value.");
                alert1.showAndWait();
                inv_modifyPart.setText("");
                errorFree = false;
            }

            if (inHouseSelect_modifyPart.isSelected() && errorFree){

                int machineID = Integer.parseInt(flexField_modifyPart.getText());
                InHouse updatedPart = new InHouse(id, partName, price, stock, min, max, machineID);
                Inventory.updatePart(index, updatedPart);
            }


            if (outSourcedSelect_modifyPart.isSelected() && errorFree){

                String company = flexField_modifyPart.getText();
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
