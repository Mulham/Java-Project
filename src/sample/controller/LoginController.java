//Autohr: Mulham Alibrahim 170503111
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



        /*
        loginSignupButton.setOnAction(event -> {
            loginSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(name:"/sample/view/signup.fxml"));
            try{
                loader.load();
             } catch (IOExeption e) {
                e.printStackTrace();
            }

            parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }

        *//*
        try {
            loginUsername.setText(databaseHandler.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        loginButton.setOnAction(event -> {

            String loginText = loginUsername.getText().trim();
            String loginpwd = loginPassword.getText().trim();

            //databaseHandler.signupUser(loginUsername.getText(), loginPassword.getText());
           if(!loginText.equals("") || !loginpwd.equals("")) {
               ResultSet userRow = databaseHandler.getUser(loginText, loginpwd);
               int counter = 0;
               Date validation;
               int year;
               int month;
               int day;
               DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
               String d1 = "2030-12-12";
               try{
                   while (userRow.next()){
                       validation = userRow.getDate(5);
                       year = validation.getYear();
                       month = validation.getMonth();
                        day = validation.getDay();
                        d1 = 1902+year + "-" + month +"-"+ day;


                       counter++;
                   }
                   Date d2 = new Date();
                   System.out.println("d2 = " + dateFormat.format(d2));
                   System.out.println("d1 = " + d1);
                   if (counter == 1 && dateFormat.format(d2).compareTo(d1) < 0){
                       System.out.println("Login Successful!!!");
                       sceneController.changeScene(loginButton, "main.fxml");
                   }
                   else{
                       if(dateFormat.format(d2).compareTo(d1) > 0){
                           label.setText("Your Certificate date has ended!");
                       }else{
                           label.setText("wrong username or password!");
                       }
                       Shaker shaker = new Shaker(loginUsername);
                       shaker.shake();
                       Shaker shaker1 = new Shaker(loginPassword);
                       shaker1.shake();
                   }

               }catch (SQLException e){
                   e.printStackTrace();
               }
            }else{
                System.out.println("error login in user");
            }
        });

    }



}
