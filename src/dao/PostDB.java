package dao;

import domain.Post_Info;
import domain.User;

import java.sql.*;
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
    public ArrayList<Post_Info> showPostInfo(Post_Info post_info){

        ArrayList<Post_Info>post_infoList = null;
        User user = null;

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
            PreparedStatement prepstat = dbConnection.prepareStatement(sql);

            //send sql to db to get query result(user info)
            prepstat.setString(1, "");
            ResultSet rs = prepstat.executeQuery();
            while(rs.next()){

            }

            //query




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
