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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/LoginClServlet")
public class LoginClServlet extends HttpServlet {

    static String loginUser;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        String url = "";
        UserDB userDB = new UserDB();
        HttpSession session = request.getSession();

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
            if(verifyRs == true){
                PostDB postDB = new PostDB();
                request.setAttribute("userName",userName);

                //for showing message board
                ArrayList<ShowPost> showPostArrayList = postDB.showPostInfo();
                request.setAttribute("showPostArrayList",showPostArrayList);

                //for showing edit message page
                ArrayList<ShowPost> editPostArrayList = postDB.EditPostInfo(user);
                request.setAttribute("editPostArrayList", editPostArrayList);

                //set session for registration
                session.setAttribute("sessionLogin", "sessionLogin");
                session.setAttribute("user", user);

                url = "/view/manager.jsp";

            }

            if(verifyRs == false){
                request.setAttribute("result", "fail");
                url="/view/login/loginResult.jsp";
            }
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         HttpSession session = request.getSession();
         String sessionLogin = (String)session.getAttribute("sessionLogin");
         User user = (User)session.getAttribute("user");
         String url = "";
         String referer = request.getHeader("Referer");
         String type = request.getParameter("type");

        //check user had already logged or not
        if(sessionLogin != null && sessionLogin.equals("sessionLogin") ) {

            //if user had logged with veryfication and referer ==null
            // goto manager.jsp
            if (referer == null) {

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

            //if referer !=null
            //meaning user clicked login button in wel.jsp
            if( referer !=null ){
                if(referer.startsWith("http://localhost:8080/view/wel.jsp")){
                    url="/view/login/loginView.jsp";
                }
            }
         }

        //if not, return to loggin page
         if(sessionLogin == null){
            if( referer != null && referer.startsWith("http://localhost:8080/view/wel.jsp")){
                url="/view/login/loginView.jsp";
            }else {
                url = "/view/wel.jsp";
            }
         }

         //whether session is set or not set
         //if type == "gootologinView", go to login page
         if(type != null && type.equals("gotologinView")){
             url = "/view/login/loginView.jsp";
         }

        if(type.equals("logOut")){
            session.removeAttribute("sessionLogin");
            url = "/view/login/loginView.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
