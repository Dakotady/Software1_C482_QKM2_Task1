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

import java.io.IOException;
import java.util.Objects;

public class ModifyPartController {
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
