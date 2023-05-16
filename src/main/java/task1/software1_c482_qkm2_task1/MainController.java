package task1.software1_c482_qkm2_task1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    public TableView partsList;
    public TableColumn col_partID_part;
    public TableColumn col_partName_part;
    public TableColumn col_inventoryLevel_part;
    public TableColumn col_priceCostPerUnit_part;
    public TextField filterPart;
    public Button onAddPart;
    public Button onModifyPart;
    public Button onDeletePart;
    public TableView productsList;
    public TableColumn col_partID_product;
    public TableColumn col_partName_product;
    public TableColumn col_inventoryLevel_product;
    public TableColumn col_priceCostPerUnit_product;
    public TextField filterProduct;
    public Button onAddProduct;
    public Button onModifyProduct;
    public Button onDeleteProduct;
    public Button exitApp;

    // this is for the exit button on the main form.
    @FXML
    public void onExitAppButtonClick() {

        Platform.exit();
        }

    // this is to send the user to the add part form.
    public void onAddPartClick(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPart.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene addPart = new Scene(root, 600, 400);
        stage.setTitle("");
        stage.setScene(addPart);
        stage.show();
    }

    // this is to send the user to the add part form.
    public void onModifyPartClick(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyPart.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene modifyPart = new Scene(root, 600, 400);
        stage.setTitle("");
        stage.setScene(modifyPart);
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
