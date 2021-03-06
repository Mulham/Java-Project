package sample.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;
public class LoginController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Label label;
    @FXML
    private Button loginButton;
    private DatabaseHandler databaseHandler;
    private SceneController sceneController;




    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();
        sceneController = new SceneController();

        loginButton.setOnAction(event -> {

            String loginText = loginUsername.getText().trim();
            String loginpwd = loginPassword.getText().trim();


            if(!loginText.equals("") || !loginpwd.equals("")) {
                ResultSet userRow = databaseHandler.getUser(loginText, loginpwd);
                int counter = 0;
                try {
                    while (userRow.next()) {
                        counter++;
                    }
                    if (counter == 1) {
                        System.out.println("Login Successful!!!");
                        sceneController.changeScene(loginButton, "main.fxml");
                    } else {
                        label.setText("wrong username or password!");
                        Shaker shaker = new Shaker(loginUsername);
                        shaker.shake();
                        Shaker shaker1 = new Shaker(loginPassword);
                        shaker1.shake();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
});
    }
}