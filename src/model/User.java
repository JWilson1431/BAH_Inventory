package model;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String employeeName;
    private String userType;



    public User(int userId, String userName, String password, String employeeName, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.employeeName=employeeName;
        this.userType=userType;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
