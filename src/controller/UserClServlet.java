package controller;

import dao.PostDB;
import dao.UserDB;
import domain.Post_Info;
import domain.ShowPost;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserClServlet")
public class UserClServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        String url = "";
        PostDB postDB = new PostDB();
        UserDB userDB = new UserDB();


        if(type.equals("gotoaddInfo")){
            url = "/view/addUser/addInfo.jsp";
        }


        if(type.equals("gotowelcome")){
            url="/view/wel.jsp";
        }


        if(type.equals("addInfo")){

            //receive value from addInfo.jsp
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String my_desc = request.getParameter("my_desc");
            String morf = request.getParameter("gender");
            Boolean gender;
            System.out.println(userName);

            if(morf.equals("male")){
                gender = true;
            }else{
                gender = false;
            }

            //pass value to db
            User user = new User(userName, password, email, my_desc, gender);
            Boolean result = userDB.insert(user);
            request.setAttribute("result", result);
            url = "/view/addUser/addInfoResult.jsp";

        }

        if(type.equals("gotomanager")){

            //show messge board page
            ArrayList<ShowPost> showPostArrayList = postDB.showPostInfo();
            request.setAttribute("showPostArrayList",showPostArrayList);
            url="/view/manager.jsp";
        }

        if(type.equals("insertPost")){
            //Step 1.
            // get userID by userName
            String userName= request.getParameter("userName");
            User user = new User();
            user.setUserName(userName);
            User reUser = userDB.queryByuserName(user);
            int userID = reUser.getUserID();

            //Step 2.
            // get post info from form and initialize
            // Post_Info object to insert value to post_list table
            String post_title = request.getParameter("post_title");
            String post_desc = request.getParameter("post_desc");
            Post_Info post_info = new Post_Info(post_title, post_desc);
            post_info.setUid(userID);

            //Step 3. insertInfo and set uid
            Boolean result = postDB.insertPostInfo(post_info);

            if(result){
                request.setAttribute("result","success");
            }
            if(!result){
                request.setAttribute("result","fail");
            }

            request.setAttribute("userName",userName);
            ArrayList<ShowPost> showPostArrayList = postDB.showPostInfo();
            request.setAttribute("showPostArrayList",showPostArrayList);
            url = "/view/manager.jsp";
           //return to manager.jsp and activate alert"update message successfully"
        }




















        getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request, response);
    }



}
