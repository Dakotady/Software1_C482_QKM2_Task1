package task1.software1_c482_qkm2_task1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This is the current Inventory listed in the application.
 */
public class Inventory {

    //This is a list of all parts loaded.
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();

    //this is a list of all products loaded.
    private static final ObservableList<Product> allProduct = FXCollections.observableArrayList();

    /**
     * this method is called to add new parts to the allParts list.
     * @param newPart
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * this method is called to add new products to the allProduct list.
     * @param newProduct
     */
    public static void addProduct(Product newProduct){
        allProduct.add(newProduct);
    }

    /**
     * when this method is called it will validate if the partID of the part is found, and return the part that was found.
     * @param partID
     * @return
     */
    public static Part lookupPart(int partID){
        for (Part part : allParts)
            if (part.getId() == partID) {
                return part;
            }
        return null;
    }

    /**
     * when this method is called it will validate if the productID of the product is found, and return the product that was found.
     * @param productID
     * @return
     */
    public static  Product lookupProduct(int productID){
        for (Product product : allProduct)
            if (product.getId() == productID) {
                return product;
            }

        return null;
    }

    /**
     * this method will return a list of filtered parts to populate the tables that holds part info.
     * @param partName
     * @return
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        for (Part part : allParts)
            if (part.getName().contains(partName)) {
                filteredParts.add(part);
            }
        return filteredParts;
    }

    /**
     * this method will return a list of filtered products to populate the tables that holds product info.
     * @param productName
     * @return
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        for (Product product : allProduct)
            if (product.getName().contains(productName)){
                filteredProducts.add(product);
            }
        return filteredProducts;
    }

    /**
     * when this method is called it will update the selected part in the modifiedPart form that matches the allParts list.
     * @param index
     * @param selectedPart
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**
     * when this method is called it will update the selected product in the modifiedProduct form that matches the allProduct list.
     * @param index
     * @param newProduct
     */
    public static void updateProduct(int index, Product newProduct){
        allProduct.set(index, newProduct);
    }

    /**
     * this method will delete matching parts from the allParts list.
     * @param selectedPart
     * @return
     */
    public static boolean deletePart(Part selectedPart){
        for(Part part : allParts){
            allParts.remove(selectedPart);
            return true;
        }
        return false;
    }

    /**
     * this method will delete matching products from the allProduct list.
     * @param selectedProduct
     * @return
     */
    public static boolean deleteProduct(Product selectedProduct){
        for (Product product : allProduct){
            allProduct.remove(selectedProduct);
            return true;
        }
        return false;
    }

    /**
     * this method will return the allParts list.
     * @return
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * this method will return the allProduct list.
     * @return
     */
    public static ObservableList<Product> getAllProduct(){
        return allProduct;
    }




}
