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
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class MainForm implements Initializable {

    Stage stage;

    @FXML
    private TableView<Part> part;

    @FXML
    private TableColumn<Part, Integer> PartId;

    @FXML
    private TableColumn<Part, String> PartName;

    @FXML
    private TableColumn<Part, Integer> PartInv;

    @FXML
    private TableColumn<Part, Double> PartPrice;

    @FXML
    private TextField PartSearch;

    @FXML
    private TableView<Product> product;

    @FXML
    private TableColumn<Product, Integer> ProductID;

    @FXML
    private TableColumn<Product, String> ProductName;

    @FXML
    private TableColumn<Product, Integer> ProductInv;

    @FXML
    private TableColumn<Product, Double> ProductPrice;

    @FXML
    private TextField productSearch;

    /**
     *
     * @param event
     */
    @FXML
    void ExitClicked(ActionEvent event) {
        System.exit(0);
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void PartAddClicked(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/software_i/AddPartsForm.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    /**
     *LOGICAL ERROR
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void PartDeleteClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/software_i/MainForm.fxml"));
        Parent scene = fxmlLoader.load();

        Part selected = part.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No part selected!");
            alert.setContentText("Select a part to delete");
            alert.show();
        } else {
            Inventory.deletePart(selected);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informative Dialog");
            alert.setHeaderText("The part is deleted!");
            alert.show();
        }

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     *RUNTIME ERROR- NullPointerException
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void PartModifyClicked(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/software_i/ModifyPartForm.fxml"));
        Parent scene = fxmlLoader.load();
        ModifyPartForm mpartForm = fxmlLoader.getController();
        try {
            mpartForm.setPart(part.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("No part selected");
            alert.setContentText("Please select a part to modify !");
            alert.show();

        }

    }

    /**
     *RUNTIME ERROR- NullPointerException
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void ProductDeleteClicked(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/software_i/MainForm.fxml"));
        Parent scene = fxmlLoader.load();

        try {
            Product selected = product.getSelectionModel().getSelectedItem();
            if (selected.getAllAssociatedParts().isEmpty()) {
                Inventory.deleteProduct(selected);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informative Dialog");
                alert.setHeaderText("The product is deleted!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Can Not Deleted Product!");
                alert.setContentText("The product has an associated part with it!");
                alert.show();
            }

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(scene));
            stage.show();


        } catch (NullPointerException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No product selected!");
            alert.setContentText("Select a product to delete");
            alert.show();

        }


    }

    /**
     *RUNTIME ERROR- NullPointerException
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void ProductModifyClicked(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/software_i/ModifyProductForm.fxml"));
        Parent scene = fxmlLoader.load();
        try {
            ModifyProductForm mProductForm = fxmlLoader.getController();
            mProductForm.setProduct(product.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a product to modify !");
            alert.show();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/software_i/MainForm.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        }

    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void ProuductAddClicked(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/software_i/AddProductForm.fxml"));
        Parent scene = fxmlLoader.load();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     *RUNTIME ERROR- NumberFormatException
     *
     * @param event
     */
    @FXML
    void searchTyped(KeyEvent event) {
        try {
            ObservableList<Part> temp = FXCollections.observableArrayList();
            int id = Integer.parseInt(PartSearch.getText());
            Part prt = Inventory.lookupPart(id);
            temp.add(prt);

            part.setItems(temp);
            PartId.setCellValueFactory(new PropertyValueFactory("id"));
            PartName.setCellValueFactory(new PropertyValueFactory("name"));
            PartInv.setCellValueFactory(new PropertyValueFactory("stock"));
            PartPrice.setCellValueFactory(new PropertyValueFactory("price"));

        } catch (NumberFormatException e) {
            String name = PartSearch.getText();

            part.setItems(Inventory.lookupPart(name));
            PartId.setCellValueFactory(new PropertyValueFactory("id"));
            PartName.setCellValueFactory(new PropertyValueFactory("name"));
            PartInv.setCellValueFactory(new PropertyValueFactory("stock"));
            PartPrice.setCellValueFactory(new PropertyValueFactory("price"));
        }

    }

    /**
     *RUNTIME ERROR- NumberFormatException
     *
     * @param event
     */
    @FXML
    void productTyped(KeyEvent event) {
        try {
            ObservableList<Product> temp = FXCollections.observableArrayList();
            int id = Integer.parseInt(productSearch.getText());
            Product prd = Inventory.lookupProduct(id);
            temp.add(prd);

            product.setItems(temp);
            ProductID.setCellValueFactory(new PropertyValueFactory("id"));
            ProductName.setCellValueFactory(new PropertyValueFactory("name"));
            ProductInv.setCellValueFactory(new PropertyValueFactory("stock"));
            ProductPrice.setCellValueFactory(new PropertyValueFactory("price"));

        } catch (NumberFormatException e) {
            String name = productSearch.getText();

            product.setItems(Inventory.lookupProduct(name));
            ProductID.setCellValueFactory(new PropertyValueFactory("id"));
            ProductName.setCellValueFactory(new PropertyValueFactory("name"));
            ProductInv.setCellValueFactory(new PropertyValueFactory("stock"));
            ProductPrice.setCellValueFactory(new PropertyValueFactory("price"));
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
        PartId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory("name"));
        PartInv.setCellValueFactory(new PropertyValueFactory("stock"));
        PartPrice.setCellValueFactory(new PropertyValueFactory("price"));

        product.setItems(Inventory.getAllProduct());
        ProductID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        ProductName.setCellValueFactory(new PropertyValueFactory("name"));
        ProductInv.setCellValueFactory(new PropertyValueFactory("stock"));
        ProductPrice.setCellValueFactory(new PropertyValueFactory("price"));
    }
}
