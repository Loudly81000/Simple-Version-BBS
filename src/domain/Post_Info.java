package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Post_Info implements Serializable {

    private String post_title;
    private String post_desc;
    private int post_id;
    private String post_time;

    public String getLocalDateTimeStr() {
        return post_time;
    }

    public Post_Info(String post_title, String post_desc){
        this.post_title = post_title;
        this.post_desc = post_desc;
        generateDateTimeStr();
    }

    public Post_Info() {
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

    public String getPost_time() {
        return post_time;
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

    //getLocalDateTime methodi: time to String
    public String getNowLocalDateTime (){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        return currentTime;
    }

    public void generateDateTimeStr() {
        this.post_time = getNowLocalDateTime();
    }


}
