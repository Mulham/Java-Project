//Autohr: Mulham Alibrahim 170503111


package sample.controller;


import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class UserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainLogout;

    @FXML
    private Button table_back;

    @FXML
    private ImageView addUser;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, Integer> tableId;
    @FXML
    private TableColumn<User, String> table_name;

    @FXML
    private TableColumn<User, String> table_surname;

    @FXML
    private TableColumn<User, String> table_level;
    @FXML
    private TableColumn<User, Date> table_date;
    @FXML
    private Button user_delete;

    @FXML
    private Button user_edit;
    @FXML
    private Label radiographer_label;
    @FXML
    private TableColumn<User, String> table_pass;
    private SceneController sceneController;
    ObservableList<User> oblist = FXCollections.observableArrayList();
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


        addUser.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker buttonShaker = new Shaker(addUser);
            buttonShaker.shake();
            sceneController.changeSceneImage(addUser, "addUser.fxml");
                }

                );
        user_delete.setOnAction(event ->{
            User user = table.getSelectionModel().getSelectedItem();
            if (!table.getSelectionModel().isEmpty()){
                boolean check = databaseHandler.deleteUser(user);
                if (check){
                    radiographer_label.setText("User deleted Successfully!");
                    oblist.clear();
                    tableView();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),radiographer_label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                }
                else{
                    radiographer_label.setText("User couldn't be deleted!");
                }
            }else{
                radiographer_label.setText("You should select an Item");
            }
        });
        user_edit.setOnAction(event -> {
            User user = table.getSelectionModel().getSelectedItem();
            if (!table.getSelectionModel().isEmpty()){
                user_edit.getScene().getWindow().hide();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/editUser.fxml"));
                    Parent root = (Parent) loader.load();
                    EditUserController editUserController = loader.getController();
                    editUserController.getInfo(user);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }catch  (IOException e){
                    e.printStackTrace();
                }
            }
            else{
                radiographer_label.setText("You should select an Item");
            }


        });
    }

    public void tableView(){
        databaseHandler = new DatabaseHandler();
        try{
            ResultSet rs = databaseHandler.getTable("Users");


            while (rs.next()) {

                oblist.add(new User(rs.getInt("idUser"),rs.getString("Name"), rs.getString("Nachname")
                        , rs.getString("Level"),rs.getDate("Zertifikat_datum").toLocalDate(), rs.getString("password")));
            } } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tableId.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        table_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        table_surname.setCellValueFactory(new PropertyValueFactory<>("Nachname"));
        table_level.setCellValueFactory(new PropertyValueFactory<>("Level"));
        table_date.setCellValueFactory(new PropertyValueFactory<>("Zertifikat_datum"));
        table_pass.setCellValueFactory(new PropertyValueFactory<>("password"));
        table.setItems(oblist);
    }





    }

