package domain;

import java.io.Serializable;

//show post_data for message board
public class ShowPost implements Serializable{

    private String userName;
    private boolean gender ;
    private String postTitle;
    private String postTime;
    private String postDesc;

    public ShowPost(String userName, boolean gender, String postTitle, String postTime, String postDesc) {
        this.userName = userName;
        this.gender = gender;
        this.postTitle = postTitle;
        this.postTime = postTime;
        this.postDesc = postDesc;
    }

    public ShowPost() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }
}
