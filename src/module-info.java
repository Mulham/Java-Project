module java.project {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    exports sample.controller;
    opens sample;
}