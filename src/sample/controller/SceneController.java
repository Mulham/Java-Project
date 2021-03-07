//Autohr: Mulham Alibrahim 170503111


package sample.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    public void changeScene(Button button, String scene ){
        button.getScene().getWindow().hide();
        FXMLLoader loader   = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/" + scene));
        try{
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void changeSceneImage(ImageView image, String scene ){
        image.getScene().getWindow().hide();
        FXMLLoader loader   = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/" + scene));
        try{
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
