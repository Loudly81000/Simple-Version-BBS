<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 8/19/2017
  Time: 10:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Welcome user</title>
    <link rel="stylesheet" href="/CSSforJSP/welcomeview.css" type="text/css"/>
</head>
<body>
<div class="h1"><h1>請填入以下資料</h1></div>

<form action="" method="post">
    <table id="table">
        <label>name    </label>
        <input type="text" name="username"><br><br>
        <label>pwd       &nbsp;&nbsp;</label>
        <input type="password" name="password"><br><br>
        <label>email   </label>
        <input type="email" name="email"><br><br>
        <label>性別       &nbsp;&nbsp;</label>
        <input type="radio" name="gender" value="boy">男
        <input type="radio" name="gender" value="girl">女
        <input type="submit" value="submit" id="submit">
    </table>
</form>
<a href="/UserClServlet?type=gotowelcome">返回上一頁</a>
</body>
</html>
