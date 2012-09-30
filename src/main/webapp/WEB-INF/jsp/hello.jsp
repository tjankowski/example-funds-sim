<%-- 
    Document   : hello
    Created on : 2010-05-30, 22:26:32
    Author     : tomek
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
        <title>Symulator oszczędzania w Towarzystwie Funduszy Inwestycyjnych</title>
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

        <h1>Witaj w symulatorze oszczędzania w Towarzystwie Funduszy Inwestycyjnych</h1>
        <div>
            Symulator pomoże Ci w zrozumieniu działania funduszy inwestycyjnych i ułatwi wybór
            odpowiedniego funduszu.
        </div>
    </body>
</html>
