package controller;

import Database.Helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InjectableMed;
import model.Medication;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class InjectableMedController implements Initializable {
    Stage stage;
    Parent scene;
    private static ObservableList<InjectableMed> allInjMeds= FXCollections.observableArrayList();

    @FXML
    private Button addMedBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<InjectableMed, String> concentrationCol;

    @FXML
    private Button editMedBtn;

    @FXML
    private Button deleteMedBtn;

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


    @FXML
    void clickDeleteMed(ActionEvent event) throws SQLException, IOException {
        //checks to see if a med is selected
        InjectableMed medToDelete=injMedTable.getSelectionModel().getSelectedItem();
        int medToDeleteId=medToDelete.getMedicationId();
        if(injMedTable.getSelectionModel().getSelectedItem()==null){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Medication not selected");
            alert.setContentText("Please select a medication to delete and try again");
            alert.showAndWait();
        }
        else{
            int rowsAffected=Helper.deleteInjMed(medToDeleteId);
            if(rowsAffected>0){
                Alert alert= new Alert(Alert.AlertType.CONFIRMATION, "This will permanently delete this medication, do you wish to proceed?");
                Optional<ButtonType> result = alert.showAndWait();

                //deletes the user if confirmation is received
                if(result.isPresent() && result.get()==ButtonType.OK){
                    allInjMeds.remove(medToDelete);
                    Alert alert1= new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Medication deleted");
                    alert1.setContentText("The medication was successfully deleted");
                    alert1.showAndWait();
                }
                //refreshes the page once user is deleted
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/injectableMed.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
    }

    public void initialize(URL url, ResourceBundle rb){
        try {
            setInjectableMeds(Helper.getAllInjMeds());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}

