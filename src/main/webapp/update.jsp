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

<br />

<form method="post" action="<c:url value='/update'/>">

    <label>Новое название модели: <input type="text" name="name" value="${requestScope.model.name}" /></label><br>

    <label>Количество моделей: <input type="number" name="quantity" value="${requestScope.model.quantity}" /></label><br>

    <label>ID Компании: <input type="number" name="com_id" value="${requestScope.model.company.id}" /></label><br>

    <h3>Список всех компаний</h3>
    <hr>

    <c:forEach var="company" items="${requestScope.companies}">
        <ul>
            <li>ID:    <c:out value="${company.id}"/></li>
            <li> <c:out value="${company.name} : ${company.country}"/></li>
        </ul>
    </c:forEach>
    <hr>
    <input type="number" hidden name="id" value="${requestScope.model.id}"/>

    <input type="submit" value="Edit" name="Ok"><br>
</form>
</body>
</html>
