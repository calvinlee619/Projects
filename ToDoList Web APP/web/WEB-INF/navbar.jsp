<%--
  Created by IntelliJ IDEA.
  User: kangw
  Date: 12/12/15
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table cellpadding="4" cellspacing="0">
  <tr>
    <!-- Banner row across the top -->
    <td width="130" bgcolor="#99FF99"></td>
    <td bgcolor="#99FF99">&nbsp;  </td>
    <td width="500" bgcolor="#99FF99">
    </td>
  </tr>

  <!-- Spacer row -->
  <tr>
    <td bgcolor="#99FF99" style="font-size:5px">&nbsp;</td>
    <td colspan="2" style="font-size:5px">&nbsp;</td>
  </tr>

  <tr>
    <td bgcolor="#99FF99" valign="top" height="500">
      <!-- Navigation bar is one table cell down the left side -->
      <p align="left">

        <c:choose>
          <c:when test="${ (empty user) }">
            <span class="menu-item"><a href="login.do">Login</a></span><br/>
            <span class="menu-item"><a href="register.do">Register</a></span><br/>
          </c:when>

          <c:otherwise>
            <span class="menu-head">${user.firstName} ${user.lastName}</span><br/>
            <span class="menu-item"><a href="manageList.do">Manage Your Lists</a></span><br/>
            <span class="menu-item"><a href="change-pwd.do">Change Password</a></span><br/>
            <span class="menu-item"><a href="logout.do">Logout</a></span><br/>
            <span class="menu-item">&nbsp;</span><br/>
          </c:otherwise>
        </c:choose>

        <c:forEach var="otheruser" items="${userList}">
          <span class="menu-item">
            <a href="otherUser.do?${otheruser.id}">
              ${otheruser.firstName} ${otheruser.lastName}
            </a>
          </span>
          <br/>
        </c:forEach>
      </p>
    </td>

    <td>
      <!-- Padding (blank space) between navbar and content -->
    </td>
    <td  valign="top">

