package controller;

import Database.Helper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InjectableMed;
import model.Medication;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InjectableMedController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private Button addMedBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<InjectableMed, String> concentrationCol;

    @FXML
    private Button editMedBtn;

    @FXML
    private TableView<InjectableMed> injMedTable;

    @FXML
    private TableColumn<InjectableMed, Integer> medIdCol;

    @FXML
    private TableColumn<InjectableMed, String> nameCol;

    @FXML
    private TableColumn<InjectableMed, Integer> quantityCol;

    @FXML
    private TableColumn<InjectableMed, Integer> vialCol;

    @FXML
    void addMed(ActionEvent event) throws IOException {
        stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        scene=(Parent) FXMLLoader.load(getClass().getResource("/view/AddMedication.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        //sends the user back to the main admin screen if they are an admin
        if (Helper.getCurrentUser().getUserType().equals("Admin")) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = (Parent) FXMLLoader.load(getClass().getResource("/view/MainScreenAdmin.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        //otherwise sends the user back to the general main screen
        else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = (Parent) FXMLLoader.load(getClass().getResource("/view/mainScreenGeneral.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void editMed(ActionEvent event) throws IOException {
        if(injMedTable.getSelectionModel().getSelectedItem()==null){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Medication not selected");
            alert.setContentText("Please select the medication you wish to edit and try again.");
            alert.showAndWait();
        }
        else{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateMed.fxml"));
            loader.load();
            EditMedController EMC = loader.getController();
            EMC.sendMed(injMedTable.getSelectionModel().getSelectedItem());

            stage=(Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene=loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    //sets the injectable med table with the appropriate data
    public void setInjectableMeds(ObservableList<InjectableMed> allInjMeds){
        this.medIdCol.setCellValueFactory(new PropertyValueFactory("medicationId"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory("medName"));
        this.quantityCol.setCellValueFactory(new PropertyValueFactory("quantity"));
        this.concentrationCol.setCellValueFactory(new PropertyValueFactory("concentration"));
        this.vialCol.setCellValueFactory(new PropertyValueFactory("sizeOfVial"));
        this.injMedTable.setItems(allInjMeds);
    }

    public void initialize(URL url, ResourceBundle rb){
        try {
            setInjectableMeds(Helper.getAllInjMeds());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}

