<%--
  Created by IntelliJ IDEA.
  User: kangw
  Date: 12/12/15
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
    <head>
        <meta charset="utf-8"/>
        <title>Favorite Link List</title>
    </head>

    <body>
        <h2>Favorite Item List</h2>

        <jsp:include page="navbar.jsp"></jsp:include>

        <c:forEach var="error" items="${errors}">
            <h3 style="color: red"> ${error} </h3>
        </c:forEach>

        <h2>Favorite for ${otherUser.firstName} ${otherUser.lastName}</h2>

        <p style="font-size: x-large">The list now has ${fn:length(items)} items.</p>
        <table>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>
                        <a href="click.do?${item.id}"> ${item.url} </a>
                    </td>
                </tr>
                <tr>
                    <td>
                            ${item.comment}
                    </td>
                </tr>
                <tr>
                    <td>
                            ${item.count} Clicks
                    </td>
                </tr>
            </c:forEach>
        </table>

        <jsp:include page="navbar-bottom.jsp"></jsp:include>
    </body>
</html>

