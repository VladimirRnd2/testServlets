<%--
  Created by IntelliJ IDEA.
  User: vladimir
  Date: 06.11.2021
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Company</title>
</head>
<body>
<h4>ID: ${requestScope.company.id}</h4>
<div>Компания: <c:out value="${requestScope.company.name}"/> </div>
<div>Страна: <c:out value="${requestScope.company.country}"/> </div>

<hr>

<h2>Вы уверены, что хотите удалить компанию?</h2>
<form action="<c:out value='/create'/>" method="get">
    <input type="submit" name="back" value="Вернуться">
</form>
<form action="<c:url value='/deleteCompany'/>" method="post">
    <label>Введите для подтверждения id модели и нажмите "Удалить" <input type="number" name="id" value="${requestScope.company.id}" /></label><br>
    <input type="submit" name="delete_model" value="Удалить модель № ${requestScope.company.id} ?">
</form>

</body>
</html>
