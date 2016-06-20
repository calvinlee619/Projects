<%--
  Created by IntelliJ IDEA.
  User: kangw
  Date: 12/12/15
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
      <meta charset="utf-8"/>
      <title>Favorite Link Login</title>
    </head>

    <body>
        <h2>Favorite Item Login</h2>

        <jsp:include page="navbar.jsp"></jsp:include>

        <c:forEach var="error" items="${errors}">
          <h3 style="color: red"> ${error} </h3>
        </c:forEach>

        <form method="POST" action="change-pwd.do">
            <table>
              <tr>
                <td style="font-size: x-large">Passwod1:</td>
                <td>
                  <input type="password" name="password1" />
                </td>
              </tr>
              <tr>
                <td style="font-size: x-large">Password2:</td>
                <td><input type="password" name="password2" /></td>
              </tr>
              <tr>
                <td colspan="1" style="text-align: center">
                  <input type="submit" name="button" value="submit" />
                </td>
              </tr>
            </table>
        </form>

        <jsp:include page="navbar-bottom.jsp"></jsp:include>
    </body>
</html>
