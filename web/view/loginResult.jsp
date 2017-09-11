<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 9/7/2017
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
<c:when test="${result == true}">
    <h1>${result}</h1>
    <h2><a href="/UserClServlet?type=gotomanager">go to your webboard manager</a></h2>
</c:when>

<c:otherwise>
    <h1>failed!</h1>
    <h2><a href="/LoginClServlet?type=loginView.jsp">return to last page</a></h2>
</c:otherwise>
</c:choose>

</body>
</html>
