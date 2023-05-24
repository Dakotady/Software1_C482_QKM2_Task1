package task1.software1_c482_qkm2_task1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    public TextField FilterAddProduct;
    public TextField ID_addProduct;
    public TextField name_addProduct;
    public TextField inv_addProduct;
    public TextField price_addProduct;
    public TextField max_addProduct;
    public TextField min_addProduct;
    public TableView partsList_addProduct;
    public TableColumn col_PartID_allParts;
    public TableColumn col_PartName_allParts;
    public TableColumn col_InvLevel_allParts;
    public TableColumn col_PriceCostPerUnit_allParts;
    public TableView association_addProduct;
    public TableColumn col_PartID_associated;
    public TableColumn col_PartName_associated;
    public TableColumn col_InvLevel_associated;
    public TableColumn col_PriceCostPerUnit_associated;
    public Button addAssociation_addProduct;
    public Button removeAssociation_addProduct;
    public Button cancel_addProduct;
    public Button save_addProduct;

    public void onCancelClick(ActionEvent actionEvent) throws IOException {

        boolean response;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Do you wish to exit without saving?");
        alert.setContentText("Choose Ok to continue or Cancel to continue filling out the Product info.");
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


    public void onFilterAddProduct(ActionEvent actionEvent) {
        String filter = FilterAddProduct.getText();
        ObservableList<Part> filteredList = FXCollections.observableArrayList();


        try {
            Part IdSearch = Inventory.lookupPart(Integer.parseInt(filter));
            filteredList.add(IdSearch);
        } catch (Exception parseString) {
            filteredList = Inventory.lookupPart(filter);
        }
        partsList_addProduct.setItems(filteredList);

        if (filter.isEmpty() || filter.isBlank()) {
            partsList_addProduct.setItems(Inventory.getAllParts());
        }
    }

    public void onAddAssociationClick(ActionEvent actionEvent) {

        Part newAssociation = (Part) partsList_addProduct.getSelectionModel().getSelectedItem();

    }

    public void onRemoveAssociationClick(ActionEvent actionEvent) {
    }

    public void onSaveClick(ActionEvent actionEvent) throws IOException {

        boolean textError = false;
        boolean errorFree = true;


        if (name_addProduct.getText().isEmpty() || name_addProduct.getText().isEmpty() || name_addProduct.getText() == null) {
            textError = true;
        } else if (inv_addProduct.getText().isEmpty() || inv_addProduct.getText().isEmpty() || inv_addProduct.getText() == null) {
            textError = true;
        } else if (price_addProduct.getText().isEmpty() || price_addProduct.getText().isEmpty() || price_addProduct.getText() == null) {
            textError = true;
        } else if (max_addProduct.getText().isEmpty() || max_addProduct.getText().isEmpty() || max_addProduct.getText() == null) {
            textError = true;
        } else if (min_addProduct.getText().isEmpty() || min_addProduct.getText().isEmpty() || min_addProduct.getText() == null) {
            textError = true;
        }


        if (textError) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please populate a value in each field to continue.");
            alert.showAndWait();
        } else {

            Main.UniqueIDs uniqueIDs = new Main.UniqueIDs();


            int id = uniqueIDs.outputUid();
            String productName = name_addProduct.getText();
            double price = Double.parseDouble(price_addProduct.getText());
            int stock = Integer.parseInt(inv_addProduct.getText());
            int min = Integer.parseInt(min_addProduct.getText());
            int max = Integer.parseInt(max_addProduct.getText());



            if (min > max) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("The minimum value cannot be greater than the max value.");
                alert1.showAndWait();
                min_addProduct.setText("");
                errorFree = false;
            } else if (stock > max || stock < min) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("The Inv value cannot be greater than the max value.");
                alert1.showAndWait();
                inv_addProduct.setText("");
                errorFree = false;
            }


            if (errorFree){
                Product product = new Product(id, productName, price, stock, min, max);
                Inventory.addProduct(product);
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
        public void initialize (URL url, ResourceBundle resourceBundle){

            col_PartID_allParts.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
            col_PartName_allParts.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
            col_InvLevel_allParts.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
            col_PriceCostPerUnit_allParts.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
            partsList_addProduct.setItems(Inventory.getAllParts());
        }
}
