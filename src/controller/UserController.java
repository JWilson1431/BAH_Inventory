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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    Stage stage;
    Parent scene;

    private static ObservableList<User> allUsers= FXCollections.observableArrayList();


    @FXML
    private Button addUserBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button deleteUserBtn;

    @FXML
    private Button editUserBtn;

    @FXML
    private TableColumn<User, String> employeeNameCol;

    @FXML
    private TableColumn<User, String> passwordCol;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    private TableColumn<User, String> userNameCol;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> userTypeCol;

    @FXML
    void clickAddUser(ActionEvent event) throws IOException {
        stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        scene=(Parent) FXMLLoader.load(getClass().getResource("/view/AddUser.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void clickBackToMainBtn(ActionEvent event) throws IOException {
        stage=(Stage)((Button)event.getSource()).getScene().getWindow();
        scene=(Parent) FXMLLoader.load(getClass().getResource("/view/MainScreenAdmin.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void clickDeleteUser(ActionEvent event) throws SQLException, IOException {
        //checks to see if a user is selected
        User userToDelete=userTable.getSelectionModel().getSelectedItem();
        int userToDeleteId=userToDelete.getUserId();
        if(userTable.getSelectionModel().getSelectedItem()==null){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User not selected");
            alert.setContentText("Please select a user to delete and try again");
            alert.showAndWait();
        }
        else{

            int rowsAffected=Helper.deleteUser(userToDeleteId);
            if(rowsAffected>0){
                Alert alert= new Alert(Alert.AlertType.CONFIRMATION, "This will permanently delete this user, do you wish to proceed?");
                Optional<ButtonType> result = alert.showAndWait();

                //deletes the user if confirmation is received
                if(result.isPresent() && result.get()==ButtonType.OK){
                    allUsers.remove(userToDelete);
                    Alert alert1= new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("User deleted");
                    alert1.setContentText("The user was successfully deleted");
                    alert1.showAndWait();
                }
                //refreshes the page once user is deleted
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/userScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
    }

    @FXML
    void clickEditUser(ActionEvent event) throws IOException {
        if(userTable.getSelectionModel().getSelectedItem()==null){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User not selected");
            alert.setContentText("Please select the user you wish to edit and try again.");
            alert.showAndWait();
        }
        else{
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/editUser.fxml"));
            loader.load();
            UpdateUserController UUC = loader.getController();
            UUC.sendUser(userTable.getSelectionModel().getSelectedItem());

            stage=(Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene=loader.getRoot();
            stage.setScene(new Scene (scene));
            stage.show();
        }
    }

    public void setAllUsers(ObservableList<User> allUsers){
        userIdCol.setCellValueFactory(new PropertyValueFactory("userId"));
        userNameCol.setCellValueFactory(new PropertyValueFactory("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory("password"));
        employeeNameCol.setCellValueFactory(new PropertyValueFactory("employeeName"));
        userTypeCol.setCellValueFactory(new PropertyValueFactory("userType"));
        userTable.setItems(allUsers);
    }

    public void initialize(URL url, ResourceBundle rb){
        try {
            setAllUsers(Helper.getAllUsers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
