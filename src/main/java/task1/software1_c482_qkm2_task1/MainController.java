package task1.software1_c482_qkm2_task1;

import javafx.application.Platform;
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

/**
 * this class will control the main form and load parts and products when initialized.
 */
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
    public TableColumn col_productID;
    public TableColumn col_productName;
    public TableColumn col_inventoryLevel_product;
    public TableColumn col_priceCostPerUnit_product;
    public TextField filterProduct;
    public Button onAddProduct;
    public Button onModifyProduct;
    public Button onDeleteProduct;
    public Button exitApp;

    /**
     * this will exit the application when the exit button is clicked.
     */
    @FXML
    public void onExitAppButtonClick() {

        Platform.exit();
        }


    /**
     * this will open the AddPart form when selected.
     * @param actionEvent
     * @throws IOException
     */
    public void onAddPartClick(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPart.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene addPart = new Scene(root, 600, 400);
        stage.setTitle("");
        stage.setScene(addPart);
        stage.show();
    }

    public static Part selectedPart;

    /**
     * this will set the selected part from the table when called.
     * @param selected
     */
    public void setSelectedPart(Part selected){
        selectedPart = selected;
    }

    /**
     * when called it will get the selected part from the table.
     * @return
     */
    public static Part getSelectedPart(){
        return selectedPart;
    }

    /**
     * this will send the user to the ModifyPart form.
     * @param actionEvent
     * @throws IOException
     */
    public void onModifyPartClick(ActionEvent actionEvent) throws IOException {

        Part selected = (Part) partsList.getSelectionModel().getSelectedItem();
        setSelectedPart(selected);

        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No part has been selected. Please select a part to modify.");
            alert.showAndWait();
        }else {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyPart.fxml")));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene modifyPart = new Scene(root, 600, 400);
            stage.setTitle("");
            stage.setScene(modifyPart);
            stage.show();
        }
    }

    /**
     * this will send the user to the AddProduct form.
     * @param actionEvent
     * @throws IOException
     */
    public void onAddProductClick(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddProduct.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene AddProduct = new Scene(root, 1062, 667);
        stage.setTitle("");
        stage.setScene(AddProduct);
        stage.show();
    }

    public static Product selectedProduct;

    /**
     * this will set the selected product form the product table.
     * @param selected
     */
    public void setSelectedProduct(Product selected){
        selectedProduct = selected;
    }

    /**
     * this will get the selected product form the product table.
     * @return
     */
    public static Product getSelectedProduct(){
        return selectedProduct;
    }

    /**
     * this will send the user to the ModifyProduct form.
     * @param actionEvent
     * @throws IOException
     */
    public void onModifyProductClick(ActionEvent actionEvent) throws IOException {

        Product selected = (Product) productsList.getSelectionModel().getSelectedItem();
        setSelectedProduct(selected);

        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No part has been selected. Please select a part to modify.");
            alert.showAndWait();
        }else {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyProduct.fxml")));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene ModifyProduct = new Scene(root, 1062, 667);
            stage.setTitle("");
            stage.setScene(ModifyProduct);
            stage.show();
        }
    }

    /**
     * this use the users input to filter or highlight a row in the parts table.
     * @param actionEvent
     */
    public void onFilterPart(ActionEvent actionEvent) {

        String filter = filterPart.getText();
        ObservableList<Part> filteredList = FXCollections.observableArrayList();
        boolean isnull = false;
        boolean idFound = false;
        partsList.getSelectionModel().clearSelection();
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
            filterPart.setText("");
        }else if (idFound){
            partsList.setItems(Inventory.getAllParts());
            for (int i = 0; i < passover.size(); i++){
                if (String.valueOf(passover.get(i).getId()).contains(filter)){
                    partsList.getSelectionModel().select(i);
                    partsList.scrollTo(i);
                }
            }

        }else {
            partsList.setItems(filteredList);

        }

        if (filter.isEmpty() || filter.isBlank()){
            partsList.setItems(Inventory.getAllParts());
        }
    }

    /**
     * this use the users input to filter or highlight a row in the product table.
     * @param actionEvent
     */
    public void onFilterProduct(ActionEvent actionEvent) {

        String filter = filterProduct.getText();
        ObservableList<Product> filteredList = FXCollections.observableArrayList();
        boolean isnull = false;
        boolean idFound = false;
        productsList.getSelectionModel().clearSelection();
        ObservableList<Product> passover = FXCollections.observableArrayList();
        passover = Inventory.getAllProduct();

        try {
            Product IdSearch = Inventory.lookupProduct(Integer.parseInt(filter));
            filteredList.add(IdSearch);
            if (IdSearch == null) {
                isnull = true;
            } else{
                idFound = true;
            }
        } catch (Exception parseString){
            filteredList = Inventory.lookupProduct(filter);
        }


        if (filteredList == null || filteredList.isEmpty() || isnull ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No Products have been found. please refine search criteria.");
            alert.showAndWait();
            filterPart.setText("");
        }else if (idFound) {
            productsList.setItems(Inventory.getAllProduct());
            for (int i = 0; i < passover.size(); i++){
                if (String.valueOf(passover.get(i).getId()).contains(filter)){
                    productsList.getSelectionModel().select(i);
                    productsList.scrollTo(i);
                }
            }

        }else {
            productsList.setItems(filteredList);
        }

        if (filter.isEmpty() || filter.isBlank()){
            productsList.setItems(Inventory.getAllProduct());
        }
    }

    /**
     * this will allow the users to delete parts when the delete button is selected along with a part.
     * @param actionEvent
     */
    public void onDeletePartClick(ActionEvent actionEvent) {

        boolean response;

        Part selected = (Part) partsList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("No part has been selected. Please select a part to delete.");
            error.showAndWait();
            filterPart.setText("");
        }else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Are you sure you want to delete the selected part?");
            alert.setContentText("Choose Ok to delete or Cancel to keep the part.");
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
            response = result == ButtonType.OK;

            if (response) {
                Inventory.deletePart(selected);
                partsList.setItems(Inventory.getAllParts());
            }
        }
    }

    /**
     * this will allow the users to delete products when the delete button is selected along with a products.
     * @param actionEvent
     */
    public void onDeleteProductClick(ActionEvent actionEvent) {

        boolean response;

        Product selected = (Product) productsList.getSelectionModel().getSelectedItem();

        if (selected.getAllAssociatedParts().isEmpty()) {

            if (selected == null) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("No product has been selected. Please select a product to delete.");
                error.showAndWait();
                filterProduct.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.setHeaderText("Are you sure you want to delete the selected product?");
                alert.setContentText("Choose Ok to delete or Cancel to keep the product.");
                ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
                response = result == ButtonType.OK;

                if (response) {
                    Inventory.deleteProduct(selected);
                    productsList.setItems(Inventory.getAllProduct());
                }
            }
        }else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Current selected product has associated parts. To delete the parts will need to be disassociated with the product.");
            error.showAndWait();
            filterProduct.setText("");
        }
    }

    /**
     * when the application is started it will load the following parts and products to the Inventory Class.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Main.ProgramInitialized.getStatus() == false){

            int id;

            col_productID.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
            col_productName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
            col_inventoryLevel_product.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
            col_priceCostPerUnit_product.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
            productsList.setItems(Inventory.getAllProduct());

            id = Main.UniqueIDs.setID();

            //adds temp parts to model delete before submission.
            Product four = new Product(id, "naruto", 3.50, 3, 3, 6);

            id = Main.UniqueIDs.setID();
            Product five = new Product(id, "Sasuke", 1.25, 5, 4, 6);

            Inventory.getAllProduct().addAll(four, five);
            // cont delete.

            col_partID_part.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
            col_partName_part.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
            col_inventoryLevel_part.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
            col_priceCostPerUnit_part.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
            partsList.setItems(Inventory.getAllParts());

            //populating test items
            id = Main.UniqueIDs.setID();
            InHouse one = new InHouse(id, "Boruto", 3.00, 2, 1, 5, 55);

            id = Main.UniqueIDs.setID();
            Outsourced two = new Outsourced(id, "Himawari", 4.00, 5, 6, 7, "Child");

            id = Main.UniqueIDs.setID();
            InHouse three = new InHouse(id, "Sarada", 5.99, 7, 7, 9, 65);

            Inventory.getAllParts().addAll(one, two, three);

            Main.ProgramInitialized.opened = true;
        }else {
            col_productID.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
            col_productName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
            col_inventoryLevel_product.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
            col_priceCostPerUnit_product.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
            productsList.setItems(Inventory.getAllProduct());

            col_partID_part.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
            col_partName_part.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
            col_inventoryLevel_part.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
            col_priceCostPerUnit_part.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
            partsList.setItems(Inventory.getAllParts());
        }


    }


}
