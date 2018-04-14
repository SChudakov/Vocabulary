<%--
  Created by IntelliJ IDEA.
  User: Semen
  Date: 07.04.2018
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Words</title>
    </head>
    <body>
        <form action="/words/wordInfo" method="GET">
            <input type="text" name="value"/><br>
            <input type="text" name="lang"/><br>
            <input type="submit" name="Send" value="get word information"/>
        </form>
    </body>
</html>
