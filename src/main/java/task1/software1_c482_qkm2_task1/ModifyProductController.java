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

/**
 * When the user opens the modifyProduct form it will start by loading all parts to the partsList_addProduct table.<br>
 * It will also load all the information from the selected product.<br>
 * this class will also contain all the methods needed to modify a product, and associate/remove parts with the product.
 */
public class ModifyProductController implements Initializable {

    public TextField filterModifyProduct;
    public TextField ID_modifyProduct;
    public TextField name_modifyProduct;
    public TextField inv_modifyProduct;
    public TextField price_modifyProduct;
    public TextField max_modifyProduct;
    public TextField min_modifyProduct;
    public TableView partsList_modifyProduct;
    public TableColumn col_partID_modifyProduct;
    public TableColumn col_partName_modifyProduct;
    public TableColumn col_invLevel_modifyProduct;
    public TableColumn col_PriceCostPerUnit_ModifyProduct;
    public TableView association_modifyProduct;
    public TableColumn col_partID_association;
    public TableColumn col_partName_association;
    public TableColumn col_invLevel_association;
    public TableColumn col_priceCostPerUnit_association;
    public Button addAssociation_modifyProduct;
    public Button removeAssociation_modifyProduct;
    public Button save_modifyProduct;

    // this will store parts to be associated with the new product.
    private ObservableList<Part> association = FXCollections.observableArrayList();

    /**
     * When the Cancel Button is selected it will ask the user if they would like to cancel if they choose "Ok" it will take them to the Main menu.<br>
     * If they select "cancel" it will take them back to the current screen.
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * This will allow users to highlight and go to a single ID. the user can also do either a full name or partial name lookup.<br>
     * when the full name or partial name lookup is complete it will only have items that contain what the user input.<br>
     * when the FilterAddProduct text field is empty it will repopulate all currently loaded parts.
     * @param actionEvent
     */
    public void onFilterModifyProduct(ActionEvent actionEvent) {

        String filter = filterModifyProduct.getText();
        ObservableList<Part> filteredList = FXCollections.observableArrayList();
        boolean isnull = false;
        boolean idFound = false;
        partsList_modifyProduct.getSelectionModel().clearSelection();
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
            filterModifyProduct.setText("");
        }else if (idFound){
            partsList_modifyProduct.setItems(Inventory.getAllParts());
            for (int i = 0; i < passover.size(); i++){
                if (String.valueOf(passover.get(i).getId()).contains(filter)){
                    partsList_modifyProduct.getSelectionModel().select(i);
                    partsList_modifyProduct.scrollTo(i);
                }
            }

        }else {
            partsList_modifyProduct.setItems(filteredList);

        }

