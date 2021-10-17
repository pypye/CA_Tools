module app.ca_tools {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.ca_tools to javafx.fxml;
    exports app.ca_tools;
}