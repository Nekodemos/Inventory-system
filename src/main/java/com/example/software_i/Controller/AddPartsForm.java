package com.example.software_i.Controller;

import com.example.software_i.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;


public class AddPartsForm {

    static int partID=0;

    @FXML
    private TextField addPartMin;

    @FXML
    private TextField addPartID;

    @FXML
    private TextField addPartName;

    @FXML
    private TextField addPartInv;

    @FXML
    private TextField addPartPrice;

    @FXML
    private TextField addPartMax;

    @FXML
    private TextField AMachine_Company;

    @FXML
    private Label MachineOrCompany;

    @FXML
    private RadioButton addPartInHouse;

    @FXML
    private RadioButton addPartOutsourced;

    /**
     * @param event
     */
    @FXML
    // change the label to "Machine ID" when the "In House" button is clicked
    void InHouseClicked(ActionEvent event) {
        MachineOrCompany.setText("Machine ID");
    }

    /**
     * @param event
     */
    @FXML
    // change the label to "Company Name" when the "Out Sourced" button is clicked
    void OutsourcedClicked(ActionEvent event) {
        MachineOrCompany.setText("Company Name");
    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    // Go back to the main page when "cancel" button is clicked
    void cancelBtnClicked(ActionEvent event) throws IOException {
        returnTOMain(event);
    }

    /**
     *RUNTIME ERROR - NumberFormatException
     *
     * @param event
     * @throws IOException
     */
    @FXML
    // save the added part if
    void saveBtnClicked(ActionEvent event) throws IOException {
        Part p1 = null;
        INOROUT company_machine = null;

        try{
            int inv = Integer.parseInt(addPartInv.getText());
            int min = Integer.parseInt(addPartMin.getText());
            int max = Integer.parseInt(addPartMax.getText());
            double price = Double.parseDouble(addPartPrice.getText());
            String name = addPartName.getText();


            if (min > max || max < inv || inv < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Part Not Addded!");
                alert.setContentText("The Min value should be less than Max value, and the Inv value should be" +
                        " between Min and Max value!");
                alert.show();
            } else {
                partID++;
                if (addPartInHouse.isSelected()) {
                    int inHouse = Integer.parseInt(AMachine_Company.getText());
                    p1 = new InHouse(partID, name, price, inv, min, max, inHouse);
                    company_machine = new INOROUT(partID, AMachine_Company.getText(), true);
                }
                if (addPartOutsourced.isSelected()) {
                    String outSourced = AMachine_Company.getText();
                    p1 = new Outsourced(partID, name, price, inv, min, max, outSourced);
                    company_machine = new INOROUT(partID, AMachine_Company.getText(), false);
                }

                Inventory.addPart(p1);
                Inventory.machinORcompany(company_machine);
            }
            returnTOMain(event);
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("Invalid user data");
            alert.setContentText("Please enter appropriate data !");
            alert.show();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/software_i/AddPartsForm.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        }

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
