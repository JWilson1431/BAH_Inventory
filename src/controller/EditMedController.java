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
import model.Medication;
import model.OralMed;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditMedController implements Initializable {
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
       // concentrationLbl.setText("");
    }

    @FXML
    void chooseOralRbtn(ActionEvent event) {

    }

    @FXML
    void clickBackBtn(ActionEvent event) throws IOException {
        stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        scene=(Parent) FXMLLoader.load(getClass().getResource("/view/MainScreenAdmin.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void clickSaveBtn(ActionEvent event) throws SQLException, IOException {
        if(injRbtn.isSelected()) {
            int medId = Integer.parseInt(medIDTxt.getText());
            String name = medNameTxt.getText();
            int quantity = Integer.parseInt(quantityTxt.getText());
            String concentration = concentrationTxt.getText();
            int sizeVial = Integer.parseInt(sizeVialTxt.getText());

            int rowsAffected = Helper.updateInjMed(name, quantity, concentration, sizeVial, medId);

            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Medication updated");
                alert.setContentText("The medication was successfully updated");
                alert.showAndWait();

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = (Parent) FXMLLoader.load(getClass().getResource("/view/injectableMed.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
            else{
                int medId1= Integer.parseInt(medIDTxt.getText());
                String name1=medNameTxt.getText();
                int quantity1= Integer.parseInt(quantityTxt.getText());
                int tabInBottle= Integer.parseInt(concentrationTxt.getText());
                int strength=Integer.parseInt(sizeVialTxt.getText());

                int rowsAffected1=Helper.updateOralMed(name1,quantity1,tabInBottle,strength,medId1);

                if(rowsAffected1>0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Medication updated");
                    alert.setContentText("The medication was successfully updated");
                    alert.showAndWait();

                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = (Parent) FXMLLoader.load(getClass().getResource("/view/oralMed.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }


    }


    //method to send the info about the med you want to edit to the edit screen
    public void sendMed(Medication medication){
        medIDTxt.setText(String.valueOf(medication.getMedicationId()));
        medNameTxt.setText(medication.getMedName());
        quantityTxt.setText(String.valueOf(medication.getQuantity()));

        if(medication instanceof InjectableMed){
            injRbtn.setSelected(true);
            oralRbtn.setDisable(true);

            concentrationTxt.setText(((InjectableMed) medication).getConcentration());
            sizeVialTxt.setText(String.valueOf(((InjectableMed) medication).getSizeOfVial()));
        }

        else{
            oralRbtn.setSelected(true);
            injRbtn.setDisable(true);
            concentrationLbl.setText("Tablets in bottle");
            sizeVialLbl.setText("Strength of med");

            concentrationTxt.setText(String.valueOf(((OralMed)medication).getNumTabletsInBottle()));
            sizeVialTxt.setText(String.valueOf(((OralMed)medication).getTabletStrength()));
        }
    }

    public void initialize(URL url, ResourceBundle rb){

        medIDTxt.setDisable(true);
    }
}
