module INF202 {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    exports sample.controller;
    opens sample;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;
    opens sample.controller;
    opens sample.model;
}