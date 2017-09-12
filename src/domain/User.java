package domain;

import java.io.Serializable;

public class User implements Serializable {

    private String userName;
    private String password;
    private String email;
    private String my_desc;
    private boolean gender;
    //true==male false==female
    private int userID;

    public User() {

    }

    public User(String userName, String password, String email, String my_desc, boolean gender) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.my_desc = my_desc;
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

    public String getMy_desc() {
        return my_desc;
    }

    public void setMy_desc(String my_desc) {
        this.my_desc = my_desc;
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
