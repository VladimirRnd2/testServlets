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

<h2> Список существующих компаний </h2>
<hr>
<c:forEach var="company" items="${requestScope.companies}">
    <ul>
        <li>ID:    <c:out value="${company.id}"/></li>
        <li> <c:out value="${company.name} : ${company.country}"/></li>

        <form method="get" action="<c:url value='/deleteCompany'/>">
            <input type="number" hidden name="id" value="${company.id}" />
            <input type="submit" value="Удалить"/>
        </form>
    </ul>
</c:forEach>

<hr>
<h2>Создание новой компании</h2>
<form action="<c:out value='/create'/>" method="post">
    <label><input type="text" name="name"></label>Имя Компании<br>
    <label><input type="text" name="country"></label>Страна<br>
    <input type="submit" name="create" value="Создать новую Компанию">
</form>
</body>
</html>
