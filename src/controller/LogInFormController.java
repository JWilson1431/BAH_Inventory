package controller;

import Database.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LogInFormController {
    String userName;
    String password;
    Stage stage;
    Parent scene;

    @FXML
    private Button logInBtn;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField userNameTxt;

    @FXML
    void clickLogIn(ActionEvent event) throws SQLException, IOException {
        userName=userNameTxt.getText();
        password=passwordTxt.getText();

        boolean correctLogIn= Helper.checkCredentials(userName,password);

        //checks for empty username or password field
        if(userName.isEmpty() || password.isEmpty()) {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty username or password");
            alert.setContentText("A username and password must be entered.");
            alert.showAndWait();
        }
        //if correct log in is true and the username and password are verified, user is logged in
        else if(correctLogIn){
            stage=(Stage)((Button) event.getSource()).getScene().getWindow();
            scene= FXMLLoader.load(getClass().getResource("/view/MainScreenAdmin.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid login");
            alert.setContentText("The username or password was incorrect. Please try again");
            alert.showAndWait();
        }
    }



}


