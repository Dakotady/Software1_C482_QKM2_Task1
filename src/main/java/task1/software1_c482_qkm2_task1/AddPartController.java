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

import java.net.URL;
import java.io.IOException;
import java.util.*;

import static java.util.UUID.randomUUID;

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

        Main.UniqueIDs uniqueIDs = new Main.UniqueIDs();

        boolean errorFree = true;
        int id = uniqueIDs.outputUid();
        String partName = name_addPart.getText();
        double price = Double.parseDouble(priceCost_addPart.getText());
        int stock = Integer.parseInt(inv_addPart.getText());
        int min = Integer.parseInt(min_addPart.getText());
        int max = Integer.parseInt(max_addPart.getText());

        if (min > max){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The minimum value cannot be greater than the max value.");
            alert.showAndWait();
            min_addPart.setText("");
            errorFree = false;
        }else if (stock > max) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The Inv value cannot be greater than the max value.");
            alert.showAndWait();
            inv_addPart.setText("");
            errorFree = false;
        }

        if (inHouseSelect_addPart.isSelected() && errorFree) {

            int machID = Integer.parseInt(flexField_addPart.getText());
            InHouse inHouse = new InHouse(id, partName, price, stock, min, max, machID);
            Inventory.getAllParts().addAll(inHouse);
        }

        if (outSourcedSelect_addPart.isSelected() && errorFree){
            String compName = flexField_addPart.getText();
            Outsourced outsourced = new Outsourced(id, partName, price, stock, min, max, compName);
            Inventory.getAllParts().addAll(outsourced);
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
