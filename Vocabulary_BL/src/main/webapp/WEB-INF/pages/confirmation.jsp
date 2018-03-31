<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Confirmation Page</title>
</head>
<body>
    name:${sessionScope.registeredUser.name}
    <br>
    login:${sessionScope.registeredUser.login}
    <br>
    password:${sessionScope.registeredUser.password}
    <br>
    <hr>
     <sf:form action="confirmation" method="GET">
        <button name="confirmation" type="submit"> Confirmation
        </button>
    </sf:form>
</body>
</html>
