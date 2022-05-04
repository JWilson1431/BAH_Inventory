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
import model.OralMed;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OralMedController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private Button addMedBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<OralMed, Integer> concentrationCol;

    @FXML
    private Button editMedBtn;

    @FXML
    private TableView<OralMed> oralMedTable;

    @FXML
    private TableColumn<OralMed, Integer> medIdCol;

    @FXML
    private TableColumn<OralMed, String> nameCol;

    @FXML
    private TableColumn<OralMed, Integer> quantityCol;

    @FXML
    private TableColumn<OralMed, Integer> vialCol;

    @FXML
    void addMed(ActionEvent event) throws IOException {
        stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        scene=(Parent) FXMLLoader.load(getClass().getResource("/view/AddMedication.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        scene=(Parent) FXMLLoader.load(getClass().getResource("/view/MainScreenAdmin.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void editMed(ActionEvent event) throws IOException {
        if(oralMedTable.getSelectionModel().getSelectedItem()==null){
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
            EMC.sendMed(oralMedTable.getSelectionModel().getSelectedItem());

            stage=(Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene=loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    //sets the oral med table with the appropriate data
    public void setOralMeds(ObservableList<OralMed> allOralMeds){
        this.medIdCol.setCellValueFactory(new PropertyValueFactory("medicationId"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory("medName"));
        this.quantityCol.setCellValueFactory(new PropertyValueFactory("quantity"));
        this.concentrationCol.setCellValueFactory(new PropertyValueFactory("numTabletsInBottle"));
        this.vialCol.setCellValueFactory(new PropertyValueFactory("tabletStrength"));
        this.oralMedTable.setItems(allOralMeds);
    }

    public void initialize(URL url, ResourceBundle rb){
        try {
            setOralMeds(Helper.getAllOralMeds());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
