//Autohr: Mulham Alibrahim 170503111

package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.model.Bericht;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Bericht3Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainLogout;

    @FXML
    private Button table_back;

    @FXML
    private TextField deviations;

    @FXML
    private Button nextButton;

    @FXML
    private Label idLabel;

    @FXML
    private TextField desc;

    @FXML
    private TextField dates;

    @FXML
    private CheckBox grafik1;
    @FXML
    private Label label;
    @FXML
    private CheckBox grafik2;
    private SceneController sceneController;
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
        nextButton.setOnAction(event -> {
            if(grafik1.isSelected() && grafik2.isSelected())
                bericht.setGrafik(3);
            else if(grafik1.isSelected())
                bericht.setGrafik(1);
            else if(grafik2.isSelected())
                bericht.setGrafik(2);
            else
                bericht.setGrafik(0);

            if (!deviations.getText().isEmpty() && !dates.getText().isEmpty()){
            bericht.setStandard_deviations(deviations.getText());
            bericht.setInspection_dates(dates.getText());
            bericht.setDescription_and_attachments(desc.getText());
            nextButton.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/bericht4.fxml"));
                Parent root = (Parent) loader.load();
                Bericht4Controller bericht4 = loader.getController();
                bericht4.getBericht(bericht);
                Stage st = new Stage();
                st.setScene(new Scene(root));
                st.show();
            }catch  (IOException e) {
                e.printStackTrace();
            }} else
                label.setText("You should fill all required fields!");

        });
    }
}
