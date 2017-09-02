package domain;

import java.io.Serializable;

public class Post_Info implements Serializable {

    private String UserID;
    private String password;
    private String email;
    private String post_title;
    private String post_desc;
    private int post_id;

    public Post_Info(String post_name, String post_pwd, String post_email,
                     String post_title, String post_desc){
        this.UserID = post_name;
        this.password = post_pwd;
        this.email = post_email;
        this.post_title = post_title;
        this.post_desc = post_desc;
    }

    public void setPost_name(String post_name) {
        this.UserID = post_name;
    }

    public void setPost_pwd(String post_pwd) {
        this.password = post_pwd;
    }

    public void setPost_email(String post_email) {
        this.email = post_email;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public void setPost_desc(String post_desc) {
        this.post_desc = post_desc;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_name() {
        return UserID;
    }

    public String getPost_pwd() {
        return password;
    }

    public String getPost_email() {
        return email;
    }

    public String getPost_title() {
        return post_title;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public int getPost_id() {
        return post_id;
    }
}
