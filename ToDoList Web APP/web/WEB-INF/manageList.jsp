<%--
  Created by IntelliJ IDEA.
  User: kangw
  Date: 12/12/15
  Time: 1:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
    <head>
        <meta charset="utf-8"/>
        <title>Favorite Link ManageList</title>
    </head>

    <body>
        <h2>Favorite Item ManageList</h2>

        <jsp:include page="navbar.jsp"></jsp:include>

    <c:forEach var="error" items="${errors}">
        <h3 style="color: red"> ${error} </h3>
    </c:forEach>

        <h2>Favorite for ${user.firstName} ${user.lastName}</h2>
        
            <form method="POST" action="add.do">
                <table>
                    <tr>
                        <td colspan="3">
                            <hr/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td style="font-size: large">URL:</td>
                        
                        <td colspan="2"><input type="text" size="40" name="url"/></td>
                    </tr>
                    
                    <tr>
                        <td style="font-size: large">Comment:</td>
                        
                        <td colspan="2"><input type="text" size="40" name="comment"/></td>
                        
                    </tr>
                    
                    <tr>
                        <td></td>
                        
                        <td><input type="submit" name="add" value="Add Favorite"/></td>
                        
                        <td><a href="logout.do">logout</a></td>
                        
                    </tr>
                    
                    <tr>
                        <td colspan="3">
                            <hr/>
                        </td>
                    </tr>
                    
                </table>
            </form>


            <p style="font-size: x-large">The list now has ${fn:length(items)} items.</p>
            <table>
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td>
                            <form method="POST" action="delete.do">
                                <input type="hidden" name="id" value="${item.id}"/>
                                <input type="submit" value="X"/>
                            </form>
                        </td>
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
