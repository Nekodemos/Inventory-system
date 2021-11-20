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


public class ModifyPartForm {

    Inventory invent;
    @FXML
    private Label Lable7;
    @FXML
    private TextField Mmin;

    @FXML
    private TextField MID;

    @FXML
    private TextField MName;

    @FXML
    private TextField MInv;

    @FXML
    private TextField MPrice;

    @FXML
    private TextField Mmax;

    @FXML
    private TextField Machine_Company;

    @FXML
    private RadioButton MInHouse;

    @FXML
    private RadioButton MOutsourced;

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void CancelClicked(ActionEvent event) throws IOException {
        returnTOMain(event);
    }

    /**
     * @param event
     */
    @FXML
    void InHouseClicked(ActionEvent event) {
        Lable7.setText("Machine ID");
    }

    /**
     * @param event
     */
    @FXML
    void OutsourcedClicked(ActionEvent event) {
        Lable7.setText("Company Name");
    }

    /**
     * RUNTIME ERROR- NumberFormatException
     * @param event
     * @throws IOException
     */
    @FXML
    void SaveClicked(ActionEvent event) throws IOException {
        Part p1 = null;
        INOROUT company_machine=null;
        try{
            int partID = Integer.parseInt(MID.getText());
            int inv = Integer.parseInt(MInv.getText());
            int min = Integer.parseInt(Mmin.getText());
            int max = Integer.parseInt(Mmax.getText());
            double price = Double.parseDouble(MPrice.getText());
            String name = MName.getText();
            if (min > max || max < inv || inv < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Part Not Modified!");
                alert.setContentText("The Min value should be less than Max value, and the Inv value should be" +
                        " between Min and Max value!");
                alert.show();
            }else {
                if (MInHouse.isSelected()) {
                    int inHouse = Integer.parseInt(Machine_Company.getText());
                    p1 = new InHouse(partID, name, price, inv, min, max, inHouse);
                    company_machine= new INOROUT(partID,Machine_Company.getText(), true);
                }
                if (MOutsourced.isSelected()) {
                    String outSourced = Machine_Company.getText();
                    p1 = new Outsourced(partID, name, price, inv, min, max, outSourced);
                    company_machine= new INOROUT(partID,Machine_Company.getText(), false);
                }

                Inventory.updatePart(partID,p1);
                Inventory.updateINOROUT(company_machine);
            }

            returnTOMain(event);

        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setHeaderText("Invalid user data");
            alert.setContentText("Please enter appropriate data !");
            alert.show();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/software_i/MainForm.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();

        }

    }

    /**
     * @param part
     */
    public void setPart(Part part){
        INOROUT MidOrCname;
        MID.setText(String.valueOf(part.getId()));
        MName.setText(String.valueOf(part.getName()));
        MInv.setText(String.valueOf(part.getStock()));
        MPrice.setText(String.valueOf(part.getPrice()));
        Mmax.setText(String.valueOf(part.getMax()));
        Mmin.setText(String.valueOf(part.getMin()));
        MidOrCname=Inventory.machineORcomp(part.getId());
        Machine_Company.setText(String.valueOf(MidOrCname.getInHouse_OutSource()));
        if (MidOrCname.isInHouse()==true)
            MInHouse.setSelected(true);
        else{
            MOutsourced.setSelected(true);
            Lable7.setText("Company Name");
        }

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
}
