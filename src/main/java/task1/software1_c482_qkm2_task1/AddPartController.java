package task1.software1_c482_qkm2_task1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddPartController {
    public ToggleGroup group1;

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
}
