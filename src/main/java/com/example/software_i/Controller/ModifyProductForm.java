package com.example.software_i.Controller;

import com.example.software_i.Model.Inventory;
import com.example.software_i.Model.Part;
import com.example.software_i.Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;


public class ModifyProductForm implements Initializable {

    Product product;
    @FXML
    private TableView<Part> part;

    @FXML
    private TableColumn<Part, Integer> PartID;

    @FXML
    private TableColumn<Part, String> PartName;

    @FXML
    private TableColumn<Part, Integer> PartInv;

    @FXML
    private TableColumn<Part, Integer> PartPrice;

    @FXML
    private TableView<Part> Asspart;

    @FXML
    private TableColumn<Part, Integer> AssoPartID;

    @FXML
    private TableColumn<Part, String> AssoPartName;

    @FXML
    private TableColumn<Part, Integer> AssoPartInv;

    @FXML
    private TableColumn<Part, Double> AssoPartPrice;

    @FXML
    private TextField ProductID;

    @FXML
    private TextField ProductName;

    @FXML
    private TextField ProductInv;

    @FXML
    private TextField ProductPrice;

    @FXML
    private TextField ProductMax;

    @FXML
    private TextField ProductMin;

    @FXML
    private TextField PartSearch;

    /**
     *
     * @param event
     */
    @FXML
    void AddProductClicked(ActionEvent event) {
        Part selected=part.getSelectionModel().getSelectedItem();
        product.addAssociatedPart(selected);

        Asspart.setItems(product.getAllAssociatedParts());
        AssoPartID.setCellValueFactory(new PropertyValueFactory<Part,Integer>("id"));
        AssoPartName.setCellValueFactory(new PropertyValueFactory("name"));
        AssoPartInv.setCellValueFactory(new PropertyValueFactory("stock"));
        AssoPartPrice.setCellValueFactory(new PropertyValueFactory("price"));

    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void CancelClicked(ActionEvent event) throws IOException {
        returnTOMain(event);
    }

    /**
     *
     * @param event
     */
    @FXML
    void RemoveAssoClicked(ActionEvent event) {
        Part selected=Asspart.getSelectionModel().getSelectedItem();
        product.deleteAssociatedPart(selected);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informative Dialog");
        alert.setHeaderText("The associated part is deleted!");
        alert.show();
    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void SaveClicked(ActionEvent event) throws IOException {

        int prodID = Integer.parseInt(ProductID.getText());
        int inv = Integer.parseInt(ProductInv.getText());
        int min = Integer.parseInt(ProductMin.getText());
        int max = Integer.parseInt(ProductMax.getText());
        double price = Double.parseDouble(ProductPrice.getText());
        String name = ProductName.getText();
        if (min > max || max < inv || inv < min) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Product Not Modified!");
            alert.setContentText("The Min value should be less than Max value, and the Inv value should be" +
                    " between Min and Max value!");
            alert.show();
        }else {
            product.setMax(max);
            product.setMin(min);
            product.setName(name);
            product.setStock(inv);
            product.setPrice(price);
            Inventory.updateProduct(prodID,product);
        }

        returnTOMain(event);

    }

    /**
     *
     * @param selectedItem
     */
    public void setProduct(Product selectedItem) {
        product=selectedItem;
        ProductID.setText(String.valueOf(selectedItem.getId()));
        ProductName.setText(String.valueOf(selectedItem.getName()));
        ProductInv.setText(String.valueOf(selectedItem.getStock()));
        ProductPrice.setText(String.valueOf(selectedItem.getPrice()));
        ProductMax.setText(String.valueOf(selectedItem.getMax()));
        ProductMin.setText(String.valueOf(selectedItem.getMin()));

        Asspart.setItems(selectedItem.getAllAssociatedParts());
        AssoPartID.setCellValueFactory(new PropertyValueFactory<Part,Integer>("id"));
        AssoPartName.setCellValueFactory(new PropertyValueFactory("name"));
        AssoPartInv.setCellValueFactory(new PropertyValueFactory("stock"));
        AssoPartPrice.setCellValueFactory(new PropertyValueFactory("price"));
    }

    /**
     * @param event
     * @throws IOException
     */
    private void returnTOMain(ActionEvent event) throws IOException {
        Stage stage= (Stage) ((Button)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/software_i/MainForm.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        part.setItems(Inventory.getAllPart());
        PartID.setCellValueFactory(new PropertyValueFactory<Part,Integer>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory("name"));
        PartInv.setCellValueFactory(new PropertyValueFactory("stock"));
        PartPrice.setCellValueFactory(new PropertyValueFactory("price"));

    }
}
