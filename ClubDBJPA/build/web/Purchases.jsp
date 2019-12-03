<%-- 
    Document   : Purchases
    Created on : Nov 18, 2019, 10:53:47 PM
    Author     : Tom Valli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Purchases</title>
    </head>
    <body>
        <h1>Transactions on File for:</h1>
        <h2>${m.memid}</h2>
        <h2>${m.firstname} ${m.lastname}</h2>
        <br>
        <table border="1">
            <tr>
                <th>Purchase Dt</th>
                <th>Trans Type</th>
                <th>Trans Cd</th>
                <th>Trans Desc</th>
                <th>Amount</th>
            </tr>
            <c:forEach var="p" items="${pur}">
                <tr>
                    <td align="right">${p.purchasedtS}</td>
                    <td align="right">${p.transtype}</td>
                    <td align="right">${p.transcd}</td>
                    <td align="right">${p.transDesc}</td>
                    <td align="right">${p.amountS}</td>
                </tr>
            </c:forEach>                
        </table>
        <br>${msg}
        <br><a href="MemberScreen.jsp">Back to Member Screen</a>
    </body>
</html>
