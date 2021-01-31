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
import sample.model.Firm;
import sample.model.Job;
import sample.model.Offer;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FirmController {

    ObservableList<Firm> oblist = FXCollections.observableArrayList();
    ObservableList<Job> oblist1 = FXCollections.observableArrayList();
    ObservableList<Offer> oblist2 = FXCollections.observableArrayList();
    DatabaseHandler databaseHandler;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private JFXButton mainLogout;
    @FXML
    private JFXButton firm_back;
    @FXML
    private ImageView addFirm;
    @FXML
    private TableView<Firm> table;
    @FXML
    private TableColumn<Firm, String> firm_name_table;
    @FXML
    private TableColumn<Firm, String> firm_city_table;
    @FXML
    private JFXButton deleteFirm;
    @FXML
    private Label firm_label;
    @FXML
    private JFXTextField firm_name;
    @FXML
    private JFXTextField firm_city;
    @FXML
    private JFXButton updateFirm;
    @FXML
    private TableView<Job> table1;
    @FXML
    private TableColumn<Job, Double> job_table;
    @FXML
    private TableView<Offer> table11;
    @FXML
    private TableColumn<Offer, Double> offer_table;
    @FXML
    private JFXTextField job;
    @FXML
    private ImageView addOffer;
    @FXML
    private JFXButton deleteOffer;
    @FXML
    private JFXButton updateOffer;
    @FXML
    private JFXTextField offer;
    @FXML
    private ImageView addJob;
    @FXML
    private JFXButton deleteJob;
    @FXML
    private JFXButton updateJob;
    private SceneController sceneController;

    @FXML
    void initialize() {
        sceneController = new SceneController();

        mainLogout.setOnAction(event -> {
            sceneController.changeScene(mainLogout, "login.fxml");
        });
        firm_back.setOnAction(event -> {
            sceneController.changeScene(firm_back, "main.fxml");
        });


        addFirm.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

                    DatabaseHandler databaseHandler = new DatabaseHandler();
                    String Name = firm_name.getText();
                    String City = firm_city.getText();
                    if (Name.equals("Name") || City.equals("City") || Name.isEmpty() || City.isEmpty()) {
                        firm_label.setText("You should Enter Firm Data!");
                    } else {
                        boolean check = false;
                        Firm firm = new Firm(Name, City);
                        check = databaseHandler.addFirm(firm);

                        if (check) {
                            firm_name.setText("Name");
                            firm_city.setText("City");
                            firm_label.setText("Firm added Successfully!");
                            oblist.clear();
                            tableView();
                            FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), firm_label);
                            fadeTransition.setFromValue(1f);
                            fadeTransition.setToValue(0f);
                            fadeTransition.setCycleCount(1);
                            fadeTransition.play();
                        } else {
                            firm_label.setText("Firm Couldn't be added!");
                        }
                    }
                }

        );
        addJob.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!table.getSelectionModel().isEmpty()) {
                Firm firm = table.getSelectionModel().getSelectedItem();
                int id = firm.getId();


                DatabaseHandler databaseHandler = new DatabaseHandler();
                double No = Double.parseDouble(job.getText());
                boolean check = false;
                Job jobb = new Job(No);
                check = databaseHandler.addJob(jobb, id);

                if (check) {
                    job.setText("Job Order No");
                    firm_label.setText("Job added Successfully!");
                    oblist1.clear();
                    table2View(id);
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), firm_label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                } else {
                    firm_label.setText("Job Couldn't be added!");
                }
            } else {
                firm_label.setText("You should select a firm!");
            }


        });
        addOffer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!table.getSelectionModel().isEmpty()) {
                Firm firm = table.getSelectionModel().getSelectedItem();
                int id = firm.getId();


                DatabaseHandler databaseHandler = new DatabaseHandler();
                double No = Double.parseDouble(offer.getText());
                boolean check = false;
                Offer offerr = new Offer(No);
                check = databaseHandler.addOffer(offerr, id);

                if (check) {
                    offer.setText("Offer No");
                    firm_label.setText("Offer added Successfully!");
                    oblist2.clear();
                    table3View(id);
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), firm_label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                } else {
                    firm_label.setText("Offer Couldn't be added!");
                }
            } else {
                firm_label.setText("You should select a firm!");
            }


        });
        deleteOffer.setOnAction(event -> {
            Offer offer = table11.getSelectionModel().getSelectedItem();
            if (!table11.getSelectionModel().isEmpty()) {
                boolean check = databaseHandler.deleteOffer(offer);
                if (check) {
                    firm_label.setText("Offer deleted Successfully!");
                    oblist2.clear();
                    table3View(offer.getFirma());
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), firm_label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                } else {
                    firm_label.setText("Offer couldn't be deleted!");
                }
            } else {
                firm_label.setText("You should select an Item");
            }
        });
        deleteJob.setOnAction(event -> {
            Job job = table1.getSelectionModel().getSelectedItem();
            if (!table1.getSelectionModel().isEmpty()) {
                boolean check = databaseHandler.deleteJob(job);
                if (check) {
                    firm_label.setText("Job deleted Successfully!");
                    oblist1.clear();
                    table2View(job.getFirma());
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), firm_label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                } else {
                    firm_label.setText("Job couldn't be deleted!");
                }
            } else {
                firm_label.setText("You should select an Item");
            }
        });

        table1.setOnMouseClicked(event -> {
            if (!table1.getSelectionModel().isEmpty()) {
                Job jobb = table1.getSelectionModel().getSelectedItem();
                job.setText(String.valueOf(jobb.getNo()));

            }
        });
        table11.setOnMouseClicked(event -> {
            if (!table11.getSelectionModel().isEmpty()) {
                Offer offerr = table11.getSelectionModel().getSelectedItem();
                offer.setText(String.valueOf(offerr.getNo()));

            }
        });
        updateJob.setOnAction(event -> {
            Job jobb = table1.getSelectionModel().getSelectedItem();
            if (!table1.getSelectionModel().isEmpty()) {
                databaseHandler = new DatabaseHandler();
                Job rg = new Job(jobb.getIdJobOrder(), Double.parseDouble(job.getText()), jobb.getFirma());
                boolean check = databaseHandler.editJob(rg);
                if (check) {
                    firm_label.setText("Job updated successfully!");
                    oblist1.clear();
                    table2View(jobb.getFirma());
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), firm_label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                } else {
                    firm_label.setText("Couldn't update the Job!");
                }
            } else {
                firm_label.setText("You should select an Item");
            }


        });
        updateOffer.setOnAction(event -> {
            Offer offerr = table11.getSelectionModel().getSelectedItem();
            if (!table11.getSelectionModel().isEmpty()) {
                databaseHandler = new DatabaseHandler();
                Offer rg = new Offer(offerr.getIdOffer(), Double.parseDouble(offer.getText()), offerr.getFirma());
                boolean check = databaseHandler.editOffer(rg);
                if (check) {
                    firm_label.setText("Offer updated successfully!");
                    oblist2.clear();
                    table3View(offerr.getFirma());
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), firm_label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                } else {
                    firm_label.setText("Couldn't update the Offer!");
                }
            } else {
                firm_label.setText("You should select an Item");
            }


        });
        deleteFirm.setOnAction(event -> {
            Firm firm = table.getSelectionModel().getSelectedItem();
            if (!table.getSelectionModel().isEmpty()) {
                boolean check = databaseHandler.deleteFirm(firm);
                if (check) {
                    firm_label.setText("Firm deleted Successfully!");
                    oblist.clear();
                    tableView();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), firm_label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                } else {
                    firm_label.setText("Firm couldn't be deleted!");
                }
            } else {
                firm_label.setText("You should select an Item");
            }
        });
        table.setOnMouseClicked(event -> {
            if (!table.getSelectionModel().isEmpty()) {
                Firm firm = table.getSelectionModel().getSelectedItem();
                firm_name.setText(firm.getName());
                firm_city.setText(firm.getStadt());
                oblist1.clear();
                table2View(firm.getId());
                oblist2.clear();
                table3View(firm.getId());

            }
        });
        updateFirm.setOnAction(event -> {


            Firm firm = table.getSelectionModel().getSelectedItem();
            if (!table.getSelectionModel().isEmpty()) {


                databaseHandler = new DatabaseHandler();
                Firm rg = new Firm(firm.getId(), firm_name.getText(), firm_city.getText()
                );
                boolean check = databaseHandler.editFirm(rg);
                if (check) {
                    firm_label.setText("Firm updated successfully!");
                    oblist.clear();
                    tableView();
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000), firm_label);
                    fadeTransition.setFromValue(1f);
                    fadeTransition.setToValue(0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                } else {
                    firm_label.setText("Couldn't update the Firm!");
                }
            } else {
                firm_label.setText("You should select an Item");
            }


        });
    }

    public void tableView() {
        databaseHandler = new DatabaseHandler();
        try {
            ResultSet rs = databaseHandler.getTable("Firmen");


            while (rs.next()) {

                oblist.add(new Firm(rs.getInt("idFirmen"), rs.getString("Name"), rs.getString("Stadt")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        firm_name_table.setCellValueFactory(new PropertyValueFactory<>("Name"));
        firm_city_table.setCellValueFactory(new PropertyValueFactory<>("Stadt"));
        table.setItems(oblist);
    }

    public void table2View(int id) {
        databaseHandler = new DatabaseHandler();


        try {
            ResultSet rs = databaseHandler.getSelect("JobOrder", id, "Firma");


            while (rs.next()) {
                oblist1.add(new Job(rs.getInt("idJobOrder"),rs.getDouble("No"), rs.getInt("Firma"))
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        job_table.setCellValueFactory(new PropertyValueFactory<>("No"));
        table1.setItems(oblist1);
    }
    public void table3View(int id) {
        databaseHandler = new DatabaseHandler();


        try {
            ResultSet rs = databaseHandler.getSelect("Offer", id, "Firma");


            while (rs.next()) {
                oblist2.add(new Offer(rs.getInt("idOffer"),rs.getDouble("No"), rs.getInt("Firma"))
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        offer_table.setCellValueFactory(new PropertyValueFactory<>("No"));
        table11.setItems(oblist2);
    }
}
