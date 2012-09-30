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
        <script type="text/javascript" src="<c:url value="/js/scripts.js"/>" ></script>
        <script type="text/javascript" src="<c:url value="/js/jquery.js"/>" ></script>
        <title>Portfel</title>
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

        <h1>Twój portfel</h1>
        <div class="padding_10  bottom_line marging_bottom_10">
              Dostępne środki: ${wallet.money}
        </div>
        <div>
            <form method="post" action="<c:url value="/wallet.html"/>">
                <table width="800px">
                    <thead>
                        <tr>
                            <th>Fundusz</th>
                            <th>Ilość</th>
                            <th></th>
                        </tr>
                    </thead>
                   
                    <c:forEach var = "item" varStatus="status" items = "${wallet.items}">
                        <tr>
                            <td  width="400px">
                                ${item.fund.name} - ${item.fundUnit.name}
                            </td>
                            <td  width="100px" class="center_text">
                                ${item.quantity}
                            </td>
                            <td id="sell_${status.count-1}"  width="300px">
                                <a href="javascript:void(0)" onclick="sellItem(${status.count-1})">[Sprzedaj]</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <input id="itemId" type="hidden" name="item"/>
                <span id="sellInput" class="hide">
                    <input type="text" name="count" class="width_50"/>
                    <input type="submit" value="Potwierdź" />
                </span>
            </form>
        </div>
    </body>
</html>
