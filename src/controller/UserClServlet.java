package controller;

import dao.UserDB;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserClServlet")
public class UserClServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        String url = "";


        if(type.equals("gotoaddInfo")){
            url = "/view/addInfo.jsp";
        }


        if(type.equals("gotowelcome")){
            url="/view/wel.jsp";
        }


        if(type.equals("addInfo")){

            //receive value from addInfo.jsp
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String my_desc = request.getParameter("my_desc");
            String morf = request.getParameter("gender");
            Boolean gender;
            System.out.println(userID);

            if(morf.equals("male")){
                gender = true;
            }else{
                gender = false;
            }

            //pass value to db
            UserDB userDB = new UserDB();
            User user = new User(userID, password, email, my_desc, gender);
            Boolean result = userDB.insert(user);
            request.setAttribute("result", result);
            url = "/view/addInfoResult.jsp";

        }

        getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request, response);
    }



}
