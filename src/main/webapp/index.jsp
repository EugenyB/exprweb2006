<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Math expressions</title>
</head>
<body>
    <form method="post" action="calculate">
        <label for="expression">Expression to calc:</label>
        <input type="text" id="expression" name="expression">
        <input type="submit" value="Calculate">
    </form>
    <a href="calculate">Show All</a>
</body>
</html>