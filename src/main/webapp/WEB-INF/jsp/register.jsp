<%--
    Document   : createContest
    Author     : Tomasz Jankowski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" />
        <title>Rejestracja</title>
    </head>
    <body>
		<div class="header">
	        <div class="navigation">
	            <sec:authorize access="isAuthenticated()">
	                <a href="<c:url value="/j_spring_security_logout"/>">Wyloguj</a>
	            </sec:authorize>
	            <sec:authorize access="isAnonymous()">
	                <a href="<c:url value="/login.jsp"/>">Zaloguj</a>
	            </sec:authorize>
	        </div>
        </div>
        <div class="login_form">
        	<h1>
                Rejestracja
            </h1>
            <form:form commandName="user" method="POST" action="register.html" class="tcolor">
                <table>
                    <tr>
                        <td>Login:</td>
                        <td><form:input path="userName" autocomplete="false" /></td>
                        <td class="errormessage"><form:errors path="userName" /></td>
                    </tr>
                    <tr>
                        <td>Hasło:</td>
                        <td><form:password path="password" autocomplete="false" /></td>
                        <td class="errormessage"><form:errors path="password" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <input type="submit" value="Zarejestruj" />
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </body>
</html>
