<%--
  Created by IntelliJ IDEA.
  User: kangw
  Date: 7/4/16
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title> Hello Page</title>
</head>
<body>
<div>
  <p> Please Enter the name that you want to type</p>
  <form method="post">
    <input name="userId" value="Type now">
    <button type="submit">submit</button>
  </form>

  <p> You enter the name: ${ name }</p>
</div>
</body>
</html>