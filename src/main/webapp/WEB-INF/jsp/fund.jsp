<%--
    Document   : funds
    Created on : 2010-06-02, 18:06:46
    Author     : Tomasz Jankowski
--%>

<%@page contentType="text/html" session="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

        <h1>${fund.name} ${fund.value}</h1>
        <div>
            Jeżeli jesteś zdecydowany na zakup jednostek tego funduszu inwestycyjnego, wybierz rodzaj jednostki,
            wpisz ilość jednostek, a następnie naciśnij przycisk "Kup".
            <div class="fund_units_form">
                <h1>
                    Zakup jednostek
                </h1>
                <form method="post" action="<c:url value="/funds/${fundId}.html"/>">
                    <table>
                        <tr>
                            <td>Rodzaj jednostek:</td>
                            <td>
                                <select name="unitName" class="textfield">
                                    <c:forEach var = "unit" items = "${units}" >
                                        <option value="${unit}">${unit.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Ilość jednostek:</td>
                            <td>
                                <input type="text" name="count" class="textfield"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="errormessage">
                                <c:choose>
                                    <c:when test="${empty param.success}"></c:when>
                                    <c:otherwise>Brak funduszy.</c:otherwise>
                                </c:choose>
                            </td>
                            <td><input type="submit" value="Kup" /></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
