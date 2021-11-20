package com.example.software_i.Controller;

import com.example.software_i.Model.Inventory;
import com.example.software_i.Model.Part;
import com.example.software_i.Model.Product;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;


public class AddProductForm implements Initializable {

    Product product = new Product();
    static int partID = 0;
    @FXML
    private TableView<Part> part;

    @FXML
    private TableColumn<Part, Integer> PartID;

    @FXML
    private TableColumn<Part, String> PartName;

    @FXML
    private TableColumn<Part, Integer> Inv;

    @FXML
    private TableColumn<Part, Integer> Price;

    @FXML
    private TableView<Part> Asspart;

    @FXML
    private TableColumn<Part, Integer> AssoPartID;

    @FXML
    private TableColumn<Part, String> AssoPartName;

    @FXML
    private TableColumn<Part, Integer> AssoInv;

    @FXML
    private TableColumn<Part, Double> AssoPrice;

    @FXML
    private TextField addProductID;

    @FXML
    private TextField addProductName;

    @FXML
    private TextField addProductInv;

    @FXML
    private TextField addProductPrice;

    @FXML
    private TextField addProductMax;

    @FXML
    private TextField addProductMin;

    @FXML
    private TextField addProductSearch;

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void CancelClicked(ActionEvent event) throws IOException {
        if(!product.getAllAssociatedParts().isEmpty()){
            product.getAllAssociatedParts().clear();
        }
        returnTOMain(event);
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void RemoveAssoClicked(ActionEvent event) throws IOException {


        Part selected = Asspart.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No associated part selected!");
            alert.setContentText("Select an associated part to delete");
            alert.show();
        } else {
            product.deleteAssociatedPart(selected);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informative Dialog");
            alert.setHeaderText("The associated part is deleted!");
            alert.show();
        }


    }

    /**
     *RUNTIME ERROR- NumberFormatException
     * @param event
     * @throws IOException
     */
    @FXML
    void SaveClicked(ActionEvent event) throws IOException {

        try {
            int inv = Integer.parseInt(addProductInv.getText());
            int min = Integer.parseInt(addProductMin.getText());
            int max = Integer.parseInt(addProductMax.getText());
            double price = Double.parseDouble(addProductPrice.getText());
            String name = addProductName.getText();

            if (min > max || max < inv || inv < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Product Not Added!");
                alert.setContentText("The Min value should be less than Max value, and the Inv value should be" +
                        " between Min and Max value!");
                alert.show();
            } else {
                partID ++;
                product.setId(partID);
                product.setMax(max);
                product.setMin(min);
                product.setName(name);
                product.setStock(inv);
                product.setPrice(price);
                Inventory.addProduct(product);
            }

            returnTOMain(event);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("Invalid user data");
            alert.setContentText("Please enter appropriate data !");
            alert.show();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/software_i/AddProductForm.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        }

    }

    /**
     *RUNTIME ERROR - NullPointerException
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void addClicked(ActionEvent event) throws IOException {

        Part selected = part.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("No part selected");
            alert.setContentText("Please select a part to add !");
            alert.show();
        } else {
            product.addAssociatedPart(selected);
            Asspart.setItems(product.getAllAssociatedParts());
            AssoPartID.setCellValueFactory(new PropertyValueFactory("id"));
            AssoPartName.setCellValueFactory(new PropertyValueFactory("name"));
            AssoInv.setCellValueFactory(new PropertyValueFactory("stock"));
            AssoPrice.setCellValueFactory(new PropertyValueFactory("price"));
        }

    }

    /**
     *RUNTIME ERROR- NumberFormatException
     *
     * @param event
     */
    @FXML
    void partSearch(KeyEvent event) {
        try {
            ObservableList<Part> temp = FXCollections.observableArrayList();
            int id = Integer.parseInt(addProductSearch.getText());
            Part prt = Inventory.lookupPart(id);
            temp.add(prt);

            part.setItems(temp);
            PartID.setCellValueFactory(new PropertyValueFactory("id"));
            PartName.setCellValueFactory(new PropertyValueFactory("name"));
            Inv.setCellValueFactory(new PropertyValueFactory("stock"));
            Price.setCellValueFactory(new PropertyValueFactory("price"));

        } catch (NumberFormatException e) {
            String name = addProductSearch.getText();

            part.setItems(Inventory.lookupPart(name));
            PartID.setCellValueFactory(new PropertyValueFactory("id"));
            PartName.setCellValueFactory(new PropertyValueFactory("name"));
            Inv.setCellValueFactory(new PropertyValueFactory("stock"));
            Price.setCellValueFactory(new PropertyValueFactory("price"));
        }


    }

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        part.setItems(Inventory.getAllPart());
        PartID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory("name"));
        Inv.setCellValueFactory(new PropertyValueFactory("stock"));
        Price.setCellValueFactory(new PropertyValueFactory("price"));

    }

    /**
     *
     * @param event
     * @throws IOException
     */
    private void returnTOMain(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/software_i/MainForm.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }


}
