package task1.software1_c482_qkm2_task1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This is the current Inventory listed in the application.
 */
public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProduct = FXCollections.observableArrayList();

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProduct.add(newProduct);
    }

    public static Part lookupPart(int partID){
        for (Part part : allParts)
            if (part.getId() == partID) {
                return part;
            }
        return null;
    }

    public static  Product lookupProduct(int productID){
        for (Product product : allProduct)
            if (product.getId() == productID) {
                return product;
            }

        return null;
    }

    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        for (Part part : allParts)
            if (part.getName().contains(partName)) {
                filteredParts.add(part);
            }
        return filteredParts;
    }

    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        for (Product product : allProduct)
            if (product.getName().contains(productName)){
                filteredProducts.add(product);
            }
        return filteredProducts;
    }

    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct){
        allProduct.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart){
        for(Part part : allParts){
            allParts.remove(selectedPart);
            return true;
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct){
        for (Product product : allProduct){
            allProduct.remove(selectedProduct);
            return true;
        }
        return false;
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProduct(){
        return allProduct;
    }




}
