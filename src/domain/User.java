package domain;

import java.io.Serializable;

public class User implements Serializable {

    private String userID;
    private String password;
    private String email;
    private String my_desc;
    private boolean gender;
    //true==male false==female

    public User() {

    }

    public User(String userID, String password, String email, String my_desc, boolean gender) {
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.my_desc = my_desc;
        this.gender = gender;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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



}
