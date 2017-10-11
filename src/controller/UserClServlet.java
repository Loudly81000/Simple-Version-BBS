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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/UserClServlet")
public class UserClServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get type from jsp for
        // setting value, showing value, or updating value
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

            //Step 1.
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

            //Step 2.
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
            System.out.println(userName);
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

            //Step4.
            //return to message board and show info
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


        if(type.equals("passpostInfoedited")){

            //Step 1.
            //get info edited from manager.jsp
            String postDesc = request.getParameter("postDesc");
            String spostId = request.getParameter("postId");
            int postId = Integer.valueOf(spostId);

            //Step 2.
            //update info to db
            //first, store data to postInfo object through encapsulation
            PostInfo postInfo = new PostInfo();
            postInfo.setPostId(postId);
            postInfo.setPostDesc(postDesc);
            //call method to update info
            Boolean setPostResult = postDB.setPostEdited(postInfo);

            //Step 3.
            //return to manager.jsp
            ArrayList<ShowPost> showPostArrayList = postDB.showPostInfo();
            request.setAttribute("showPostArrayList",showPostArrayList);

            //for showing edit message page
            String userName = request.getParameter("userName");
            User user = new User();
            user.setUserName(userName);
            request.setAttribute("userName",userName);
            ArrayList<ShowPost> editPostArrayList = postDB.EditPostInfo(user);
            request.setAttribute("editPostArrayList", editPostArrayList);

            //return to manager.jsp and activate alert"update message successfully"
            url = "/view/manager.jsp";

        }


        if(type.equals("deleteComment")){

            //Step 1.
            //get info user want to delete
            String spostId = request.getParameter("postId");
            int postId = Integer.valueOf(spostId);

            //Step 2.
            //delete comment
            PostInfo postInfo = new PostInfo();
            postInfo.setPostId(postId);
            Boolean deleteResult = postDB.deletePost(postInfo);

            //Step3.
            //return to manager.jsp
            ArrayList<ShowPost> showPostArrayList = postDB.showPostInfo();
            request.setAttribute("showPostArrayList",showPostArrayList);

            //for showing edit message page
            String userName = request.getParameter("userName");
            User user = new User();
            user.setUserName(userName);
            request.setAttribute("userName",userName);
            ArrayList<ShowPost> editPostArrayList = postDB.EditPostInfo(user);
            request.setAttribute("editPostArrayList", editPostArrayList);

            //return to manager.jsp and activate alert"update message successfully"
            url = "/view/manager.jsp";

        }

        getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String sessionLogin = (String)session.getAttribute("sessionLogin");
        User user = (User)session.getAttribute("user");
        String referer = request.getHeader("Referer");
        String url = "";
        System.out.println(sessionLogin);
        String type = request.getParameter("type");

        //check whether session is set or not
        //if set finished -> show message board & editpost page
        if(sessionLogin !=null && sessionLogin.equals("sessionLogin")){

                UserDB userDB = new UserDB();

                //for showing message board
                PostDB postDB = new PostDB();
                ArrayList<ShowPost> showPostArrayList = postDB.showPostInfo();
                request.setAttribute("showPostArrayList", showPostArrayList);

                //for showing edit message page
                ArrayList<ShowPost> editPostArrayList = postDB.EditPostInfo(user);
                request.setAttribute("editPostArrayList", editPostArrayList);

                url = "/view/manager.jsp";

        }

        //if not, return to loggin page
        if(sessionLogin == null){
                url="/view/login/loginView.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);

    }


}
