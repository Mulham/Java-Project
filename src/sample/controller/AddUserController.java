//Autohr: Mulham Alibrahim 170503111


package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton mainLogout;

    @FXML
    private JFXTextField addName;

    @FXML
    private JFXTextField addSurname;

    @FXML
    private JFXTextField addLevel;

    @FXML
    private JFXDatePicker addDate;

    @FXML
    private JFXButton table_back;

    @FXML
    private JFXPasswordField addPassword;


    @FXML
    private JFXButton addButton;

    @FXML
    private Label addLabel;
    private SceneController sceneController;

    @FXML
    void initialize() {
        sceneController = new SceneController();

        addButton.setOnAction(event -> {
            createUser();
       });
        mainLogout.setOnAction(event -> {
            sceneController.changeScene(mainLogout, "login.fxml");
        });
        table_back.setOnAction(event -> {
            table_back.getScene().getWindow().hide();
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
    private void createUser(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String Name = addName.getText();
        String Nachname = addSurname.getText();
        String Level = addLevel.getText();
        LocalDate date = addDate.getValue();
        String pass = addPassword.getText();
        boolean check = false;
            User user = new User(Name,Nachname,Level,pass,date);
            check = databaseHandler.addUser(user);

        if (check){
            addName.setText("Name");
            addSurname.setText("Surname");
            addLevel.setText("Level");
            addDate.getEditor().clear();
            addPassword.setText("Password");
            addLabel.setText("User has been added Successfully!");
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),addLabel);
            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(1);
            fadeTransition.play();
        } else{
            addLabel.setText("User Couldn't be added!");
        }
    }
}
