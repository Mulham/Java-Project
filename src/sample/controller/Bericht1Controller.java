//Autohr: Mulham Alibrahim 170503111

package sample.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Bericht;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class Bericht1Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainLogout;

    @FXML
    private Button backButton;

    @FXML
    private TextField inspectionPlace;

    @FXML
    private Button nextButton;

    @FXML
    private Label idLabel;

    @FXML
    private ComboBox<String> Customer;

    @FXML
    private ComboBox<String> Project;

    @FXML
    private TextField inspectionStandart;

    @FXML
    private TextField evaluationStandart;

    @FXML
    private TextField inspectionProcedure;

    @FXML
    private Slider inspectionScpoe;

    @FXML
    private TextField drawingNo;

    @FXML
    private ComboBox<String> surfaceCondition;

    @FXML
    private ComboBox<String> stageOfExamination;

    @FXML
    private TextField page;

    @FXML
    private Label reportNo;

    @FXML
    private DatePicker reportDate;

    @FXML
    private ComboBox<String> jobOrderNo;
    @FXML
    private ComboBox<String> equipment;
    @FXML
    private Label label;
    @FXML
    private ComboBox<String> offerNo;
    private SceneController sceneController;
    DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {
        sceneController = new SceneController();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        mainLogout.setOnAction(event -> {
            sceneController.changeScene(mainLogout, "login.fxml");
        });
        backButton.setOnAction(event -> {
            sceneController.changeScene(backButton, "main.fxml");
        });
        databaseHandler = new DatabaseHandler();
        try {
            ResultSet rs = databaseHandler.getTable("Firmen");
            while (rs.next()) {
                Customer.getItems().add(rs.getString("Name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = databaseHandler.getTable("Geraeten");
            while (rs.next()) {
                equipment.getItems().add(rs.getString("Name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Customer.setOnMousePressed(event -> {
            if (!Customer.getSelectionModel().isEmpty()) {
                try {
                    DatabaseHandler databaseHandler1 = new DatabaseHandler();
                    int myid  = databaseHandler1.getId("Firmen", "Name", Customer.getSelectionModel().getSelectedItem());
                    ResultSet rrs =  databaseHandler1.getSelect("Firmen", myid, "idFirmen" );
                    while(rrs.next())
                        inspectionPlace.setText(rrs.getString("Stadt"));
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        try {
            ResultSet rs = databaseHandler.getTable("Projects");
            while (rs.next()) {
                Project.getItems().add(rs.getString("Name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = databaseHandler.getTable("SurfaceCondition");
            while (rs.next()) {
                surfaceCondition.getItems().add(rs.getString("Name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = databaseHandler.getTable("Stages");
            while (rs.next()) {
                stageOfExamination.getItems().add(rs.getString("Name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = databaseHandler.getTable("JobOrder");
            while (rs.next()) {
                jobOrderNo.getItems().add(rs.getString("No"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = databaseHandler.getTable("Offer");
            while (rs.next()) {
                offerNo.getItems().add(rs.getString("No"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int id = databaseHandler.getLastId("Berichten") +1;


        reportNo.setText(String.valueOf(id));

        DatabaseHandler finalDatabaseHandler = databaseHandler;
        nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            int customer = 0;
            if (!Customer.getSelectionModel().isEmpty() && !Project.getSelectionModel().isEmpty()
                    && !inspectionPlace.getText().isEmpty() && !evaluationStandart.getText().isEmpty()
                    && !inspectionProcedure.getText().isEmpty() && !page.getText().isEmpty() && reportDate.getValue() != null
                    && !equipment.getSelectionModel().isEmpty() && !surfaceCondition.getSelectionModel().isEmpty()
                    && !stageOfExamination.getSelectionModel().isEmpty() && !jobOrderNo.getSelectionModel().isEmpty()
                    && !offerNo.getSelectionModel().isEmpty()) {

            try {
                customer = finalDatabaseHandler.getId("Firmen", "Name", Customer.getSelectionModel().getSelectedItem());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int project = 0;
            try {
                project = finalDatabaseHandler.getId("Projects","Name",Project.getSelectionModel().getSelectedItem());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String ins_place = inspectionPlace.getText();
                    String ins_standart = inspectionStandart.getText();
                    String ev_standart = evaluationStandart.getText();
                    String ins_procedure = inspectionProcedure.getText();
                    int scope = (int) inspectionScpoe.getValue();
                    String drawing = drawingNo.getText();
            int sur_condition = 0;
            try {
                sur_condition = finalDatabaseHandler.getId("SurfaceCondition", "Name", surfaceCondition.getSelectionModel().getSelectedItem());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int stage = 0;
            try {
                stage = finalDatabaseHandler.getId("Stages", "Name", stageOfExamination.getSelectionModel().getSelectedItem());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String pg = page.getText();
            LocalDate date = reportDate.getValue();
            String jobNo = jobOrderNo.getSelectionModel().getSelectedItem();
            String offer = offerNo.getSelectionModel().getSelectedItem();

            boolean check = false;
            Bericht bericht = new Bericht(id, customer, project, ins_place, ins_standart, ev_standart,
                                ins_procedure, scope, drawing,sur_condition,stage,pg,String.valueOf(id), date,jobNo,offer);
            try {
                DatabaseHandler databaseHandler1 = new DatabaseHandler();
                int myid  = databaseHandler1.getId("Geraeten", "Name", equipment.getSelectionModel().getSelectedItem());
                Date validation;
                int year;
                int month;
                int day;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String d1 = "2030-12-12";
                validation = databaseHandler1.getDate("Geraeten","Kolabirasyon_Datum","idGeraeten", myid);
                year = validation.getYear();
                month = validation.getMonth();
                day = validation.getDay();
                d1 = 1902+year + "-" + month +"-"+ day;
                Date d2 = new Date();
                bericht.setEquipment(myid);

                if (dateFormat.format(d2).compareTo(d1) > 0){
                    label.setText("kolabirasyon date for equipment has ended!");

                }
                else{
                    nextButton.getScene().getWindow().hide();

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/bericht2.fxml"));
                        Parent root = (Parent) loader.load();
                        Bericht2Controller bericht2 = loader.getController();
                        bericht2.getBericht(bericht);
                        Stage st = new Stage();
                        st.setScene(new Scene(root));
                        st.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                }catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            }else
                label.setText("You should fill all required fields!");




                }

        );


    }
    public void getInfo(Bericht bericht) throws SQLException {
        Customer.setValue(bericht.getCustomerName());
        Project.setValue(bericht.getProjectName());
       // inspectionPlace.setText(bericht.getInspection_place());
        equipment.setValue(bericht.getEquipmentName());
      /*  inspectionScpoe.setValue(bericht.getInspection_scope());
        drawingNo.setText(bericht.getDrawing_no());
        String surface = databaseHandler.getName1("SurfaceCondition",bericht.getSurface_condiotion(), "idSurfaceCondition");
        surfaceCondition.setValue(surface);
        String stage = databaseHandler.getName1("Stages",bericht.getStage(), "idStages");
        stageOfExamination.setValue(stage);
        reportDate.setValue(bericht.getReport_date());
        jobOrderNo.setValue(bericht.getJob());
        offerNo.setValue(bericht.getOffer());*/
    }
}