        if (filter.isEmpty() || filter.isBlank()){
            partsList_modifyProduct.setItems(Inventory.getAllParts());
        }
    }

    /**
     * when the Add button is selected and a part has been selected it will add it to the association_addProduct table.<br>
     * after the product has been saved the parts will then be added as an association for the product.
     * @param actionEvent
     */
    public void onAddAssociationClick(ActionEvent actionEvent) {

        Part newAssociation = (Part) partsList_modifyProduct.getSelectionModel().getSelectedItem();

        if (newAssociation == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No part has been selected. Please select a part to add.");
            alert.showAndWait();
        } else {
            association.addAll(newAssociation);

            col_partID_association.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
            col_partName_association.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
            col_invLevel_association.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
            col_priceCostPerUnit_association.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
            association_modifyProduct.setItems(association);
        }

    }

    /**
     * This will allow users to Remove part Associations pending the product being saved.
     * @param actionEvent
     */
    public void onRemoveAssociationClick(ActionEvent actionEvent) {

        Part newAssociation = (Part) association_modifyProduct.getSelectionModel().getSelectedItem();

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

                association_modifyProduct.setItems(association);
            }
        }
    }

    /**
     * When the save button is clicked it will check to see if the product can be submitted, and after the logical test passes it will become a modified product.<br>
     * this will also finalize the part associations tied to the product.
     * @param actionEvent
     * @throws IOException
     */
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

        Product selectedPart = MainController.getSelectedProduct();
        int index = Inventory.getAllProduct().indexOf(selectedPart);


        if (name_modifyProduct.getText().isEmpty() || name_modifyProduct.getText().isEmpty() || name_modifyProduct.getText() == null) {
            textError = true;
        } else if (inv_modifyProduct.getText().isEmpty() || inv_modifyProduct.getText().isEmpty() || inv_modifyProduct.getText() == null) {
            textError = true;
        } else if (price_modifyProduct.getText().isEmpty() || price_modifyProduct.getText().isEmpty() || price_modifyProduct.getText() == null) {
            textError = true;
        } else if (max_modifyProduct.getText().isEmpty() || max_modifyProduct.getText().isEmpty() || max_modifyProduct.getText() == null) {
            textError = true;
        } else if (min_modifyProduct.getText().isEmpty() || min_modifyProduct.getText().isEmpty() || min_modifyProduct.getText() == null) {
            textError = true;
        }


        if (textError) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please populate a value in each field to continue.");
            alert.showAndWait();
        } else {

            int id = Integer.parseInt(ID_modifyProduct.getText());

            try {
                try {
                    if (Integer.parseInt(name_modifyProduct.getText()) >= 0){
                        ParseError = true;
                        parseErrorText = "Name is not equal to a String. ";
                    }
                }catch (Exception convertToString){
                    productName = name_modifyProduct.getText();
                }

                try {
                    if (Double.parseDouble(name_modifyProduct.getText()) >= 0.00){
                        ParseError = true;
                        parseErrorText = "Name is not equal to a String. ";
                    }
                }catch (Exception convertToString){
                    productName = name_modifyProduct.getText();
                }

            }catch (Exception cannotConvertString){

            }

            try {
                price = Double.parseDouble(price_modifyProduct.getText());
            }catch (Exception cannotConvertDouble){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert price to double. ";
            }

            try {
                stock = Integer.parseInt(inv_modifyProduct.getText());
            }catch (Exception cannotConvertNum){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert Inv to a Integer. ";
            }

            try {
                min = Integer.parseInt(min_modifyProduct.getText());
            }catch (Exception cannotConvertNum){
                ParseError = true;
                parseErrorText = parseErrorText + "Cannot convert min to a Integer. ";
            }

            try {
                max = Integer.parseInt(max_modifyProduct.getText());
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
                    min_modifyProduct.setText("");
                    errorFree = false;
                } else if (stock > max || stock < min) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setContentText("The Inv value cannot be greater than the max value or less than the min value.");
                    alert1.showAndWait();
                    inv_modifyProduct.setText("");
                    errorFree = false;
                }


                if (errorFree) {
                    Product updatedProduct = new Product(id, productName, price, stock, min, max);
                    Inventory.updateProduct(index, updatedProduct);

                    for (Part part : association) {
                            updatedProduct.addAssociatedPart(part);
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

    /**
     * when the form is opened it will populate the partsList_addProduct table with all the parts loaded in the Inventory.<br>
     * This method will also load all the selected part information and the parts that have been previously associated.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Product selectedProduct = MainController.getSelectedProduct();

        ID_modifyProduct.setText(String.valueOf(selectedProduct.getId()));
        name_modifyProduct.setText(selectedProduct.getName());
        inv_modifyProduct.setText(String.valueOf(selectedProduct.getStock()));
        price_modifyProduct.setText(String.valueOf(selectedProduct.getPrice()));
        min_modifyProduct.setText(String.valueOf(selectedProduct.getMin()));
        max_modifyProduct.setText(String.valueOf(selectedProduct.getMax()));

        association = selectedProduct.getAllAssociatedParts();
        col_partID_association.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        col_partName_association.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        col_invLevel_association.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        col_priceCostPerUnit_association.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
        association_modifyProduct.setItems(association);



        col_partID_modifyProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
        col_partName_modifyProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        col_invLevel_modifyProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        col_PriceCostPerUnit_ModifyProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        partsList_modifyProduct.setItems(Inventory.getAllParts());
    }
}
