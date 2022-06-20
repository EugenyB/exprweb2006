<jsp:useBean id="expressionBean" scope="request" type="com.example.exprweb2006.beans.ExpressionBean"/>
<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 20.06.2022
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit expression</title>
</head>
<body>
    <form action="finish_edit" method="post">
        <label for="expr">Expression</label>
        <input id="expr" type="text" name="expr" value="${expressionBean.edited.expr}">
        <input type="hidden" name="id" value="${expressionBean.edited.id}">
        <input type="submit" value="Edit">
    </form>
</body>
</html>
