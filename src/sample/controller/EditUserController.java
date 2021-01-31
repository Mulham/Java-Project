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
import java.util.ResourceBundle;

public class EditUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton mainLogout;

    @FXML
    private JFXButton table_back;

    @FXML
    private JFXTextField addName;

    @FXML
    private JFXTextField addSurname;

    @FXML
    private JFXTextField addLevel;

    @FXML
    private JFXDatePicker addDate;

    @FXML
    private JFXPasswordField addPassword;

    @FXML
    private JFXButton updateButton;
    @FXML
    private Label idLabel;
    @FXML
    private Label addLabel;
    private SceneController sceneController;
    DatabaseHandler databaseHandler;
    @FXML
    void initialize() {

        sceneController = new SceneController();
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
    public void getInfo(User user){
       addName.setText(user.getName());
       addSurname.setText(user.getNachname());
       addLevel.setText(user.getLevel());
       addDate.getEditor().setText(String.valueOf(user.getZertifikat_datum()));
       idLabel.setText(String.valueOf(user.getId()));
       updateButton.setOnAction(event -> {
            databaseHandler = new DatabaseHandler();
            User rg = new User( user.getId() , addName.getText(),addSurname.getText(),
                    addLevel.getText(),addDate.getValue(), addPassword.getText() );
            boolean check = databaseHandler.editUser(rg);
            if (check){
                addLabel.setText("User updated successfully!");
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),addLabel);
                fadeTransition.setFromValue(1f);
                fadeTransition.setToValue(0f);
                fadeTransition.setCycleCount(1);
                fadeTransition.play();
            }
            else{
                addLabel.setText("Couldn't update the user!");
            }
       });
    }

}
