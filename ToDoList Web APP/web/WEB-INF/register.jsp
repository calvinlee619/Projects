<%--
  Created by IntelliJ IDEA.
  User: kangw
  Date: 12/12/15
  Time: 1:35 PM
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

  <form method="POST">
      <table>
          <tr>
              <td style="font-size: x-large">Email Address:</td>
              <td>
                  <input type="text" name="email" value = "${form.email}" />
              </td>
          </tr>
              <tr>
                  <tr>
                      <td style="font-size: x-large">First Name:</td>
                      <td>
                          <input type="text" name="firstName" value="${form.firstName}" />
                      </td>
                  </tr>

              <tr>
                  <tr>
                      <td style="font-size: x-large">Last Name:</td>
                      <td>
                          <input type="text" name="lastName" value="${form.lastName}" />
                      </td>
                  </tr>

              <tr>
                    <td style="font-size: x-large">Password:</td>
                    <td><input type="password" name="password" /></td>
              </tr>

              <tr>
                  <td style="font-size: x-large">Password Confirmation:</td>
                  <td><input type="password" name="confirm" /></td>
              </tr>
              <tr>
                    <td colspan="1" style="text-align: center">
                        <input type="submit" name="action" value="register" />
                    </td>
                    <td colspan="1" style="text-align: center">
                        <a href="login.do"> login </a>
                    </td>
                </tr>
          </table>
    </form>

  <jsp:include page="navbar-bottom.jsp"></jsp:include>
  </body>
</html>
