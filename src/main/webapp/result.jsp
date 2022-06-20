<jsp:useBean id="expressionBean" scope="request" type="com.example.exprweb2006.beans.ExpressionBean"/>
<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 20.06.2022
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
    <h1>${expressionBean.current}</h1>
    <h2>${expressionBean.message}</h2>
</body>
</html>
