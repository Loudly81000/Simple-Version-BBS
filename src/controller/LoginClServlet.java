package controller;

import dao.PostDB;
import dao.UserDB;
import domain.ShowPost;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LoginClServlet")
public class LoginClServlet extends HttpServlet {

    static String loginUser;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        String url = "";
        UserDB userDB = new UserDB();

        System.out.println(type);
        if(type.equals("gotologinView")){
            url = "/view/login/loginView.jsp";
        }

        if(type.equals("verification")){

            //get user's value from form
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");

            //encapsulate info to User object and verify
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            Boolean verifyRs = userDB.getVerifyResult(user);

            //get login result and dispatch to jsp to show result
            if(verifyRs==true){
                PostDB postDB = new PostDB();
                request.setAttribute("userName",userName);

                //for showing message board
                ArrayList<ShowPost> showPostArrayList = postDB.showPostInfo();
                request.setAttribute("showPostArrayList",showPostArrayList);

                //for showing edit message page
                ArrayList<ShowPost> editPostArrayList = postDB.EditPostInfo(user);
                request.setAttribute("editPostArrayList", editPostArrayList);

                url="/view/manager.jsp";

            }
            if(verifyRs==false){
                request.setAttribute("result", "fail");
                url="/view/login/loginResult.jsp";
            }
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
