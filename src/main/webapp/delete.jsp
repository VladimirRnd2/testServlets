<%--
  Created by IntelliJ IDEA.
  User: vladimir
  Date: 05.11.2021
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h4>ID: ${requestScope.model.id}</h4>
<div>Имя: <c:out value="${requestScope.model.name}"/> </div>
<div>Количество: <c:out value="${requestScope.model.quantity}"/> </div>
<div>Компания: <c:out value="${requestScope.model.company.name} (${requestScope.model.company.country})"/> </div>
<hr>

<h2>Вы уверены, что хотите удалить модель?</h2>
<form action="<c:out value='/'/>" method="get">
    <input type="submit" name="back" value="Вернуться на главную страницу">
</form>
<form action="<c:url value='/delete'/>" method="post">
    <label>Введите для подтверждения id модели и нажмите "Удалить" <input type="number" name="id" value="${requestScope.model.id}" /></label><br>
    <input type="submit" name="delete_model" value="Удалить модель № ${requestScope.model.id} ?">
</form>
</body>
</html>
