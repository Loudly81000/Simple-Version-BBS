<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 8/17/2017
  Time: 5:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Welcome user</title>
    <link rel="stylesheet" href="/CSSforJSP/view.css" type="text/css"/>
</head>
<body>
<div class="h1"><h1>Welcome to  <br>Simple-Version BBS</h1></div>

<form action="" method="post" id="formstyle">
    <p>登入</p>
    <div class="tb">
    <table id="table">
        <label>name    </label>
        <input type="text" name="username"><br><br>
        <label>pwd       &nbsp;&nbsp;</label>
        <input type="text" name="password"><br><br>
        <input type="submit" value="submit" id="submit">
    </table>
    </div>
    <br><br><br>
    <a href="UserClServlet?type=gotoaddUser">沒有帳號?請點擊這裡申請</a>
</form>




</body>
</html>
