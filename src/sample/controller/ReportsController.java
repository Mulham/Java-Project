//Autohr: Mulham Alibrahim 170503111


package sample.controller;


import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Database.DatabaseHandler;
import sample.model.Bericht;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainLogout;

    @FXML
    private Button table_back;

    @FXML
    private ImageView addReport;

    @FXML
    private TableView<Bericht> table;

    @FXML
    private TableColumn<Bericht, Integer> tableId;

    @FXML
    private TableColumn<Bericht, String> table_customer;

    @FXML
    private TableColumn<Bericht, String> table_project;

    @FXML
    private TableColumn<Bericht, String> table_equipment;

    @FXML
    private Button deleteReport;

    @FXML
    private Button editReport;

    @FXML
    private Label radiographer_label;
    private SceneController sceneController;
    ObservableList<Bericht> oblist = FXCollections.observableArrayList();

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
        addReport.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

                    sceneController.changeSceneImage(addReport, "bericht1.fxml");
                }

        );
        deleteReport.setOnAction(event ->{
            Bericht bericht = table.getSelectionModel().getSelectedItem();
            if (!table.getSelectionModel().isEmpty()){
                boolean check = databaseHandler.deleteBericht(bericht);
                if (check){
                    radiographer_label.setText("Report deleted Successfully!");
                    oblist.clear();
                    tableView();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),radiographer_label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    radiographer_label.setText("Report couldn't be deleted!");
                }
            }else{
                radiographer_label.setText("You should select an Item");
            }
        });
        editReport.setOnAction(event -> {
            Bericht bericht = table.getSelectionModel().getSelectedItem();
            if (!table.getSelectionModel().isEmpty()){
                editReport.getScene().getWindow().hide();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/bericht1.fxml"));
                    Parent root = (Parent) loader.load();
                    Bericht1Controller bericht1 = loader.getController();
                    bericht1.getInfo(bericht);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }catch  (IOException | SQLException e){
                    e.printStackTrace();
                }
            }
            else{
                radiographer_label.setText("You should select an Item");
            }


        });
    }
    public void tableView(){
        databaseHandler = new DatabaseHandler();
        try{
            ResultSet rs = databaseHandler.getTable("Berichten");
            while (rs.next()) {
                String customer = databaseHandler.getName1("Firmen", rs.getInt("firma"),"idFirmen");
                String project = databaseHandler.getName1("Projects", rs.getInt("project"),"idProjects");
                String equipment = databaseHandler.getName1("Geraeten", rs.getInt("geraet"),"idGeraeten");

                oblist.add(new Bericht(rs.getInt("idBerichten"),customer,project,equipment));

            } } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tableId.setCellValueFactory(new PropertyValueFactory<>("idBericht"));
        table_customer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        table_project.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        table_equipment.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
        table.setItems(oblist);

    }
}
