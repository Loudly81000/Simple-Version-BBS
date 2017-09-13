package domain;

import java.io.Serializable;

//show post_data for message board
public class ShowPost implements Serializable{

    private String userName;
    private boolean gender ;
    private String post_title;
    private String post_time;
    private String post_desc;

    public ShowPost(String userName, boolean gender, String post_title, String post_time, String post_desc) {
        this.userName = userName;
        this.gender = gender;
        this.post_title = post_title;
        this.post_time = post_time;
        this.post_desc = post_desc;
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

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public void setPost_desc(String post_desc) {
        this.post_desc = post_desc;
    }
}
