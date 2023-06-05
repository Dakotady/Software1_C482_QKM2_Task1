package task1.software1_c482_qkm2_task1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    private ObservableList <Part> association = FXCollections.observableArrayList();

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
        boolean isnull = false;
        boolean idFound = false;
        partsList_addProduct.getSelectionModel().clearSelection();
        ObservableList<Part> passover = FXCollections.observableArrayList();
        passover = Inventory.getAllParts();


        try {
            Part IdSearch = Inventory.lookupPart(Integer.parseInt(filter));
            filteredList.add(IdSearch);
            if (IdSearch == null){
                isnull = true;
            }else {
                idFound = true;
            }
        } catch (Exception parseString){
            filteredList = Inventory.lookupPart(filter);
        }

        if (filteredList == null || filteredList.isEmpty() || isnull ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No parts have been found. please refine search criteria.");
            alert.showAndWait();
            FilterAddProduct.setText("");
        }else if (idFound){
            partsList_addProduct.setItems(Inventory.getAllParts());
            for (int i = 0; i < passover.size(); i++){
                if (String.valueOf(passover.get(i).getId()).contains(filter)){
                    partsList_addProduct.getSelectionModel().select(i);
                    partsList_addProduct.scrollTo(i);
                }
            }

        }else {
            partsList_addProduct.setItems(filteredList);

        }

        if (filter.isEmpty() || filter.isBlank()){
            partsList_addProduct.setItems(Inventory.getAllParts());
        }
    }

    public void onAddAssociationClick(ActionEvent actionEvent) {

        Part newAssociation = (Part) partsList_addProduct.getSelectionModel().getSelectedItem();

        if (newAssociation == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No part has been selected. Please select a part to add.");
            alert.showAndWait();
        } else {
            association.addAll(newAssociation);

            col_PartID_associated.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
            col_PartName_associated.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
            col_InvLevel_associated.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
            col_PriceCostPerUnit_associated.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
            association_addProduct.setItems(association);
        }


    }

    public void onRemoveAssociationClick(ActionEvent actionEvent) {

        Part newAssociation = (Part) association_addProduct.getSelectionModel().getSelectedItem();

        if (newAssociation == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No part has been selected. Please select a part to remove.");
            alert.showAndWait();
        }else {

            boolean response;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Do you wish remove the association?");
            alert.setContentText("Choose Ok to continue or Cancel to keep the part.");
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
            response = result == ButtonType.OK;

            if (response) {
                association.remove(newAssociation);

                association_addProduct.setItems(association);
            }
        }

    }

    public void onSaveClick(ActionEvent actionEvent) throws IOException {

        boolean textError = false;
        boolean errorFree = true;
        boolean ParseError = false;
        String parseErrorText = "";

        String productName = null;
        double price = 0;
        int stock = 0;
        int min = 0;
        int max = 0;

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

            try {
                try {
                    if (Integer.parseInt(name_addProduct.getText()) >= 0){
                        ParseError = true;
                        parseErrorText = "Name is not equal to a String. ";
                    }
                }catch (Exception convertToString){
                    productName = name_addProduct.getText();
                }

                try {
                    if (Double.parseDouble(name_addProduct.getText()) >= 0.00){
                        ParseError = true;
                        parseErrorText = "Name is not equal to a String. ";
                    }
                }catch (Exception convertToString){
                    productName = name_addProduct.getText();
                }

            }catch (Exception cannotConvertString){

            }

            try {
                price = Double.parseDouble(price_addProduct.getText());
            }catch (Exception cannotConvertDouble){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert price to double. ";
            }

            try {
                stock = Integer.parseInt(inv_addProduct.getText());
            }catch (Exception cannotConvertNum){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert Inv to a Integer. ";
            }

            try {
                min = Integer.parseInt(min_addProduct.getText());
            }catch (Exception cannotConvertNum){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert min to a Integer. ";
            }

            try {
                max = Integer.parseInt(max_addProduct.getText());
            }catch (Exception cannotConvertNum){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert max to a Integer. ";
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
                    min_addProduct.setText("");
                    errorFree = false;
                } else if (stock > max || stock < min) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setContentText("The Inv value cannot be greater than the max value or less than the min value.");
                    alert1.showAndWait();
                    inv_addProduct.setText("");
                    errorFree = false;
                }


                if (errorFree) {
                    int id = Main.UniqueIDs.setID();

                    Product product = new Product(id, productName, price, stock, min, max);
                    Inventory.addProduct(product);

                    for (Part part : association) {
                        product.addAssociatedPart(part);
                    }

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

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){

            col_PartID_allParts.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
            col_PartName_allParts.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
            col_InvLevel_allParts.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
            col_PriceCostPerUnit_allParts.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
            partsList_addProduct.setItems(Inventory.getAllParts());


        }
}
