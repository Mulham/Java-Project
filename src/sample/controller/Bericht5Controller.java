//Autohr: Mulham Alibrahim 170503111


package sample.controller;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    private JFXButton mainLogout;



    @FXML
    private Label label;

    @FXML
    private JFXComboBox<String> operator;

    @FXML
    private JFXComboBox<String> evaluator;

    @FXML
    private JFXComboBox<String> confirmator;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton pdfButton;

    @FXML
    private JFXButton excelButton;

    @FXML
    private JFXButton mainButton;
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
                        exportExcel(bericht, path[0] + "/");
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

                    } else {
                        exportPdf(bericht);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
                label.setText("You should fill the required fields!");
        });
    }
    public void exportPdf(Bericht bericht) throws Exception {
        exportExcel(bericht, "");
        Workbook workbook = new Workbook("Report_"+bericht.getReport_no()+".xlsx");

//This line is important-------------------
        workbook.calculateFormula();

// Save the document in PDF format

        String path ="";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(this);
        if (x==JFileChooser.APPROVE_OPTION)
            path = j.getSelectedFile().getPath();
        workbook.save(path +"/Report_"+bericht.getReport_no()+".pdf", SaveFormat.PDF);
        label.setText("Report exported successfully!");
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
        fadeTransition.setFromValue(1f);
        fadeTransition.setToValue(0f);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
    }
    public void exportExcel(Bericht bericht, String path){
        String excelFilePath = "report.xlsx";



        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelFilePath));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row1 = sheet.getRow(2);
            XSSFCell cell1 = row1.getCell(3);
            cell1.setCellValue(bericht.getCustomer());
            XSSFRow row2 = sheet.getRow(3);
            XSSFCell cell2 = row2.getCell(3);
            cell2.setCellValue(bericht.getProject());
            XSSFRow row3 = sheet.getRow(4);
            XSSFCell cell3 = row3.getCell(3);
            cell3.setCellValue(bericht.getInspection_place());
            XSSFRow row4 = sheet.getRow(5);
            XSSFCell cell4 = row4.getCell(3);
            cell4.setCellValue(bericht.getInspection_standart());

            XSSFRow row5 = sheet.getRow(6);
            XSSFCell cell5 = row5.getCell(3);
            cell5.setCellValue(bericht.getEvaluation_standart());

            XSSFRow row6 = sheet.getRow(2);
            XSSFCell cell6 = row6.getCell(19);
            cell6.setCellValue(bericht.getInspection_procedure());
            XSSFRow row7 = sheet.getRow(3);
            XSSFCell cell7 = row7.getCell(19);
            cell7.setCellValue(bericht.getInspection_scope()+"%");
            XSSFRow row8 = sheet.getRow(4);
            XSSFCell cell8 = row8.getCell(19);
            cell8.setCellValue(bericht.getDrawing_no());
            XSSFRow row9 = sheet.getRow(5);
            XSSFCell cell9 = row9.getCell(19);
            cell9.setCellValue(bericht.getSurface_condiotion());
            XSSFRow row10 = sheet.getRow(6);
            XSSFCell cell10 = row10.getCell(19);
            cell10.setCellValue(bericht.getStage());

            XSSFRow row11 = sheet.getRow(2);
            XSSFCell cell11 = row11.getCell(26);
            cell11.setCellValue(bericht.getPage());
            XSSFRow row12 = sheet.getRow(3);
            XSSFCell cell12 = row12.getCell(26);
            cell12.setCellValue(bericht.getReport_no());
            XSSFRow row13 = sheet.getRow(4);
            XSSFCell cell13 = row13.getCell(26);
            cell13.setCellValue(bericht.getReport_date());
            XSSFRow row14 = sheet.getRow(5);
            XSSFCell cell14 = row14.getCell(26);
            cell14.setCellValue(bericht.getJob());
            XSSFRow row15 = sheet.getRow(6);
            XSSFCell cell15 = row15.getCell(26);
            cell15.setCellValue(bericht.getOffer());


            XSSFRow row16 = sheet.getRow(8);
            XSSFCell cell16 = row16.getCell(4);
            cell16.setCellValue(bericht.getPole_distance());
            XSSFRow row17 = sheet.getRow(9);
            XSSFCell cell17 = row17.getCell(4);
            cell17.setCellValue(bericht.getEquipment());
            XSSFRow row19 = sheet.getRow(10);
            XSSFCell cell19 = row19.getCell(4);
            cell19.setCellValue(bericht.getMp());
            XSSFRow row45 = sheet.getRow(11);
            XSSFCell cell45 = row45.getCell(4);
            cell45.setCellValue(bericht.getMag());
            XSSFRow row46 = sheet.getRow(12);
            XSSFCell cell46 = row46.getCell(4);
            cell46.setCellValue(bericht.getUv());
            XSSFRow row47 = sheet.getRow(13);
            XSSFCell cell47 = row47.getCell(4);
            cell47.setCellValue(bericht.getDistance_of_light());

            XSSFRow row20 = sheet.getRow(8);
            XSSFCell cell20 = row20.getCell(16);
            cell20.setCellValue(bericht.getExamination_area());
            XSSFRow row21 = sheet.getRow(9);
            XSSFCell cell21 = row21.getCell(16);
            cell21.setCellValue(bericht.getCurrent_type());
            XSSFRow row22 = sheet.getRow(10);
            XSSFCell cell22 = row22.getCell(16);
            cell22.setCellValue(bericht.getLuxmeter());
            XSSFRow row23 = sheet.getRow(11);
            XSSFCell cell23 = row23.getCell(16);
            cell23.setCellValue(bericht.getTest_medium());
            XSSFRow row24 = sheet.getRow(12);
            XSSFCell cell24 = row24.getCell(16);
            cell24.setCellValue(bericht.getDemagnetization());
            XSSFRow row25 = sheet.getRow(13);
            XSSFCell cell25 = row25.getCell(16);
            cell25.setCellValue(bericht.getHeat_treatment());

            XSSFRow row26 = sheet.getRow(8);
            XSSFCell cell26 = row26.getCell(25);
            cell26.setCellValue(bericht.getSurface_temp());
            XSSFRow row27 = sheet.getRow(9);
            XSSFCell cell27 = row27.getCell(25);
            cell27.setCellValue(bericht.getGauss());
            XSSFRow row28 = sheet.getRow(11);
            XSSFCell cell28 = row28.getCell(25);
            cell28.setCellValue(bericht.getSurface_condition2());
            XSSFRow row29 = sheet.getRow(12);
            XSSFCell cell29 = row29.getCell(25);
            cell29.setCellValue(bericht.getId_light());
            XSSFRow row30 = sheet.getRow(13);
            XSSFCell cell30 = row30.getCell(25);
            cell30.setCellValue(bericht.getLifting_test());

            int dfg = bericht.getGrafik();


            XSSFRow row31 = sheet.getRow(14);
            XSSFCell cell31 = row31.getCell(0);
            if (dfg == 1 || dfg == 3 )
                 cell31.setCellValue(true);
            else
                cell31.setCellValue(false);

            XSSFRow row32 = sheet.getRow(14);
            XSSFCell cell32 = row32.getCell(7);
            if (dfg == 2 || dfg == 3 )
                 cell32.setCellValue(true);
            else
                cell32.setCellValue(false);
            XSSFRow row33 = sheet.getRow(19);
            XSSFCell cell33 = row33.getCell(7);
            cell33.setCellValue(bericht.getStandard_deviations());
            XSSFRow row34 = sheet.getRow(20);
            XSSFCell cell34 = row34.getCell(7);
            cell34.setCellValue(bericht.getInspection_dates());
            XSSFRow row35 = sheet.getRow(21);
            XSSFCell cell35 = row35.getCell(7);
            cell35.setCellValue(bericht.getDescription_and_attachments());

            String Name = "",Nachname= "",level= "",datum = "";
            DatabaseHandler databaseHandler = new DatabaseHandler();
            ResultSet rs = databaseHandler.getSelect("Users", bericht.getOperator(), "idUser");
            while(rs.next()) {
                 Name = rs.getString("Name");
                 Nachname = rs.getString("Nachname");
                 level = rs.getString("Level");
                 datum = rs.getString("Zertifikat_datum");
            }
            XSSFRow row36 = sheet.getRow(39);
            XSSFCell cell36 = row36.getCell(5);
            cell36.setCellValue(Name + " " + Nachname);
            XSSFRow row37 = sheet.getRow(40);
            XSSFCell cell37 = row37.getCell(5);
            cell37.setCellValue(level);
            XSSFRow row38 = sheet.getRow(41);
            XSSFCell cell38 = row38.getCell(5);
            cell38.setCellValue(datum);

            rs = databaseHandler.getSelect("Users", bericht.getEvaluator(), "idUser");
            while(rs.next()) {
                Name = rs.getString("Name");
                Nachname = rs.getString("Nachname");
                level = rs.getString("Level");
                datum = rs.getString("Zertifikat_datum");
            }
            XSSFRow row39 = sheet.getRow(39);
            XSSFCell cell39 = row39.getCell(15);
            cell39.setCellValue(Name + " " + Nachname);
            XSSFRow row40 = sheet.getRow(40);
            XSSFCell cell40 = row40.getCell(15);
            cell40.setCellValue(level);
            XSSFRow row41 = sheet.getRow(41);
            XSSFCell cell41 = row41.getCell(15);
            cell41.setCellValue(datum);

            rs = databaseHandler.getSelect("Users", bericht.getConfirmator(), "idUser");
            while(rs.next()) {
                Name = rs.getString("Name");
                Nachname = rs.getString("Nachname");
                level = rs.getString("Level");
                datum = rs.getString("Zertifikat_datum");
            }
            XSSFRow row42 = sheet.getRow(39);
            XSSFCell cell42 = row42.getCell(20);
            cell42.setCellValue(Name + " " + Nachname);
            XSSFRow row43 = sheet.getRow(40);
            XSSFCell cell43 = row43.getCell(20);
            cell43.setCellValue(level);
            XSSFRow row44 = sheet.getRow(41);
            XSSFCell cell44 = row44.getCell(20);
            cell44.setCellValue(datum);




            XSSFRow row18 = sheet.getRow(5);
            XSSFCell cell18 = row18.getCell(3);
            cell18.setCellValue(bericht.getInspection_standart());



            //Results
            int it = 0;
            String weld="",length="",thickness="",diameter="",type="",loc="",res="",welding="";
            rs = databaseHandler.getSelect("Results", bericht.getIdBericht()-1 , "Bericht");
            while(rs.next()) {
                weld = rs.getString("weld");
                length = rs.getString("length");
                thickness = rs.getString("thickness");
                diameter = rs.getString("diameter");
                type = rs.getString("defect_type");
                loc = rs.getString("defect_loc");
                res = rs.getString("result");
                welding = rs.getString("welding_process");

                it++;
                if (it == 1) {
                    XSSFRow row48 = sheet.getRow(24);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(24);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(24);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(24);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(24);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(24);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(24);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(24);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }
                else if (it == 2){
                    XSSFRow row48 = sheet.getRow(25);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(25);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(25);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(25);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(25);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(25);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(25);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(25);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }
                else if (it == 3){
                    XSSFRow row48 = sheet.getRow(26);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(26);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(26);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(26);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(26);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(26);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(26);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(26);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }
                else if (it == 4){
                    XSSFRow row48 = sheet.getRow(27);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(27);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(27);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(27);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(27);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(27);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(27);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(27);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }
                else if (it == 5){
                    XSSFRow row48 = sheet.getRow(28);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(28);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(28);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(28);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(28);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(28);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(28);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(28);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }
                else if (it == 6){
                    XSSFRow row48 = sheet.getRow(29);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(29);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(29);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(29);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(29);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(29);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(29);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(29);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }
                else if (it == 7){
                    XSSFRow row48 = sheet.getRow(30);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(30);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(30);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(30);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(30);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(30);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(30);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(30);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }else if (it == 8){
                    XSSFRow row48 = sheet.getRow(31);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(31);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(31);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(31);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(31);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(31);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(31);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(31);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }else if (it == 9){
                    XSSFRow row48 = sheet.getRow(32);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(32);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(32);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(32);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(32);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(32);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(32);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(32);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }
                else if (it == 10){
                    XSSFRow row48 = sheet.getRow(33);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(33);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(33);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(33);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(33);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(33);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(33);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(33);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }
                else if (it == 11){
                    XSSFRow row48 = sheet.getRow(34);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(34);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(34);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(34);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(34);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(34);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(34);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(34);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }
                else if (it == 12){
                    XSSFRow row48 = sheet.getRow(35);
                    XSSFCell cell48 = row48.getCell(1);
                    cell48.setCellValue(weld);
                    XSSFRow row49 = sheet.getRow(35);
                    XSSFCell cell49 = row49.getCell(8);
                    cell49.setCellValue(length);
                    XSSFRow row50 = sheet.getRow(35);
                    XSSFCell cell50 = row50.getCell(11);
                    cell50.setCellValue(welding);
                    XSSFRow row51 = sheet.getRow(35);
                    XSSFCell cell51 = row51.getCell(17);
                    cell51.setCellValue(thickness);
                    XSSFRow row52 = sheet.getRow(35);
                    XSSFCell cell52 = row52.getCell(18);
                    cell52.setCellValue(diameter);
                    XSSFRow row53 = sheet.getRow(35);
                    XSSFCell cell53 = row53.getCell(22);
                    cell53.setCellValue(type);
                    XSSFRow row54 = sheet.getRow(35);
                    XSSFCell cell54 = row54.getCell(24);
                    cell54.setCellValue(loc);
                    XSSFRow row55 = sheet.getRow(35);
                    XSSFCell cell55 = row55.getCell(27);
                    cell55.setCellValue(res);
                }
            }







            inputStream.close();

            FileOutputStream fos =new FileOutputStream(new File(path+"Report_" + bericht.getReport_no() +".xlsx"));
            workbook.write(fos);

            fos.close();
            label.setText("Report exported successfully!");
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), label);
            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(1);
            fadeTransition.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}