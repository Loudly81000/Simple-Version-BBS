<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 9/6/2017
  Time: 12:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<c:choose>
    <c:when test="${result == true}">
        <h1>created account successfully</h1>
    </c:when>
    <c:otherwise>
        <h1>failed! The name you chose is already taken.</h1>
    </c:otherwise>
</c:choose>

<a href="/UserClServlet?type=gotowelcome">return to the welcome page</a>

</body>
</html>
