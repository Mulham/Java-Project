//Autohr: Mulham Alibrahim 170503111


package sample.controller;


import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.util.Duration;

import sample.Database.DatabaseHandler;
import sample.model.Bericht;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
public class Bericht5Controller extends Component {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainLogout;



    @FXML
    private Label label;

    @FXML
    private ComboBox<String> operator;

    @FXML
    private ComboBox<String> evaluator;

    @FXML
    private ComboBox<String> confirmator;

    @FXML
    private Button saveButton;

    @FXML
    private Button pdfButton;

    @FXML
    private Button excelButton;

    @FXML
    private Button mainButton;
    private SceneController sceneController;
    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        sceneController = new SceneController();

        mainLogout.setOnAction(event -> {
            sceneController.changeScene(mainLogout, "login.fxml");
        });

        try {
            ResultSet rs = databaseHandler.getTable("Users");
            while (rs.next()) {
                operator.getItems().add(rs.getString("Name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = databaseHandler.getTable("Users");
            while (rs.next()) {
                evaluator.getItems().add(rs.getString("Name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = databaseHandler.getTable("Users");
            while (rs.next()) {
                confirmator.getItems().add(rs.getString("Name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        mainButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Report data will be deleted if you didn't save it", ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK)
            sceneController.changeScene(mainButton, "main.fxml");

        });
    }

    public void getBericht(Bericht bericht) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        saveButton.setOnAction(event -> {
            if (operator.getSelectionModel().getSelectedIndex() != -1 && evaluator.getSelectionModel().getSelectedIndex() != -1
                    && confirmator.getSelectionModel().getSelectedIndex() != -1) {
                int op = 0;
                String name = null;
                String name1 = null;
                String name2 = null;
                try {
                    op = databaseHandler.getId("Users", "Name", operator.getSelectionModel().getSelectedItem());
                    bericht.setOperator(op);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                int ev = 0;
                try {
                    ev = databaseHandler.getId("Users", "Name", evaluator.getSelectionModel().getSelectedItem());
                    bericht.setEvaluator(ev);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                int cf = 0;
                try {
                    cf = databaseHandler.getId("Users", "Name", confirmator.getSelectionModel().getSelectedItem());
                    bericht.setConfirmator(cf);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Date validation;
                Date validation1;
                Date validation2;
                int year,year1,year2;
                int month, month1,month2;
                int day,day1,day2;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String d1 = "2030-12-12";
                String d3 = "2030-12-12";
                String d4 = "2030-12-12";

                try {
                    validation = databaseHandler.getDate("Users","Zertifikat_datum","idUser", op);
                    year = validation.getYear();
                    month = validation.getMonth();
                    day = validation.getDay();
                    d1 = 1902+year + "-" + month +"-"+ day;
                    Date d2 = new Date();
                    name = databaseHandler.getName1("Users",op,"idUser");
                    validation1 = databaseHandler.getDate("Users","Zertifikat_datum","idUser", ev);
                    year1 = validation1.getYear();
                    month1 = validation1.getMonth();
                    day1 = validation1.getDay();
                    d3 = 1902+year1 + "-" + month1 +"-"+ day1;
                    name1 = databaseHandler.getName1("Users",ev,"idUser");
                    validation2 = databaseHandler.getDate("Users","Zertifikat_datum","idUser", cf);
                    year2 = validation2.getYear();
                    month2 = validation2.getMonth();
                    day2 = validation2.getDay();
                    d4 = 1902+year2 + "-" + month2 +"-"+ day2;
                    name2 = databaseHandler.getName1("Users",cf,"idUser");
                    if (dateFormat.format(d2).compareTo(d1) > 0){
                        label.setText("Certificate date for "+ name  +" has ended!");

                    }else if (dateFormat.format(d2).compareTo(d3) > 0){
                        label.setText("Certificate date for "+ name1  +" has ended!");

                    }else if (dateFormat.format(d2).compareTo(d4) > 0){
                        label.setText("Certificate date for "+ name2  +" has ended!");

                    }
                    else{
                        boolean check = databaseHandler.addBericht(bericht);
                        if (check){
                            label.setText("Report added successfully!");
                            FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
                            fadeTransition.setFromValue(1f);
                            fadeTransition.setToValue(0f);
                            fadeTransition.setCycleCount(1);
                            fadeTransition.play();
                        }
                        else{
                            label.setText("Couldn't add the Report!");
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
               }else
                label.setText("You should fill the required fields!");
        });
        final String[] path = {""};
        JFileChooser j = new JFileChooser();
        excelButton.setOnAction(event -> {
            if (operator.getSelectionModel().getSelectedIndex() != -1 && evaluator.getSelectionModel().getSelectedIndex() != -1
            && confirmator.getSelectionModel().getSelectedIndex() != -1){
                int op=0;
                try {
                    op = databaseHandler.getId("Users", "Name", operator.getSelectionModel().getSelectedItem());
                    bericht.setOperator(op);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                int ev = 0;
                try {
                    ev = databaseHandler.getId("Users", "Name", evaluator.getSelectionModel().getSelectedItem());
                    bericht.setEvaluator(ev);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                int cf = 0;
                try {
                    cf = databaseHandler.getId("Users", "Name", confirmator.getSelectionModel().getSelectedItem());
                    bericht.setConfirmator(cf);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String name = null;
                String name1 = null;
                String name2 = null;
                Date validation;
            Date validation1;
            Date validation2;
            int year,year1,year2;
            int month, month1,month2;
            int day,day1,day2;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String d1 = "2030-12-12";
            String d3 = "2030-12-12";
            String d4 = "2030-12-12";

            try {
                validation = databaseHandler.getDate("Users", "Zertifikat_datum", "idUser", op);
                year = validation.getYear();
                month = validation.getMonth();
                day = validation.getDay();
                d1 = 1902 + year + "-" + month + "-" + day;
                Date d2 = new Date();
                name = databaseHandler.getName1("Users", op, "idUser");
                validation1 = databaseHandler.getDate("Users", "Zertifikat_datum", "idUser", ev);
                year1 = validation1.getYear();
                month1 = validation1.getMonth();
                day1 = validation1.getDay();
                d3 = 1902 + year1 + "-" + month1 + "-" + day1;
                name1 = databaseHandler.getName1("Users", ev, "idUser");
                validation2 = databaseHandler.getDate("Users", "Zertifikat_datum", "idUser", cf);
                year2 = validation2.getYear();
                month2 = validation2.getMonth();
                day2 = validation2.getDay();
                d4 = 1902 + year2 + "-" + month2 + "-" + day2;
                name2 = databaseHandler.getName1("Users", cf, "idUser");
                if (dateFormat.format(d2).compareTo(d1) > 0) {
                    label.setText("Certificate date for " + name + " has ended!");

                } else if (dateFormat.format(d2).compareTo(d3) > 0) {
                    label.setText("Certificate date for " + name1 + " has ended!");

                } else if (dateFormat.format(d2).compareTo(d4) > 0) {
                    label.setText("Certificate date for " + name2 + " has ended!");

                } else {
                    j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int x = j.showSaveDialog(this);
                    if (x == JFileChooser.APPROVE_OPTION) {
                        path[0] = j.getSelectedFile().getPath();

                  }


                }
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }}
            else
                label.setText("You should fill the required fields!");
        });
        pdfButton.setOnAction(event -> {
            if (operator.getSelectionModel().getSelectedIndex() != -1 && evaluator.getSelectionModel().getSelectedIndex() != -1
                    && confirmator.getSelectionModel().getSelectedIndex() != -1) {
                int op=0;
                try {
                    op = databaseHandler.getId("Users", "Name", operator.getSelectionModel().getSelectedItem());
                    bericht.setOperator(op);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                int ev = 0;
                try {
                    ev = databaseHandler.getId("Users", "Name", evaluator.getSelectionModel().getSelectedItem());
                    bericht.setEvaluator(ev);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                int cf = 0;
                try {
                    cf = databaseHandler.getId("Users", "Name", confirmator.getSelectionModel().getSelectedItem());
                    bericht.setConfirmator(cf);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String name = null;
                String name1 = null;
                String name2 = null;
                Date validation;
                Date validation1;
                Date validation2;
                int year,year1,year2;
                int month, month1,month2;
                int day,day1,day2;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String d1 = "2030-12-12";
                String d3 = "2030-12-12";
                String d4 = "2030-12-12";
                try {
                    validation = databaseHandler.getDate("Users", "Zertifikat_datum", "idUser", op);
                    year = validation.getYear();
                    month = validation.getMonth();
                    day = validation.getDay();
                    d1 = 1902 + year + "-" + month + "-" + day;
                    Date d2 = new Date();
                    name = databaseHandler.getName1("Users", op, "idUser");
                    validation1 = databaseHandler.getDate("Users", "Zertifikat_datum", "idUser", ev);
                    year1 = validation1.getYear();
                    month1 = validation1.getMonth();
                    day1 = validation1.getDay();
                    d3 = 1902 + year1 + "-" + month1 + "-" + day1;
                    name1 = databaseHandler.getName1("Users", ev, "idUser");
                    validation2 = databaseHandler.getDate("Users", "Zertifikat_datum", "idUser", cf);
                    year2 = validation2.getYear();
                    month2 = validation2.getMonth();
                    day2 = validation2.getDay();
                    d4 = 1902 + year2 + "-" + month2 + "-" + day2;
                    name2 = databaseHandler.getName1("Users", cf, "idUser");
                    if (dateFormat.format(d2).compareTo(d1) > 0) {
                        label.setText("Certificate date for " + name + " has ended!");

                    } else if (dateFormat.format(d2).compareTo(d3) > 0) {
                        label.setText("Certificate date for " + name1 + " has ended!");

                    } else if (dateFormat.format(d2).compareTo(d4) > 0) {
                        label.setText("Certificate date for " + name2 + " has ended!");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
                label.setText("You should fill the required fields!");
        });
    }










}