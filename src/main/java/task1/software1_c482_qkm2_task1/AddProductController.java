package task1.software1_c482_qkm2_task1;

import javafx.event.ActionEvent;
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

public class AddProductController {

    public TextField FilterAddProduct;
    public TextField ID_addProduct;
    public TextField name_addProduct;
    public TextField inv_addProduct;
    public TextField price_addProduct;
    public TextField max_addProduct;
    public TextField min_addProduct;
    public TableView partsList_addProduct;
    public TableColumn partID_allParts;
    public TableColumn partName_allParts;
    public TableColumn invLevel_allParts;
    public TableColumn priceCostPerUnit_allParts;
    public TableView association_addProduct;
    public TableColumn partID_associated;
    public TableColumn partName_associated;
    public TableColumn invLevel_associated;
    public TableColumn priceCostPerUnit_associated;
    public Button addAssociation_addProduct;
    public Button removeAssociation_addProduct;
    public Button cancel_addProduct;
    public Button save_addProduct;

    public void onCancelClick(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene cancel = new Scene(root, 1000, 400);
        stage.setTitle("");
        stage.setScene(cancel);
        stage.show();

    }


    public void onFilterAddProduct(ActionEvent actionEvent) {
    }

    public void onAddAssociationClick(ActionEvent actionEvent) {
    }

    public void onRemoveAssociationClick(ActionEvent actionEvent) {
    }

    public void onSaveClick(ActionEvent actionEvent) {
    }
}
