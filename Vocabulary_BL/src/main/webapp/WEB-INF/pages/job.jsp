<%--
  Created by IntelliJ IDEA.
  User: dbriskin
  Date: 21.04.2017
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Surname</title>
</head>
<body>
<h1>First: ${sessionScope.firstname} <br>
    Last: ${sessionScope.lastname}</h1>
<form action="job" method="GET">
    <input type="text" name="job"/>
    <input type="submit" name="Send" value="Send job"/>
</form>

</body>
</html>
