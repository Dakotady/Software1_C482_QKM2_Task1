package task1.software1_c482_qkm2_task1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

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

    private static Part selectedPart;
    public static void setSelectedPart(Part selected){
        selectedPart = selected;
    }
    public static Part getSelectedPart() {
        return selectedPart;
    }


    private static Product selectedProduct;
    public static void setSelectedProduct(Product selected){
        selectedProduct = selected;
    }
    public static Product getSelectedProduct(){
        return selectedProduct;
    }


    public void onFilterPart(ActionEvent actionEvent) {
    }

    public void onFilterProduct(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_partID_product.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
        col_partName_product.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        col_inventoryLevel_product.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        col_priceCostPerUnit_product.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        productsList.setItems(Inventory.getAllProduct());

        //adds temp parts to model delete before submission.
        Product four = new Product(1, "30", 3.50, 1, 3, 6);
        Product five = new Product(2, "10", 1.25, 2, 4, 6);

        Inventory.getAllProduct().addAll(four, five);
        // cont delete.

        col_partID_part.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        col_partName_part.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        col_inventoryLevel_part.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        col_priceCostPerUnit_part.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
        partsList.setItems(Inventory.getAllParts());

        //populating test items
        InHouse one = new InHouse(1, "Test", 3, 4, 5, 1, 55);
        Outsourced two = new Outsourced(2, "NotReal", 4, 5, 6, 7, "TestComp");
        InHouse three = new InHouse(3, "Really", 5, 6, 7, 8, 65);

        Inventory.getAllParts().addAll(one, two, three);


    }
}
