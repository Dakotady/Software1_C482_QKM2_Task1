package task1.software1_c482_qkm2_task1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddPartController {
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

    @FXML

    public void  initialize(URL url, ResourceBundle resourceBundle){


    }

    public void OnRadioClick(ActionEvent actionEvent) {



    }


    public void onCancelClick(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene cancel = new Scene(root, 1000, 400);
        stage.setTitle("");
        stage.setScene(cancel);
        stage.show();

    }

    public void onSaveClick(ActionEvent actionEvent) {
    }
}
