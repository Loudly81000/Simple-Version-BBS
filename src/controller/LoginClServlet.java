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

// TODO:session function, Now can pass page to manager.jsp after press f5 when you had entered to manager.jsp but can go to another page
//
@WebServlet("/LoginClServlet")
public class LoginClServlet extends HttpServlet {

    static String loginUser;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        String url = "";
        UserDB userDB = new UserDB();
        HttpSession session = request.getSession();

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

        //如果已經登入的話,再重新整理url可能會500
        //在doPost之處如果驗證成功的話就建立一個session裡面放一個String
        // (可能不要因為如果先在manager.jsp修改過的話會拿到修改前的紀錄,所以改成隨便放一個session的值當作認證用)
        //再重新整理的時候會從doGet block經過
        //此時驗證session的字串存不存在
        //有的話繼續在manager.jsp記得要show資訊(此時不用驗證因為之前驗證成功過)
        //沒session的話,如果滿足referer.startwith(http://localhost/LoginClServlet)就直接跳登入頁面
        //referer的應用範例:
//        String referer = response.getHeader("Referer");
//
//        if(referer==null||!referer.startsWith("http://localhost:8080/RefererEx")){
//            response.sendRedirect("/RefererEx/error");
//        }

         HttpSession session = request.getSession();
         String sessionLogin = (String)session.getAttribute("sessionLogin");
         User user = (User)session.getAttribute("user");
         String url = "";
         String referer = request.getHeader("Referer");
        String type = request.getParameter("type");

        //check user is already logged or not
        //if user had logged in with veryfication, goto manager.jsp
        //&& referer.startsWith("http://localhost")
        if(sessionLogin != null && sessionLogin.equals("sessionLogin") ) {

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

            //avoid nullpointer Exception so use "&&"  not "||"
            if( referer !=null ){

                if(!referer.startsWith("http://localhost/LoginClServlet")) {

                    url = "/view/login/loginView.jsp";

                }else{

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
            }

        }

        //if not, return to loggin page
         if(sessionLogin == null){
             url = "/view/login/loginView.jsp";
         }

         System.out.println(url);
         System.out.println(referer);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
