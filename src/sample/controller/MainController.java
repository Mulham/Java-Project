//Autohr: Mulham Alibrahim 170503111


package sample.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button mainLogout;

    @FXML
    private Button mainUsers;

    @FXML
    private Button mainCustomers;

    @FXML
    private Button mainEquipments;

    @FXML
    private Button mainReports;

    @FXML
    private Button mainCompanies;

    private SceneController sceneController;
    @FXML
    void initialize() {
        sceneController = new SceneController();
        mainLogout.setOnAction(event -> {
            sceneController.changeScene(mainLogout, "login.fxml");
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

    }




}
