package controller;

import Database.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddUserController {
    Stage stage;
    Parent scene;
    String userType;

    @FXML
    private RadioButton adminRbtn;

    @FXML
    private Button backBtn;

    @FXML
    private TextField employeeNameTxt;

    @FXML
    private RadioButton generalRbtn;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private ToggleGroup toggle2;

    @FXML
    private TextField userIdTxt;

    @FXML
    private TextField userNameTxt;

    @FXML
    void clickBackBtn(ActionEvent event) throws IOException {
        stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        scene=(Parent) FXMLLoader.load(getClass().getResource("/view/userScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void clickSave(ActionEvent event) throws SQLException, IOException {
        String userName=userNameTxt.getText();
        String password=passwordTxt.getText();
        String employeeName=employeeNameTxt.getText();

        if(adminRbtn.isSelected()){
            userType= "admin";
        }
        else if(generalRbtn.isSelected()){
            userType="general";
        }

        if(userNameTxt.getText().isEmpty() || passwordTxt.getText().isEmpty() || employeeNameTxt.getText().isEmpty()){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty field");
            alert.setContentText("Please fill out all fields and try again");
            alert.showAndWait();
        }
        else{
            int rowsAffected= Helper.addUser(userName,password,employeeName,userType);
            if(rowsAffected>0){
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User added");
                alert.setContentText("The user was successfully added");
                alert.showAndWait();

                stage=(Stage)((Button)event.getSource()).getScene().getWindow();
                scene=(Parent) FXMLLoader.load(getClass().getResource("/view/userScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
    }
}
