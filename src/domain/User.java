package domain;

import java.io.Serializable;

public class User implements Serializable {

    private String userName;
    private String password;
    private String email;
    private String myDesc;
    private boolean gender;
    //true==male false==female
    private int userID;

    public User() {

    }

    public User(String userName, String password, String email, String myDesc, boolean gender) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.myDesc = myDesc;
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMyDesc() {
        return myDesc;
    }

    public void setMyDesc(String myDesc) {
        this.myDesc = myDesc;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
