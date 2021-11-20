package com.example.software_i.Model;

import com.example.software_i.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventory {

    private static ObservableList<Part> allPart = FXCollections.observableArrayList();
    private static ObservableList<Product> allProduct=FXCollections.observableArrayList();
    private static ArrayList<INOROUT> INorOUT= new ArrayList<>();

    /**
     *
     * @param inorout
     */
    public static void machinORcompany(INOROUT inorout){
        INorOUT.add(inorout);
    }

    /**
     *
     * @param partid
     * @return
     */
    public static INOROUT machineORcomp (int partid){
        for (int x=0; x<INorOUT.size(); x++){
            if(INorOUT.get(x).getPartID()==partid)
                return INorOUT.get(x);
        }
        return null;
    }

    /**
     *
     * @param selected
     */
    public static void updateINOROUT(INOROUT selected){
        for (int x=0; x<INorOUT.size(); x++){
            if (INorOUT.get(x).getPartID()==selected.getPartID())
                INorOUT.set(x,selected);
        }
    }

    /**
     *
     * @param newPart
     */
    public static void addPart(Part newPart){
        allPart.add(newPart);
    }

    /**
     *
     * @param newProduct
     */
    public static void addProduct(Product newProduct){
        allProduct.add(newProduct);
    }

    /**
     *
     * @param partId
     * @return
     */
    public static Part lookupPart(int partId){
        for (int x=0; x<allPart.size();x++){
            if(allPart.get(x).getId()==partId)
                return allPart.get(x);
        }
            return null;
    }

    /**
     *
     * @param productId
     * @return
     */
    public static Product lookupProduct(int productId){
        for (int x=0; x<allProduct.size();x++){
            if(allProduct.get(x).getId()==productId)
                return allProduct.get(x);
        }
        return null;
    }

    /**
     *
     * @param partName
     * @return
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> lookedParts = FXCollections.observableArrayList();
        for (int x=0; x<allPart.size();x++){
            if(allPart.get(x).getName().toLowerCase().contains(partName.toLowerCase()))
                lookedParts.add(allPart.get(x));
        }
        return lookedParts;
    }

    /**
     *
     * @param productName
     * @return
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<com.example.software_i.Model.Product> lookedProduct = FXCollections.observableArrayList();
        for (int x=0; x<allProduct.size();x++){
            if(allProduct.get(x).getName().toLowerCase().contains(productName.toLowerCase()))
                lookedProduct.add(allProduct.get(x));
        }
        return lookedProduct;
    }

    /**
     *
     * @param index
     * @param selectedPart
     */
    public static void updatePart(int index, Part selectedPart){
        for (int x=0; x<allPart.size(); x++){
            if (allPart.get(x).getId()==index)
                allPart.set(x,selectedPart);
        }

    }

    /**
     *
     * @param index
     * @param newProduct
     */
    public static void updateProduct(int index, Product newProduct){
        for (int x=0; x<allProduct.size(); x++){
            if (allProduct.get(x).getId()==index)
                allProduct.set(x,newProduct);
        }
    }

    /**
     *
     * @param selectedPart
     * @return
     */
    public static boolean deletePart (Part selectedPart){
            return allPart.remove(selectedPart);
    }

    /**
     *
     * @param selectedProduct
     * @return
     */
    public static boolean deleteProduct (Product selectedProduct){
            return allProduct.remove(selectedProduct);
    }

    /**
     *
     * @return
     */
    public static ObservableList<Part> getAllPart() {
        return allPart;
    }

    /**
     *
     * @return
     */
    public static ObservableList<Product> getAllProduct() {
        return allProduct;
    }
}
