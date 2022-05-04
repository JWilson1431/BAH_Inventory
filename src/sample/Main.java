package sample;

import Database.DatabaseConnection;
import Database.Helper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/logInForm.fxml"));
        primaryStage.setTitle("BAH Inventory system");
        primaryStage.setScene(new Scene(root, 650, 550));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        DatabaseConnection.openConnection();
        launch(args);

        //Helper.addUser("admin","passW0rd!");
        //Helper.addInjMed("Ketamine", 2, "10mg/ml",10);
        //Helper.addOralMed("Trazodone",3,500,100);

        DatabaseConnection.closeConnection();
    }
}
