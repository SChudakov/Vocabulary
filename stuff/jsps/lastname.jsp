<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Spring MVC</title>
</head>
<body>
<h1>First: ${sessionScope.firstname}</h1>
</body>

    <form action="lastname" method="GET">
        <input type="text" name="lastname"/>
        <input type="submit" name="Send" value="Send last name"/>
    </form>
</html>
