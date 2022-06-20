<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="expressionBean" scope="request" type="com.example.exprweb2006.beans.ExpressionBean"/>
<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 20.06.2022
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Expressions</title>
    <link rel="stylesheet" type="text/css" href="style.css" >
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>expr</th>
                <th>result</th>
                <th>edit</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${expressionBean.expressions}" var="e">
            <tr>
                <td>${e.id}</td>
                <td>${e.expr}</td>
                <td>${e.value}</td>
                <td><a href="edit?id=${e.id}">edit</a> </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
