//Autohr: Mulham Alibrahim 170503111

package sample.controller;


import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Database.DatabaseHandler;
import sample.model.Bericht;
import sample.model.Result;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Bericht4Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainLogout;

    @FXML
    private Button table_back;

    @FXML
    private TableView<Result> table;

    @FXML
    private TableColumn<Result, Integer> serial_no;

    @FXML
    private TableColumn<Result, String> weld;

    @FXML
    private TableColumn<Result, Integer> legnth;

    @FXML
    private TableColumn<Result, String> welding_process;

    @FXML
    private TableColumn<Result, Integer> thickness;

    @FXML
    private TableColumn<Result, String> diameter;

    @FXML
    private TableColumn<Result, String> defect_type;

    @FXML
    private TableColumn<Result, String> defect_loc;
    @FXML
    private TableColumn<Result, String> result_table;
    @FXML
    private ToggleGroup result;

    @FXML
    private TextField weld_field;

    @FXML
    private TextField length_field;

    @FXML
    private TextField welding_field;

    @FXML
    private TextField thickness_field;

    @FXML
    private TextField diameter_field;

    @FXML
    private TextField defect_type_field;

    @FXML
    private TextField defect_loc_field;

    @FXML
    private RadioButton result_field_ok;

    @FXML
    private RadioButton result_field_red;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;
    @FXML
    private Label label;
    @FXML
    private Button nextButton;
    private SceneController sceneController;
    ObservableList<Result> oblist = FXCollections.observableArrayList();
    DatabaseHandler databaseHandler;
    @FXML
    void initialize() {
        sceneController = new SceneController();

        mainLogout.setOnAction(event -> {
            sceneController.changeScene(mainLogout, "login.fxml");
        });
        table_back.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Report data will be deleted if you didn't save it", ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK)
            sceneController.changeScene(table_back, "main.fxml");
        });
        table.setOnMouseClicked(event -> {
            if (!table.getSelectionModel().isEmpty()){
                Result result = table.getSelectionModel().getSelectedItem();
                weld_field.setText(result.getWeld());
                length_field.setText(String.valueOf(result.getLength()));

                welding_field.setText(result.getWelding_process());

                thickness_field.setText(String.valueOf(result.getThickness()));
                diameter_field.setText(result.getDiameter());
                defect_type_field.setText(result.getDefect_type());
                defect_loc_field.setText(result.getDefect_loc());
                if (result_table.getText().equals("OK")){
                    result_field_ok.setSelected(true);
                }else if (result_table.getText().equals("RED")){
                    result_field_red.setSelected(true);
                }

            }
        });
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    DatabaseHandler databaseHandler = new DatabaseHandler();
            int id = 0;
            try {
                id = databaseHandler.getLastId("Berichten");    // TODO +1
            } catch (SQLException throwables) {

            }
            if (result.getSelectedToggle() != null && !weld_field.getText().isEmpty()
            && !welding_field.getText().isEmpty() && !length_field.getText().isEmpty() && !thickness_field.getText().isEmpty()) {
                String weld = weld_field.getText();
                    int length = Integer.parseInt(length_field.getText());
                    String welding = welding_field.getText();
                    int thinkness = Integer.parseInt(thickness_field.getText());
                    String diameter = diameter_field.getText();
                    String defect_type = defect_type_field.getText();
                    String defect_loc= defect_loc_field.getText();
                RadioButton selectedRadioButton = (RadioButton) result.getSelectedToggle();
                String the_result = selectedRadioButton.getText();
                if(the_result.equals("RED") && (defect_type_field.getText().isEmpty() || defect_loc_field.getText().isEmpty())){
                    label.setText("Defect type and Loc. required!");
                }else{

                boolean check = false;
                Result re = new Result(id, weld, welding, length, thinkness, diameter, defect_type, defect_loc, the_result);
                check = databaseHandler.addResult(re);

                if (check) {

                    label.setText("Result added Successfully!");
                    oblist.clear();
                    tableView(id);
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                } else {
                    label.setText("Result Couldn't be added!");
                }}
            } else
                label.setText("You should fill all required fields!");
                }
        );
        updateButton.setOnAction(event -> {
            if (!table.getSelectionModel().isEmpty()){
                Result rs = table.getSelectionModel().getSelectedItem();

                databaseHandler = new DatabaseHandler();
                String weld = weld_field.getText();
                int length = Integer.parseInt(length_field.getText());
                String welding = welding_field.getText();
                int thinkness = Integer.parseInt(thickness_field.getText());
                String diameter = diameter_field.getText();
                String defect_type = defect_type_field.getText();
                String defect_loc= defect_loc_field.getText();
                RadioButton selectedRadioButton = (RadioButton) result.getSelectedToggle();
                String the_result = selectedRadioButton.getText();

                boolean check = false;
                Result re = new Result(rs.getIdResults(),rs.getBericht(), weld, welding, length, thinkness, diameter, defect_type, defect_loc, the_result);
                check = databaseHandler.editResult(re);
                if (check){
                    label.setText("Result updated successfully!");
                    oblist.clear();
                    tableView(rs.getBericht());
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    label.setText("Couldn't update the Result!");
                }
            }


            else{
                label.setText("You should select an Item");
            }


        });
        deleteButton.setOnAction(event ->{
            Result rs = table.getSelectionModel().getSelectedItem();
            if (!table.getSelectionModel().isEmpty()){
                boolean check = databaseHandler.deleteResult(rs);
                if (check){
                    label.setText("Result deleted Successfully!");
                    oblist.clear();
                    tableView(rs.getBericht());
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    label.setText("Result couldn't be deleted!");
                }
            }else{
                label.setText("You should select an Item");
            }
        });
    }
    public void tableView(int id) {
        databaseHandler = new DatabaseHandler();
        int i = 0;
        try {
            ResultSet rs = databaseHandler.getSelect("Results", id, "Bericht");
            while (rs.next()) {
                i++;
                oblist.add(new Result(rs.getInt("idResults"), rs.getInt("Bericht"), rs.getString("weld")
                        , rs.getString("welding_process"), rs.getInt("length"), rs.getInt("thickness"),
                        rs.getString("diameter"), rs.getString("defect_type"),rs.getString("defect_loc"),
                        rs.getString("result")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
           weld.setCellValueFactory(new PropertyValueFactory<>("weld"));
        legnth.setCellValueFactory(new PropertyValueFactory<>("length"));
        welding_process.setCellValueFactory(new PropertyValueFactory<>("welding_process"));
        thickness.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        diameter.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        defect_type.setCellValueFactory(new PropertyValueFactory<>("defect_type"));
        defect_loc.setCellValueFactory(new PropertyValueFactory<>("defect_loc"));
        result_table.setCellValueFactory(new PropertyValueFactory<>("result"));
        table.setItems(oblist);
    }
    public void getBericht(Bericht bericht){
        nextButton.setOnAction(event -> {

            nextButton.getScene().getWindow().hide();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/bericht5.fxml"));
            Parent root = (Parent) loader.load();
            Bericht5Controller bericht5 = loader.getController();
            bericht5.getBericht(bericht);
            Stage st = new Stage();
            st.setScene(new Scene(root));
            st.show();
        }catch  (IOException e){
            e.printStackTrace();
        }
        });
    }

}
