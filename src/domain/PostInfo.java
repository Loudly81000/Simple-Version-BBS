package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostInfo implements Serializable {

    private String postTitle;
    private String postDesc;
    private int postId;
    private String postTime;
    private int uid; //foerign key connect to user_list table


    public String getLocalDateTimeStr() {
        return postTime;
    }

    public PostInfo(String postTitle, String postDesc){
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        generateDateTimeStr();
    }

    public PostInfo() {
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTime() {
        return postTime;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public int getPostId() {
        return postId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    //getLocalDateTime methodi: time to String
    public String getNowLocalDateTime (){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        return currentTime;
    }

    public void generateDateTimeStr() {
        this.postTime = getNowLocalDateTime();
    }


}
