package dao;

import domain.User;

import java.sql.*;

public class UserDB {

    public static final String url = "jdbc:mysql://128.199.212.17:3306/zhengfuyi";
    public static final String username = "zhengfuyi";
    public static final String password = "it6oMGUPNX1utE68";

    Connection dbConnection  = null;

    //insert data to table user_list
    public Boolean insert(User user) {

        Boolean result;

            try{

                Class.forName("com.mysql.jdbc.Driver");
                //Step1 getConnection java-DB
                dbConnection = getDbConnection();

                //Step2 send sql to PreparedStatement
                String sql = "INSERT INTO user_list (userID, password, email, my_desc, gender)" +
                                " VALUES (?, ?, ?, ?, ?)";
                PreparedStatement PreStat = dbConnection.prepareStatement(sql);
                PreStat.setString(1,user.getUserID());
                PreStat.setString(2,user.getPassword());
                PreStat.setString(3,user.getEmail());
                PreStat.setString(4,user.getMy_desc());
                PreStat.setBoolean(5,user.isGender());

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


    public Connection getDbConnection(){
        try {
            dbConnection = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dbConnection;
    }








}
