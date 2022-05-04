package Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.InjectableMed;
import model.OralMed;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Helper {

    //method to add a new user to the database
    public static int addUser(String userName, String password,String employeeName, String userType) throws SQLException {
        String sql= "INSERT INTO users(userName, password, employeeName, userType) VALUES (?,?,?,?)";
        PreparedStatement ps = DatabaseConnection.connection.prepareStatement(sql);
        ps.setString(1,userName);
        ps.setString(2,password);
        ps.setString(3,employeeName);
        ps.setString(4,userType);
        int rowsAffected=ps.executeUpdate();
        return rowsAffected;
    }

    //method to add an injectable med
    public static int addInjMed(String medName, int quantity, String concentration, int sizeOfVial) throws SQLException {
        String sql="INSERT INTO injectable_med(Name, Quantity_In_Stock, Concentration, Size_Of_Vial) VALUES(?,?,?,?)";
        PreparedStatement ps=DatabaseConnection.connection.prepareStatement(sql);
        ps.setString(1,medName);
        ps.setInt(2,quantity);
        ps.setString(3,concentration);
        ps.setInt(4,sizeOfVial);
        int rowsAffected=ps.executeUpdate();
        return rowsAffected;
    }

    //method to add an oral med
    public static int addOralMed(String medName, int quantity, int tabInBottle, int tabStrength) throws SQLException {
        String sql="INSERT INTO oral_med(Name, Quantity_In_Stock, Tablets_In_Bottle, Tablet_Strength) VALUES(?,?,?,?)";
        PreparedStatement ps=DatabaseConnection.connection.prepareStatement(sql);
        ps.setString(1,medName);
        ps.setInt(2,quantity);
        ps.setInt(3,tabInBottle);
        ps.setInt(4,tabStrength);
        int rowsAffected=ps.executeUpdate();
        return rowsAffected;
    }

    //method to check the log in credentials
    public static boolean checkCredentials(String userName, String password) throws SQLException {
        String sql= "SELECT * from users WHERE UserName=? and Password=?";
        PreparedStatement ps= DatabaseConnection.connection.prepareStatement(sql);
        ps.setString(1,userName);
        ps.setString(2,password);
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
            int userId=rs.getInt(1);
            String userName1=rs.getString(2);
            String password1=rs.getString(3);
            String employeeName1=rs.getString(4);
            String userType=rs.getString(5);
            User currentUser= new User(userId,userName1,password1,employeeName1,userType);
            return true;
        }
        else{
            return false;
        }
    }

    //method to get all users from the database
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> allUsers= FXCollections.observableArrayList();
        String sql="SELECT * FROM users";
        PreparedStatement ps=DatabaseConnection.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();

        while(rs.next()){
            int userID=rs.getInt(1);
            String userName=rs.getString(2);
            String password=rs.getString(3);
            String employeeName=rs.getString(4);
            String userType=rs.getString(5);

            allUsers.add(new User(userID,userName,password,employeeName,userType));
        }
        return allUsers;
    }

    //method to get all injectable meds from the database
    public static ObservableList<InjectableMed> getAllInjMeds() throws SQLException {
        ObservableList<InjectableMed> allInjMeds=FXCollections.observableArrayList();
        String sql="SELECT * FROM injectable_med";
        PreparedStatement ps=DatabaseConnection.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();

        while(rs.next()){
            int medId=rs.getInt(1);
            String medName=rs.getString(2);
            int quantity=rs.getInt(3);
            String concentration=rs.getString(4);
            int sizeOfVial=rs.getInt(5);
            allInjMeds.add(new InjectableMed(medId,medName,quantity,concentration,sizeOfVial));
        }
        return allInjMeds;
    }

    //method to get all oral meds from the database
    public static ObservableList<OralMed> getAllOralMeds() throws SQLException {
        ObservableList<OralMed> allOralMeds=FXCollections.observableArrayList();
        String sql="SELECT * FROM oral_med";
        PreparedStatement ps=DatabaseConnection.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();

        while(rs.next()){
            int medId=rs.getInt(1);
            String medName=rs.getString(2);
            int quantity=rs.getInt(3);
            int tabInBottle=rs.getInt(4);
            int strengthOfTab=rs.getInt(5);
            allOralMeds.add(new OralMed(medId,medName,quantity,tabInBottle,strengthOfTab));
        }
        return allOralMeds;
    }

    //method to update a medication
    public static int updateInjMed(String name, int quantity, String concentration, int vialSize, int medId) throws SQLException {
        String sql= "UPDATE injectable_med SET Name = ?, Quantity_In_Stock = ?, Concentration = ?, Size_Of_Vial = ? WHERE Medication_ID = ?";
        PreparedStatement ps = DatabaseConnection.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2,quantity);
        ps.setString(3, concentration);
        ps.setInt(4,vialSize);
        ps.setInt(5,medId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int updateOralMed(String name, int quantity, int tabInBottle, int strength, int medId) throws SQLException {
        String sql= "UPDATE oral_med SET Name = ?, Quantity_In_Stock = ?, Tablets_In_Bottle = ?, Tablet_Strength = ? WHERE Medication_ID = ?";
        PreparedStatement ps = DatabaseConnection.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2,quantity);
        ps.setInt(3, tabInBottle);
        ps.setInt(4,strength);
        ps.setInt(5,medId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }



}
