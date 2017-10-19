package dao;

import domain.*;
import domain.PostInfo;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PostDAO {

    public static final String url = "jdbc:mysql://128.199.212.17:3306/zhengfuyi";
    public static final String username = "zhengfuyi";
    public static final String password = "it6oMGUPNX1utE68";

    private Connection dbConnection  = null;
    private String sql;

    public Connection getDbConnection(){
        try {
            dbConnection = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dbConnection;
    }

    //query user's info by userID(primary key)
    public ArrayList<ShowPost> showPostInfo(){

        ArrayList<ShowPost>post_infoList = new ArrayList<>();


        try {

            //create sql
            //SELECT * FROM comment LEFT JOIN user on user.UserID = comment.uid WHERE user.FirstName="qqqqqqqqqqq";
            //SELECT * FROM post_list LEFT JOIN user_list on user_list.userID = post_list.uid WHERE user_list.userName = "ffff";
            sql =
                    "SELECT user_list.userName, user_list.gender, post_list.post_title, post_list.post_time, post_list.post_desc " +
                            "FROM post_list " +
                            "LEFT JOIN user_list on user_list.userID = post_list.uid" ;

            //create connection
            dbConnection = getDbConnection();
            Statement stat = dbConnection.createStatement();

            //send sql to db to get query result(user info)
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                String userName = rs.getString(1);
                boolean gender = rs.getBoolean(2);
                String postTitle = rs.getString(3);

                //get datetime from db and convert to String
                Timestamp pTime = rs.getTimestamp(4);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String  postTime= sdf.format(pTime);

                String postDesc = rs.getString(5);
                ShowPost showPost = new ShowPost(userName, gender, postTitle, postTime, postDesc);
                post_infoList.add(showPost);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try {
                dbConnection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return post_infoList;
    }

    public Boolean insertPostInfo(PostInfo postInfo){

        Boolean result;

        try{

            Class.forName("com.mysql.jdbc.Driver");
            //Step1 getConnection java-DB
            dbConnection = getDbConnection();

            //Step2 send sql to PreparedStatement
            sql = "INSERT INTO post_list (post_title, post_desc, post_time, uid)" +
                    " VALUES (?, ?, ?, ?) ";

            PreparedStatement PreStat = dbConnection.prepareStatement(sql);
            PreStat.setString(1,postInfo.getPostTitle());
            PreStat.setString(2,postInfo.getPostDesc());
            PreStat.setString(3,postInfo.getPostTime());
            PreStat.setInt(4, postInfo.getUid());

            PreStat.execute();
            result = true;

        }catch(SQLException e) {
            e.printStackTrace();
            result = false;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            result = false;
        }finally {
            try {
                dbConnection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }


    public Boolean deletePost(PostInfo postInfo){

        Boolean result = false;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            //Step 1.
            //create sql statement for updating post edited
            String sql = "DELETE FROM post_list WHERE post_id = ?";

            //Step 2.
            //pass sql statement to db
            dbConnection = getDbConnection();
            PreparedStatement preState = dbConnection.prepareStatement(sql);
            preState.setInt(1, postInfo.getPostId());
            result = preState.execute();

        }catch(SQLException e){
            e.printStackTrace();
            result = false;
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            result = false;
        }finally {
            try {
                dbConnection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return result;
    }


    //update editted post data to DB
    //input parameter is post edited
    public Boolean setPostEdited(PostInfo postEdited){

        Boolean result = true;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            //Step 1.
            //create sql statement for updating post edited
            String sql = "UPDATE post_list SET post_desc = ? WHERE post_id = ?";

            //Step 2.
            //pass sql statement to db
            dbConnection = getDbConnection();
            PreparedStatement preState = dbConnection.prepareStatement(sql);
            preState.setString(1, postEdited.getPostDesc());
            preState.setInt(2, postEdited.getPostId());
            result = preState.execute();

        }catch(SQLException e){
            e.printStackTrace();
            result = false;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            result = false;
        }finally{
            try {
                dbConnection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return  result;
    }




    //query user's info by userID(primary key)
    public ArrayList<ShowPost> EditPostInfo(User loginUser){

        ArrayList<ShowPost>postInfoList = new ArrayList<>();


        try {

            //Step 1.
            //create sql statement and send statement to db
            sql =
                    "SELECT user_list.userName, user_list.gender, post_list.post_title, post_list.post_time, post_list.post_desc, post_list.post_id " +
                            "FROM post_list " +
                            "LEFT JOIN user_list on user_list.userID = post_list.uid WHERE user_list.userName=?" ;

            //create connection
            dbConnection = getDbConnection();
            PreparedStatement prestat = dbConnection.prepareStatement(sql);

            prestat.setString(1,loginUser.getUserName());
            //send sql to db to get query result(user info)
            ResultSet rs = prestat.executeQuery();

            //Step 2.
            //get result from db
            while(rs.next()){
                String userName = rs.getString(1);
                boolean gender = rs.getBoolean(2);
                String postTitle = rs.getString(3);

                //get datetime from db and convert to String
                Timestamp timestamp = rs.getTimestamp(4);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String  postTime= dateFormat.format(timestamp);

                String postDesc = rs.getString(5);

                //get post_id
                //post_id is for editing comment
                int postId = rs.getInt(6);

                //Step 3.
                //set value in showPost object
                // pass value to arraylist for showing post info in edit page
                ShowPost showPost = new ShowPost(userName, gender, postTitle, postTime, postDesc);
                showPost.setPostId(postId);
                postInfoList.add(showPost);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try {
                dbConnection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return postInfoList;
    }

    public int getPageNums(){

        int pageNums =0;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            //Step1 getConnection java-DB
            dbConnection = getDbConnection();

            sql = "SELECT COUNT(*) AS ROWCOUNT FROM user_list";
            Statement stat = dbConnection.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()){
                pageNums = rs.getInt(1);
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try {
                dbConnection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return pageNums;
    }


    //get now page info and return it by result set
    public ResultSet getPageNowInfoRs(String sql, SeperatePage seperatePage){

        ResultSet rs=null;

        try{
            dbConnection = getDbConnection();
            PreparedStatement preStat= dbConnection.prepareStatement(sql);

            preStat.setInt(1, (seperatePage.getPageNow()-1)*seperatePage.getPageSize());
            preStat.setInt(2, seperatePage.getPageSize());

            rs = preStat.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return rs;
    }


    //encapsulate page info to arraylist for displaying info
    public ArrayList<ShowPost> getPageInfo (SeperatePage seperatePage){

        ArrayList <ShowPost> pageNowInfo = new ArrayList<>();
        sql = "select user_list.userName, user_list.gender, post_list.post_title, post_list.post_time, post_list.post_desc from\n" +
                "  post_list LEFT JOIN user_list on user_list.userID = post_list.uid order by post_id  ASC limit ?, ?";
        //try 1~3 comment
        ShowPost showPost = null;

        ResultSet rs = getPageNowInfoRs(sql, seperatePage);
        try{
            while(rs.next()){
                String userName = rs.getString(1);
                Boolean gender = rs.getBoolean(2);
                String postTitle = rs.getString(3);
                Timestamp timestamp = rs.getTimestamp(4);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String  postTime= dateFormat.format(timestamp);
                String postDesc = rs.getString(5);

                showPost = new ShowPost(userName, gender, postTitle, postTime, postDesc);
                pageNowInfo.add(showPost);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                dbConnection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return pageNowInfo;
    }



}
