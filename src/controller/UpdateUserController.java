package controller;

import Database.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateUserController {
    Stage stage;
    Parent scene;
    String employeeType;


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
        int userId=Integer.parseInt(userIdTxt.getText());
        String name=userNameTxt.getText();
        String password=passwordTxt.getText();
        String employeeName=employeeNameTxt.getText();

        if(adminRbtn.isSelected()){
            employeeType="Admin";
        }
        else{
            employeeType="General";
        }
         int rowsAffected= Helper.updateUser(name,password,employeeName,employeeType,userId);

        if(rowsAffected>0){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User updated");
            alert.setContentText("The user was successfully updated");
            alert.showAndWait();

            stage=(Stage)((Button)event.getSource()).getScene().getWindow();
            scene=(Parent) FXMLLoader.load(getClass().getResource("/view/userScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    public void sendUser(User user){
        userIdTxt.setText(String.valueOf(user.getUserId()));
        userNameTxt.setText(user.getUserName());
        passwordTxt.setText(user.getPassword());
        employeeNameTxt.setText(user.getEmployeeName());

        if(user.getUserType().equals("Admin")){
            adminRbtn.setSelected(true);
        }
        else{
            generalRbtn.setSelected(true);
        }
    }
}
