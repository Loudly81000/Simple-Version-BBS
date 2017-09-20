package dao;

import domain.Post_Info;
import domain.ShowPost;
import domain.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PostDB {

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
                String post_title = rs.getString(3);

                //get datetime from db and convert to String
                Date pTime = rs.getDate(4);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String  post_time= sdf.format(pTime);

                String post_desc = rs.getString(5);
                ShowPost showPost = new ShowPost(userName, gender, post_title, post_time, post_desc);
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

    public Boolean insertPostInfo(Post_Info post_Info){

        Boolean result;

        try{

            Class.forName("com.mysql.jdbc.Driver");
            //Step1 getConnection java-DB
            dbConnection = getDbConnection();

            //Step2 send sql to PreparedStatement
            sql = "INSERT INTO post_list (post_title, post_desc, post_time, uid)" +
                    " VALUES (?, ?, ?, ?) ";

            PreparedStatement PreStat = dbConnection.prepareStatement(sql);
            PreStat.setString(1,post_Info.getPost_title());
            PreStat.setString(2,post_Info.getPost_desc());
            PreStat.setString(3,post_Info.getPost_time());
            PreStat.setInt(4, post_Info.getUid());

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

    //query user's info by userID(primary key)
    public ArrayList<ShowPost> EditPostInfo(User loginUser){

        ArrayList<ShowPost>post_infoList = new ArrayList<>();


        try {

            //create sql
            //SELECT * FROM comment LEFT JOIN user on user.UserID = comment.uid WHERE user.FirstName="qqqqqqqqqqq";
            //SELECT * FROM post_list LEFT JOIN user_list on user_list.userID = post_list.uid WHERE user_list.userName = "ffff";
            sql =
                    "SELECT user_list.userName, user_list.gender, post_list.post_title, post_list.post_time, post_list.post_desc " +
                            "FROM post_list " +
                            "LEFT JOIN user_list on user_list.userID = post_list.uid WHERE user_list.userName=?" ;

            //create connection
            dbConnection = getDbConnection();
            PreparedStatement prestat = dbConnection.prepareStatement(sql);

            prestat.setString(1,loginUser.getUserName());
            //send sql to db to get query result(user info)
            ResultSet rs = prestat.executeQuery();
            while(rs.next()){
                String userName = rs.getString(1);
                boolean gender = rs.getBoolean(2);
                String post_title = rs.getString(3);

                //get datetime from db and convert to String
                Timestamp timestamp = rs.getTimestamp(4);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String  post_time= dateFormat.format(timestamp);
                System.out.println(timestamp);

                String post_desc = rs.getString(5);
                ShowPost showPost = new ShowPost(userName, gender, post_title, post_time, post_desc);
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




}
