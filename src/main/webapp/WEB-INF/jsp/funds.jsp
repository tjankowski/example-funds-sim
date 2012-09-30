<%-- 
    Document   : funds
    Created on : 2010-06-02, 18:06:46
    Author     : Tomasz Jankowski
--%>

<%@page contentType="text/html" session="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" />
        <title>Towarzystwa Funduszy Inwestycyjnych</title>
    </head>
    <body>
        <div class="header">
            <div class="navigation">
                <sec:authorize access="isAuthenticated()">
                    <a href="<c:url value="/funds.html"/>">Fundusze</a>
                    <a href="<c:url value="/wallet.html"/>">Portfel</a>
                    <a href="<c:url value="/j_spring_security_logout"/>">Wyloguj</a>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <a href="<c:url value="/login.jsp"/>">Zaloguj</a>
                    <a href="<c:url value="/register.html"/>">Zarejestruj</a>
                </sec:authorize>
            </div>
        </div>

        <h1>Towarzystwa Funduszy Inwestycyjnych</h1>
        <div>
            <table width="600px">
                <c:forEach var = "fund" items = "${funds}">
                    <tr>
                        <td>
                            ${fund.name}
                        </td>
                        <td>
                            ${fund.value}
                        </td>
                        <td>
                            <a href="<c:url value="/funds/${fund.id}.html"/>">[Kup]</a>
                        </td>
                         </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
