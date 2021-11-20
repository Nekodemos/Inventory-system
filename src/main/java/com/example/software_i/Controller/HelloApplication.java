package com.example.software_i.Controller;

import com.example.software_i.Model.InHouse;
import com.example.software_i.Model.Inventory;
import com.example.software_i.Model.Part;
import com.example.software_i.Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/*
*JavaDoc location
*C:\Users\Neko2\OneDrive\Documents\java projects\Software_I\javadoc
*/

public class HelloApplication extends Application {
    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/software_i/MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

       launch(args);
    }
}