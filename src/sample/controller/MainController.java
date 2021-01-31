//Autohr: Mulham Alibrahim 170503111


package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton mainLogout;

    @FXML
    private JFXButton mainUsers;

    @FXML
    private JFXButton mainCustomers;

    @FXML
    private JFXButton mainEquipments;

    @FXML
    private JFXButton mainReports;

    @FXML
    private JFXButton mainCompanies;

    private SceneController sceneController;
    @FXML
    void initialize() {
        sceneController = new SceneController();
        mainLogout.setOnAction(event -> {
            sceneController.changeScene(mainLogout, "login.fxml");
        });
        mainReports.setOnAction(event -> {
            mainReports.getScene().getWindow().hide();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/reports.fxml"));
                Parent root = (Parent) loader.load();
                ReportsController reportsController = loader.getController();
                reportsController.tableView();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch  (IOException e){
                e.printStackTrace();
            }
        });

        mainCompanies.setOnAction(event -> {
            mainUsers.getScene().getWindow().hide();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/firmen.fxml"));
                Parent root = (Parent) loader.load();
                FirmController firmController = loader.getController();
                firmController.tableView();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch  (IOException e){
                e.printStackTrace();
            }


        });
        mainUsers.setOnAction(event -> {
            mainUsers.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/users.fxml"));
                Parent root = (Parent) loader.load();
                UserController userController = loader.getController();
                userController.tableView();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch  (IOException e){
                e.printStackTrace();
            }       });
        mainEquipments.setOnAction(event -> {
            mainEquipments.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/equipments.fxml"));
                Parent root = (Parent) loader.load();
                EquipmentController equipmentController = loader.getController();
                equipmentController.tableView();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch  (IOException e){
                e.printStackTrace();
            }       });

        mainCustomers.setOnAction(event -> {
            mainCustomers.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/projects.fxml"));
                Parent root = (Parent) loader.load();
                ProjectController customerController = loader.getController();
                customerController.tableView();
                customerController.table2View();
                customerController.table3View();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch  (IOException e){
                e.printStackTrace();
            }
        });

    }




}
