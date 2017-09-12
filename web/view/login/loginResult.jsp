<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 9/7/2017
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login Result</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<c:if test="${result == 'success'}">
    <h1>${result}</h1>
    <h2><a href="/UserClServlet?type=gotomanager">go to your webboard manager</a></h2>
</c:if>
<c:if test="${result =='fail'}">
    <h1>failed!</h1>
    <h2><a href="/LoginClServlet?type=gotologinView">return to last page</a></h2>
</c:if>


</body>
</html>
