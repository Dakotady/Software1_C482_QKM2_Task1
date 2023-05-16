package task1.software1_c482_qkm2_task1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    // this is for the exit button on the main form.
    @FXML
    public void onExitappButtonClick() {

        Platform.exit();
        }

    // this is to send the user to the add part form.
    public void onAddPartClick(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPart.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene Addpart = new Scene(root, 600, 400);
        stage.setTitle("");
        stage.setScene(Addpart);
        stage.show();
    }

    // this is to send the user to the add part form.
    public void onModifyPartClick(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Modifypart.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene Modifypart = new Scene(root, 600, 400);
        stage.setTitle("");
        stage.setScene(Modifypart);
        stage.show();
    }


    public void onAddProductClick(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddProduct.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene AddProduct = new Scene(root, 1062, 667);
        stage.setTitle("");
        stage.setScene(AddProduct);
        stage.show();
    }

    public void onModifyProductClick(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyProduct.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene ModifyProduct = new Scene(root, 1062, 667);
        stage.setTitle("");
        stage.setScene(ModifyProduct);
        stage.show();
    }


    public void onFilterPart(ActionEvent actionEvent) {
    }

    public void onFilterProduct(ActionEvent actionEvent) {
    }
}
