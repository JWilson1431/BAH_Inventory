package controller;

import Database.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InjectableMed;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddMedicationController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private Button backBtn;

    @FXML
    private Label concentrationLbl;

    @FXML
    private TextField concentrationTxt;

    @FXML
    private RadioButton injRbtn;

    @FXML
    private TextField medIDTxt;

    @FXML
    private TextField medNameTxt;

    @FXML
    private RadioButton oralRbtn;

    @FXML
    private TextField quantityTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private Label sizeVialLbl;

    @FXML
    private TextField sizeVialTxt;

    @FXML
    private ToggleGroup toggleGroup1;

    @FXML
    void chooseInjRbtn(ActionEvent event) {
        concentrationLbl.setText("Concentration");
        sizeVialLbl.setText("Size of vial");
    }

    @FXML
    void chooseOralRbtn(ActionEvent event) {
        concentrationLbl.setText("Tablets in bottle");
        sizeVialLbl.setText("Strength of tablets");
    }

    @FXML
    void clickBackBtn(ActionEvent event) throws IOException {
        stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        scene= FXMLLoader.load(getClass().getResource("/view/MainScreenAdmin.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void clickSaveBtn(ActionEvent event) throws SQLException, IOException {
        String medName;
        int quantity;
        String concentration;
        int sizeOfVial;
        int tabInBottle;
        int strengthOfMed;


        if (medNameTxt.getText().isEmpty() || quantityTxt.getText().isEmpty() || concentrationTxt.getText().isEmpty() || sizeVialTxt.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty field");
            alert.setContentText("Fields cannot be empty. Please fill out all fields and try again");
            alert.showAndWait();
        }
        else if(injRbtn.isSelected()){
            medName = medNameTxt.getText().toString();
            quantity = Integer.parseInt(quantityTxt.getText().toString());
            concentration = concentrationTxt.getText().toString();
            sizeOfVial = Integer.parseInt(sizeVialTxt.getText().toString());
            int rowsAffected= Helper.addInjMed(medName,quantity,concentration,sizeOfVial);
            if(rowsAffected>0){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Medication added");
                alert.setContentText("The medication was successfully added");
                alert.showAndWait();

                //goes back to the injectable med screen once med is added
                stage=(Stage) ((Button)event.getSource()).getScene().getWindow();
                scene=(Parent) FXMLLoader.load(this.getClass().getResource("/view/injectableMed.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        else if(oralRbtn.isSelected()){
            medName = medNameTxt.getText().toString();
            quantity = Integer.parseInt(quantityTxt.getText().toString());
            tabInBottle = Integer.parseInt(concentrationTxt.getText().toString());
            strengthOfMed= Integer.parseInt(sizeVialTxt.getText().toString());
            int rowsAffected=Helper.addOralMed(medName,quantity,tabInBottle,strengthOfMed);
            if(rowsAffected>0){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Medication added");
                alert.setContentText("The medication was successfully added");
                alert.showAndWait();

                //goes back to the injectable med screen once med is added
                stage=(Stage) ((Button)event.getSource()).getScene().getWindow();
                scene=(Parent) FXMLLoader.load(this.getClass().getResource("/view/injectableMed.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }

    }

    public void initialize(URL url, ResourceBundle rb){
        medIDTxt.setDisable(true);
    }
}


