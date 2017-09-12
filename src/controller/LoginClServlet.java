package controller;

import dao.UserDB;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginClServlet")
public class LoginClServlet extends HttpServlet {
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

            //encapsulation & call method to verify
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            Boolean verifyRs = userDB.getVerifyResult(user);

            //get login result and dispatch to jsp to show result
            if(verifyRs){
                request.setAttribute("result","success");
            }
            if(!verifyRs){
                request.setAttribute("result", "fail");
            }

            url="/view/login/loginResult.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
