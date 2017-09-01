<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 8/31/2017
  Time: 3:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Add User Information</title>
</head>
<body>

<form action="/">
    <fieldset>
        <legend>Personal information:</legend>
        Username:<br>
        <input type="text" name="post_name" >
        <br>
        Password:<br>
        <input type="password" name="post_pwd" >
        <br>
        Email:<br>
        <input type="email" name="post_email" >
        <br>
        Gender:<br>
        <input type="radio" name="gender" value="boy">男
        <input type="radio" name="gender" value="girl">女
        <br><br>
        <input type="submit" value="Submit">
    </fieldset>
</form>

</body>
</html>
