<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" />
        <title>Logowanie</title>
    </head>

    <body onload='document.loginForm.j_username.focus();'>
        <div class="header">
            <div class="navigation">
                <sec:authorize access="isAnonymous()">
                    <a href="<c:url value="/register.html"/>">Zarejestruj</a>
                </sec:authorize>
            </div>
        </div>
        <div class="login_form">
            <h1>
                Logowanie
            </h1>
            <form id="loginForm" name="loginForm" action="j_spring_security_check" method="post">
                <table>
                    <tr>
                        <td>Login:</td>
                        <td>
                            <input class="textfield" id="usernameField" type="text" name="j_username" value="<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Hasło:</td>
                        <td>
                            <input class="textfield" id="passwordField" type="password" name="j_password" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <input type="submit" value="Zaloguj" />
                        </td>
                    </tr>
                </table>
            </form>
            <c:if test="${not empty param.login_error}">
                <div class="errormessage">
                    <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                </div>
            </c:if>
        </div>
    </body>

</html>