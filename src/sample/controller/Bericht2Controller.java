//Autohr: Mulham Alibrahim 170503111

package sample.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Bericht;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Bericht2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainLogout;

    @FXML
    private Button table_back;

    @FXML
    private TextField poleDistance;

    @FXML
    private Button nextButton;

    @FXML
    private Label idLabel;

    @FXML
    private TextField meg;

    @FXML
    private TextField exam_area;

    @FXML
    private TextField lux;

    @FXML
    private TextField surface_temp;

    @FXML
    private TextField equipment;

    @FXML
    private TextArea mp;

    @FXML
    private TextField uv;

    @FXML
    private TextField light;

    @FXML
    private RadioButton type_ac;

    @FXML
    private ToggleGroup Type;

    @FXML
    private RadioButton type_dc;

    @FXML
    private TextField test_medium;

    @FXML
    private TextField Demagnetization;

    @FXML
    private TextField heat;

    @FXML
    private TextField gauss;

    @FXML
    private TextField surface_condition;

    @FXML
    private TextField id_light;
    @FXML
    private Label label;
    @FXML
    private TextField lifting;
    private SceneController sceneController;
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

    }
    public void getBericht(Bericht bericht){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try {
            ResultSet rrs =  databaseHandler.getSelect("Geraeten", bericht.getEquipment(), "idGeraeten" );
            while(rrs.next()){
                poleDistance.setText(rrs.getString("pole_distance"));
                equipment.setText(rrs.getString("Name"));
                mp.setText(rrs.getString("mp_carrier_medium"));
                meg.setText(rrs.getString("mag_tech"));
                uv.setText(rrs.getString("uv_light_intensity"));
                light.setText(rrs.getString("distance_of_light"));



            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        nextButton.setOnAction(event -> {
            if (!poleDistance.getText().isEmpty() && !equipment.getText().isEmpty()
            && !mp.getText().isEmpty() && !meg.getText().isEmpty()
            && !uv.getText().isEmpty() && !light.getText().isEmpty() && !exam_area.getText().isEmpty()
            && !lux.getText().isEmpty() && !surface_temp.getText().isEmpty() && !gauss.getText().isEmpty()
            && !surface_condition.getText().isEmpty() && !id_light.getText().isEmpty() && !lifting.getText().isEmpty()
            && Type.getSelectedToggle() != null){
            bericht.setPole_distance(poleDistance.getText());

            bericht.setMp(mp.getText());
            bericht.setMag(meg.getText());
            bericht.setUv(uv.getText());
            bericht.setDistance_of_light(light.getText());
            bericht.setExamination_area(exam_area.getText());
                RadioButton selectedRadioButton = (RadioButton) Type.getSelectedToggle();
                String toogleGroupValue = selectedRadioButton.getText();
                bericht.setCurrent_type(toogleGroupValue);

            bericht.setLuxmeter(lux.getText());
            bericht.setTest_medium(test_medium.getText());
            bericht.setDemagnetization(Demagnetization.getText());
            bericht.setHeat_treatment(heat.getText());
            bericht.setSurface_temp(surface_temp.getText());
            bericht.setGauss(gauss.getText());
            bericht.setSurface_condition2(surface_condition.getText());
            bericht.setId_light(id_light.getText());
            bericht.setLifting_test(lifting.getText());
            nextButton.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/bericht3.fxml"));
                Parent root = (Parent) loader.load();
                Bericht3Controller bericht3 = loader.getController();
                bericht3.getBericht(bericht);
                Stage st = new Stage();
                st.setScene(new Scene(root));
                st.show();
            }catch  (IOException e){
                e.printStackTrace();
            } } else
                    label.setText("You should fill all required fields!");
        });
    }
}
