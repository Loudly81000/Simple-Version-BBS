package controller;

import dao.PostDB;
import dao.UserDB;
import domain.PostInfo;
import domain.PostInfo;
import domain.ShowPost;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/UserClServlet")
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
            String myDesc = request.getParameter("myDesc");
            String morf = request.getParameter("gender");
            Boolean gender;
            System.out.println(userName);

            if(morf.equals("male")){
                gender = true;
            }else{
                gender = false;
            }

            //pass value to db
            User user = new User(userName, password, email, myDesc, gender);
            Boolean result = userDB.insert(user);
            request.setAttribute("result", result);
            url = "/view/addUser/addInfoResult.jsp";

        }

        if(type.equals("gotomanager")){

            String login = request.getParameter("login");

            if(login == "login") {
                //show messge board page
                ArrayList<ShowPost> showPostArrayList = postDB.showPostInfo();
                request.setAttribute("showPostArrayList", showPostArrayList);
                url = "/view/manager.jsp";
            }

            if(login == null || login == ""){
                url = "/view/wel.jsp";
            }

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
            String postTitle = request.getParameter("postTitle");
            String postDesc = request.getParameter("postDesc");
            PostInfo postInfo = new PostInfo(postTitle, postDesc);
            postInfo.setUid(userID);

            //Step 3. insertInfo and set uid
            Boolean result = postDB.insertPostInfo(postInfo);

            if(result){
                request.setAttribute("result","success");
            }
            if(!result){
                request.setAttribute("result","fail");
            }

            //for showing message board
            request.setAttribute("userName",userName);
            ArrayList<ShowPost> showPostArrayList = postDB.showPostInfo();
            request.setAttribute("showPostArrayList",showPostArrayList);

            //for showing edit message page
            ArrayList<ShowPost> editPostArrayList = postDB.EditPostInfo(user);
            request.setAttribute("editPostArrayList", editPostArrayList);

            //return to manager.jsp and activate alert"update message successfully"
            url = "/view/manager.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request, response);
    }



}
