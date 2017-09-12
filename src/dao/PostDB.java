package dao;

import domain.Post_Info;
import domain.ShowPost;
import domain.User;

import java.sql.*;
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
    public ArrayList<ShowPost> showPostInfo(Post_Info post_info){

        ArrayList<ShowPost>post_infoList = null;


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

}
