<%--
  Created by IntelliJ IDEA.
  User: kangw
  Date: 12/12/15
  Time: 1:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Favorite -- Error Page</title>
</head>

<body>

<h2>Favorite Link Error</h2>

<c:forEach var="error" items="${errors}">
  <h3 style="color:red"> ${error} </h3>
</c:forEach>

<c:choose>
  <c:when test="${ (empty user) }">
    Click <a href="login.do">here</a> to login.
  </c:when>
  <c:otherwise>
    Click <a href="manageList.do">here</a> to return to the To Do List.
  </c:otherwise>
</c:choose>

</body>
</html>
