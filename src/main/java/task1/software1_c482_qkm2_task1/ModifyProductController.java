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

public class ModifyProductController {

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

    public void onCancelClick(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene cancel = new Scene(root, 1000, 400);
        stage.setTitle("");
        stage.setScene(cancel);
        stage.show();

    }

    public void onFilterModifyProduct(ActionEvent actionEvent) {
    }

    public void onAddAssociationClick(ActionEvent actionEvent) {
    }

    public void onRemoveAssociationClick(ActionEvent actionEvent) {
    }

    public void onSaveClick(ActionEvent actionEvent) {
    }
}
