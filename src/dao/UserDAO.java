package dao;

import domain.User;

import java.sql.*;

public class UserDAO {

    public static final String url = "jdbc:mysql://128.199.212.17:3306/zhengfuyi";
    public static final String username = "zhengfuyi";
    public static final String password = "it6oMGUPNX1utE68";

    private Connection dbConnection  = null;
    private String sql;


    //insert data to table user_list
    public Boolean insert(User user) {

        Boolean result;

            try{

                Class.forName("com.mysql.jdbc.Driver");
                //Step1 getConnection java-DB
                dbConnection = getDbConnection();

                //Step2 send sql to PreparedStatement
                sql = "INSERT INTO user_list (userName, password, email, my_desc, gender)" +
                                " VALUES (?, ?, ?, ?, ?)";
                PreparedStatement PreStat = dbConnection.prepareStatement(sql);
                PreStat.setString(1,user.getUserName());
                PreStat.setString(2,user.getPassword());
                PreStat.setString(3,user.getEmail());
                PreStat.setString(4,user.getMyDesc());
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


    //query user's info by userID(primary key)
    public User queryByuserName(User user){

        User re = null;

        try {

            //create sql
            sql = "SELECT * FROM user_list WHERE userName = ?";

            //create connection
            dbConnection = getDbConnection();
            PreparedStatement prepstat = dbConnection.prepareStatement(sql);

            //send sql to db to get query result
            prepstat.setString(1,user.getUserName());
            ResultSet rs = prepstat.executeQuery();
            while(rs.next()){
                String userName = rs.getString(1);
                String password = rs.getString(2);
                String email = rs.getString(3);
                String myDesc = rs.getString(4);
                Boolean gender = rs.getBoolean(5);
                int userID = rs.getInt(6);
                re = new User(userName, password, email, myDesc, gender);
                re.setUserID(userID);
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

        return re;
    }


    //password verification
    //parameter is the value from user inputted
    public Boolean getVerifyResult(User user){

        Boolean verifyResult = false;
        User queryResultByuserName = queryByuserName(user);
        String password = queryResultByuserName.getPassword();
        if(password.equals(user.getPassword())){
            verifyResult = true;
        }
        return verifyResult;
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
