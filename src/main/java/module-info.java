module com.example.software_i {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.software_i to javafx.fxml;
    opens com.example.software_i.Model to javafx.fxml;
    opens com.example.software_i.Controller to javafx.fxml;
    exports com.example.software_i.Controller;
    exports com.example.software_i.Model;

}