package controller;

import Database.Helper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    Stage stage;
    Parent scene;

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
    void clickDeleteUser(ActionEvent event) {

    }

    @FXML
    void clickEditUser(ActionEvent event) {

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
