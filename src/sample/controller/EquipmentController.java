//Autohr: Mulham Alibrahim 170503111


package sample.controller;


import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import sample.Database.DatabaseHandler;
import sample.model.Equipment;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EquipmentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainLogout;

    @FXML
    private Button table_back;
    @FXML
    private TableView<Equipment> table;
    @FXML
    private TableColumn<Equipment, String> name;

    @FXML
    private TableColumn<Equipment, Float> poleDistance;

    @FXML
    private TableColumn<Equipment, String> mp;

    @FXML
    private TableColumn<Equipment, String> mag;

    @FXML
    private TableColumn<Equipment, String> uv;

    @FXML
    private TableColumn<Equipment, String> light;

    @FXML
    private TableColumn<Equipment, String> performance;

    @FXML
    private TableColumn<Equipment, LocalDate> k_date;

    @FXML
    private TextField name_field;

    @FXML
    private TextField pole_field;

    @FXML
    private TextField mp_field;

    @FXML
    private TextField mag_field;

    @FXML
    private TextField uv_field;

    @FXML
    private TextField light_field;

    @FXML
    private TextField performance_field;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;
    @FXML
    private Label label;

    @FXML
    private DatePicker date_field;
    private SceneController sceneController;
    ObservableList<Equipment> oblist = FXCollections.observableArrayList();
    DatabaseHandler databaseHandler;
    @FXML
    void initialize() {
        sceneController = new SceneController();

        mainLogout.setOnAction(event -> {
            sceneController.changeScene(mainLogout, "login.fxml");
        });
        table_back.setOnAction(event -> {
            sceneController.changeScene(table_back, "main.fxml");
        });
        table.setOnMouseClicked(event -> {
            if (!table.getSelectionModel().isEmpty()){
                Equipment equipment = table.getSelectionModel().getSelectedItem();
                name_field.setText(equipment.getName());
                pole_field.setText(String.valueOf(equipment.getPole_distance()));

                mp_field.setText(equipment.getMp_carrier_medium());

                mag_field.setText(equipment.getMag_tech());
                uv_field.setText(equipment.getUv_light_intensity());
                light_field.setText(equipment.getDistance_of_light());
                performance_field.setText(equipment.getProduktleistungen());
                date_field.getEditor().setText(String.valueOf(equipment.getKolabirasyon_Datum()));
            }
        });
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    DatabaseHandler databaseHandler = new DatabaseHandler();
                    String Name = name_field.getText();
                    float distance = Float.parseFloat(pole_field.getText());
                    String mp = mp_field.getText();
                    String mag = mag_field.getText();
                    String uv = uv_field.getText();
                    String light = light_field.getText();
                    String performance= performance_field.getText();
                    LocalDate date = date_field.getValue();

                        boolean check = false;
                        Equipment equipment = new Equipment(Name, distance, mp, mag, uv, light, performance, date);
                        check = databaseHandler.addEquipment(equipment);

                        if (check) {
                            name_field.clear();
                            pole_field.clear();
                            label.setText("Equipment added Successfully!");
                            oblist.clear();
                            tableView();
                            FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), label);
                            fadeTransition.setFromValue(1f);
                            fadeTransition.setToValue(0f);
                            fadeTransition.setCycleCount(1);
                            fadeTransition.play();
                        } else {
                            label.setText("Equipment Couldn't be added!");
                        }

                }

        );
        updateButton.setOnAction(event -> {
            Equipment equipment = table.getSelectionModel().getSelectedItem();
            if (!table.getSelectionModel().isEmpty()){
                databaseHandler = new DatabaseHandler();
                String Name = name_field.getText();
                float distance = Float.parseFloat(pole_field.getText());
                String mp = mp_field.getText();
                String mag = mag_field.getText();
                String uv = uv_field.getText();
                String light = light_field.getText();
                String performance= performance_field.getText();
                LocalDate date = date_field.getValue();
                Equipment rg = new Equipment( equipment.getIdGeraeten() , Name, distance, mp, mag, uv, light, performance, date);
                boolean check = databaseHandler.editEquipment(rg);
                if (check){
                    label.setText("Equipment updated successfully!");
                    oblist.clear();
                    tableView();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    label.setText("Couldn't update the Equipment!");
                }
            }


            else{
                label.setText("You should select an Item");
            }


        });
        deleteButton.setOnAction(event ->{
            Equipment equipment = table.getSelectionModel().getSelectedItem();
            if (!table.getSelectionModel().isEmpty()){
                boolean check = databaseHandler.deleteEquipment(equipment);
                if (check){
                    label.setText("Equipment deleted Successfully!");
                    oblist.clear();
                    tableView();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    label.setText("Equipment couldn't be deleted!");
                }
            }else{
                label.setText("You should select an Item");
            }
        });
    }
    public void tableView() {
        databaseHandler = new DatabaseHandler();
        try {
            ResultSet rs = databaseHandler.getTable("Geraeten");
            while (rs.next()) {

                oblist.add(new Equipment(rs.getInt("idGeraeten"), rs.getString("Name"), rs.getFloat("pole_distance")
                        , rs.getString("mp_carrier_medium"), rs.getString("mag_tech"), rs.getString("uv_light_intensity"),
                        rs.getString("distance_of_light"), rs.getString("produktleistungen"),rs.getDate("kolabirasyon_Datum").toLocalDate()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        poleDistance.setCellValueFactory(new PropertyValueFactory<>("pole_distance"));
        mp.setCellValueFactory(new PropertyValueFactory<>("mp_carrier_medium"));
        mag.setCellValueFactory(new PropertyValueFactory<>("mag_tech"));
        uv.setCellValueFactory(new PropertyValueFactory<>("uv_light_intensity"));
        light.setCellValueFactory(new PropertyValueFactory<>("distance_of_light"));
        performance.setCellValueFactory(new PropertyValueFactory<>("produktleistungen"));
        k_date.setCellValueFactory(new PropertyValueFactory<>("Kolabirasyon_Datum"));
        table.setItems(oblist);
    }
}
