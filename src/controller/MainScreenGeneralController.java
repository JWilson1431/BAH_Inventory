package controller;

import Database.Helper;
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
import model.OralMed;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenGeneralController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private Button exitBtn;

    @FXML
    private Button goToInjectableBtn;

    @FXML
    private Button goToOralMedBtn;

    @FXML
    private TableColumn<InjectableMed, String> injMedConcentrationCol;

    @FXML
    private TableColumn<InjectableMed, Integer> injMedIdCol;

    @FXML
    private TableColumn<InjectableMed, String> injMedNameCol;

    @FXML
    private TableColumn<InjectableMed, Integer> injMedQuantityCol;

    @FXML
    private TableView<InjectableMed> injMedTable;

    @FXML
    private TableColumn<InjectableMed, Integer> injMedVialSizeCol;

    @FXML
    private TableColumn<OralMed, Integer> oralMedIdCol;

    @FXML
    private TableColumn<OralMed, String> oralMedNameCol;

    @FXML
    private TableColumn<OralMed, Integer> oralMedNumInBottleCol;

    @FXML
    private TableColumn<OralMed, Integer> oralMedQuantityCol;

    @FXML
    private TableColumn<OralMed, Integer> oralMedTabStrengthCol;

    @FXML
    private TableView<OralMed> oralMedTable;

    @FXML
    void exitApp(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close the application?", new ButtonType[0]);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            System.exit(0);
        }
    }

    @FXML
    void goToInjectableMed(ActionEvent event) throws IOException {
        stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        scene=(Parent) FXMLLoader.load(getClass().getResource("/view/injectableMed.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void goToOralMed(ActionEvent event) throws IOException {
        stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        scene=(Parent) FXMLLoader.load(getClass().getResource("/view/oralMed.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    //sets the injectable med table with the appropriate data
    public void setInjectableMeds(ObservableList<InjectableMed> allInjMeds){
        this.injMedIdCol.setCellValueFactory(new PropertyValueFactory("medicationId"));
        this.injMedNameCol.setCellValueFactory(new PropertyValueFactory("medName"));
        this.injMedQuantityCol.setCellValueFactory(new PropertyValueFactory("quantity"));
        this.injMedConcentrationCol.setCellValueFactory(new PropertyValueFactory("concentration"));
        this.injMedVialSizeCol.setCellValueFactory(new PropertyValueFactory("sizeOfVial"));
        this.injMedTable.setItems(allInjMeds);
    }

    //sets the oral med table with the appropriate data
    public void setOralMeds(ObservableList<OralMed> allOralMeds){
        this.oralMedIdCol.setCellValueFactory(new PropertyValueFactory("medicationId"));
        this.oralMedNameCol.setCellValueFactory(new PropertyValueFactory("medName"));
        this.oralMedQuantityCol.setCellValueFactory(new PropertyValueFactory("quantity"));
        this.oralMedNumInBottleCol.setCellValueFactory(new PropertyValueFactory("numTabletsInBottle"));
        this.oralMedTabStrengthCol.setCellValueFactory(new PropertyValueFactory("tabletStrength"));
        this.oralMedTable.setItems(allOralMeds);
    }

    public void initialize(URL url, ResourceBundle rb){
        try{

            this.setInjectableMeds(Helper.getAllInjMeds());
            this.setOralMeds(Helper.getAllOralMeds());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
