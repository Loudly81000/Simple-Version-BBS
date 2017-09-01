<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 8/31/2017
  Time: 3:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Welcome user</title>
    <link rel="stylesheet" href="/CSSforJSP/wel.css" type="text/css"/>
</head>
<body>

<h2>Welcome to  <br>Simple-Version BBS</h2>


<button style="width:auto;" onclick="self.location.href='/LoginClServlet?type=gotologinView'">
    Login</button>


<br>
<a href="/UserClServlet?type=gotoaddInfo">Sign up</a>




</body>
</html>

