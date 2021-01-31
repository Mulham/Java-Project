//Autohr: Mulham Alibrahim 170503111


package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import sample.Database.DatabaseHandler;
import sample.model.Project;
import sample.model.Stage;
import sample.model.Surface;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProjectController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton mainLogout;

    @FXML
    private JFXButton table_back;

    @FXML
    private ImageView surface_add;

    @FXML
    private TableView<Project> table;

    @FXML
    private TableColumn<Project, Integer> project_id;

    @FXML
    private TableColumn<Project, String> project_table;

    @FXML
    private JFXButton project_delete;

    @FXML
    private JFXTextField project_field;

    @FXML
    private JFXTextField surface_field;

    @FXML
    private JFXButton project_update;

    @FXML
    private TableView<Surface> table1;

    @FXML
    private TableColumn<Surface, Integer> surface_id;

    @FXML
    private TableColumn<Surface, String> surface_table;

    @FXML
    private JFXButton surface_delete;

    @FXML
    private JFXButton surface_update;
    @FXML
    private TableView<Stage> table11;

    @FXML
    private TableColumn<Stage, Integer> stage_id;

    @FXML
    private TableColumn<Stage, String> stage_table;

    @FXML
    private ImageView stage_add;

    @FXML
    private JFXTextField stage_field;

    @FXML
    private JFXButton stage_delete;

    @FXML
    private JFXButton stage_update;
    @FXML
    private Label label;

    @FXML
    private ImageView project_add;

    private SceneController sceneController;
    ObservableList<Project> oblist = FXCollections.observableArrayList();
    ObservableList<Surface> oblist1 = FXCollections.observableArrayList();
    ObservableList<Stage> oblist2 = FXCollections.observableArrayList();

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


        project_add.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            String Name = project_field.getText();
            if (Name.equals("Name") || Name.isEmpty()){
                label.setText("You should Enter Project Name!");
            }
            else {
                boolean check = false;
                Project project = new Project(Name);
                check = databaseHandler.addProject(project);

                if (check) {
                    project_field.setText("Project Name");
                    label.setText("Project added Successfully!");
                    oblist.clear();
                    tableView();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                } else {
                    label.setText("Project Couldn't be added!");
                }
            }
                }

        );
        project_delete.setOnAction(event ->{
            Project project = table.getSelectionModel().getSelectedItem();
            if (!table.getSelectionModel().isEmpty()){
                boolean check = databaseHandler.deleteProject(project);
                if (check){
                    label.setText("Project deleted Successfully!");
                    oblist.clear();
                    tableView();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    label.setText("Project couldn't be deleted!");
                }
            }else{
                label.setText("You should select an Item");
            }
        });
        table.setOnMouseClicked(event -> {
            if (!table.getSelectionModel().isEmpty()){
                Project project = table.getSelectionModel().getSelectedItem();
                project_field.setText(project.getName());
            }
        });
        project_update.setOnAction(event -> {
            Project project = table.getSelectionModel().getSelectedItem();
            if (!table.getSelectionModel().isEmpty()){
                databaseHandler = new DatabaseHandler();
                Project rg = new Project( project.getIdProjects() , project_field.getText()
                );
                boolean check = databaseHandler.editProject(rg);
                if (check){
                    label.setText("Project updated successfully!");
                    oblist.clear();
                    tableView();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    label.setText("Couldn't update the Project!");
                }
            }


            else{
                label.setText("You should select an Item");
            }


        });
        surface_add.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    DatabaseHandler databaseHandler = new DatabaseHandler();
                    String Name = surface_field.getText();
                    if (Name.equals("Name") || Name.isEmpty()){
                        label.setText("You should Enter Surface Condition!");
                    }
                    else {
                        boolean check = false;
                        Surface surface = new Surface(Name);
                        check = databaseHandler.addSurface(surface);

                        if (check) {
                            surface_field.setText("Surface Condition");
                            label.setText("Surface added Successfully!");
                            oblist1.clear();
                            table2View();
                            FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), label);
                            fadeTransition.setFromValue(1f);
                            fadeTransition.setToValue(0f);
                            fadeTransition.setCycleCount(1);
                            fadeTransition.play();
                        } else {
                            label.setText("Surface Couldn't be added!");
                        }
                    }
                }

        );
        surface_delete.setOnAction(event ->{
            Surface surface = table1.getSelectionModel().getSelectedItem();
            if (!table1.getSelectionModel().isEmpty()){
                boolean check = databaseHandler.deleteSurface(surface);
                if (check){
                    label.setText("Surface deleted Successfully!");
                    oblist1.clear();
                    table2View();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    label.setText("Surface couldn't be deleted!");
                }
            }else{
                label.setText("You should select an Item");
            }
        });
        table1.setOnMouseClicked(event -> {
            if (!table1.getSelectionModel().isEmpty()){
                Surface surface = table1.getSelectionModel().getSelectedItem();
                surface_field.setText(surface.getName());
            }
        });
        surface_update.setOnAction(event -> {
            Surface surface = table1.getSelectionModel().getSelectedItem();
            if (!table1.getSelectionModel().isEmpty()){
                databaseHandler = new DatabaseHandler();
                Surface rg = new Surface( surface.getIdSurfaceCondition() , surface_field.getText()
                );
                boolean check = databaseHandler.editSurface(rg);
                if (check){
                    label.setText("Surface updated successfully!");
                    oblist1.clear();
                    table2View();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    label.setText("Couldn't update the Surface!");
                }
            }


            else{
                label.setText("You should select an Item");
            }


        });
        stage_add.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    DatabaseHandler databaseHandler = new DatabaseHandler();
                    String Name = stage_field.getText();
                    if (Name.equals("Stage") || Name.isEmpty()){
                        label.setText("You should Enter Stage Condition!");
                    }
                    else {
                        boolean check = false;
                        Stage stage = new Stage(Name);
                        check = databaseHandler.addStage(stage);

                        if (check) {
                            stage_field.setText("Stage");
                            label.setText("Stage added Successfully!");
                            oblist2.clear();
                            table3View();
                            FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), label);
                            fadeTransition.setFromValue(1f);
                            fadeTransition.setToValue(0f);
                            fadeTransition.setCycleCount(1);
                            fadeTransition.play();
                        } else {
                            label.setText("Stage Couldn't be added!");
                        }
                    }
                }

        );
        stage_delete.setOnAction(event ->{
            Stage stage = table11.getSelectionModel().getSelectedItem();
            if (!table11.getSelectionModel().isEmpty()){
                boolean check = databaseHandler.deleteStage(stage);
                if (check){
                    label.setText("Stage deleted Successfully!");
                    oblist2.clear();
                    table3View();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    label.setText("Stage couldn't be deleted!");
                }
            }else{
                label.setText("You should select an Item");
            }
        });
        table11.setOnMouseClicked(event -> {
            if (!table11.getSelectionModel().isEmpty()){
                Stage stage = table11.getSelectionModel().getSelectedItem();
                stage_field.setText(stage.getName());
            }
        });
        stage_update.setOnAction(event -> {
            Stage stage = table11.getSelectionModel().getSelectedItem();
            if (!table11.getSelectionModel().isEmpty()){
                databaseHandler = new DatabaseHandler();
                Stage rg = new Stage( stage.getIdStages() , stage_field.getText()
                );
                boolean check = databaseHandler.editStage(rg);
                if (check){
                    label.setText("Stage updated successfully!");
                    oblist2.clear();
                    table3View();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    label.setText("Couldn't update the Stage!");
                }
            }


            else{
                label.setText("You should select an Item");
            }


        });
    }

    public void tableView(){
        databaseHandler = new DatabaseHandler();
        try{
            ResultSet rs = databaseHandler.getTable("Projects");

            while (rs.next()) {

                oblist.add(new Project(rs.getInt("idProjects"),rs.getString("Name"))
                        );
            } } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        project_id.setCellValueFactory(new PropertyValueFactory<>("idProjects"));
        project_table.setCellValueFactory(new PropertyValueFactory<>("Name"));
        table.setItems(oblist);
    }
    public void table2View(){
        databaseHandler = new DatabaseHandler();
        try{
            ResultSet rs = databaseHandler.getTable("SurfaceCondition");

            while (rs.next()) {

                oblist1.add(new Surface(rs.getInt("idSurfaceCondition"),rs.getString("Name"))
                );
            } } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        surface_id.setCellValueFactory(new PropertyValueFactory<>("idSurfaceCondition"));
        surface_table.setCellValueFactory(new PropertyValueFactory<>("Name"));
        table1.setItems(oblist1);
    }
    public void table3View(){
        databaseHandler = new DatabaseHandler();
        try{
            ResultSet rs = databaseHandler.getTable("Stages");

            while (rs.next()) {

                oblist2.add(new Stage(rs.getInt("idStages"),rs.getString("Name"))
                );
            } } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        stage_id.setCellValueFactory(new PropertyValueFactory<>("idStages"));
        stage_table.setCellValueFactory(new PropertyValueFactory<>("Name"));
        table11.setItems(oblist2);
    }



}

