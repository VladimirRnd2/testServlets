<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.example.testservlets.entities.Model" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1> Main Page</h1>
<br/>

<h2> Создать новую модель</h2>

<form method="post" action="<c:url value='/'/>">

    <label><input type="text" name="name"></label>Имя Модели<br>

    <label><input type="number" name="quantity"></label>Количество<br>

    <h4>Компания производитель</h4>

        <c:forEach var="company" items="${requestScope.companies}">
            <ul>
                <li>ID:    <c:out value="${company.id}"/></li>
                <li> <c:out value="${company.name} : ${company.country}"/></li>
            </ul>
        </c:forEach>



    <label><input type="number" name="company_id"></label>ID Компании<br>

    <input type="submit" value="Создать Модель" name="Ok"><br>
</form>
<hr> <br>
<form action="<c:url value='/create'/> " method="get">

    <input type="submit" name="new_company" value="Создать Новую Компанию"/>
</form>
<hr>

<h2>Список Моделей</h2><br />

<c:forEach var="model" items="${requestScope.models}">
    <ul>
        <li>ID:         <c:out value="${model.id}"/> </li>

        <li>Модель:     <c:out value="${model.name}"/></li>

        <li>Количество: <c:out value="${model.quantity}"/></li>

        <li>Компания:   <c:out value="${model.company.name}"/></li>

        <li>Страна:     <c:out value="${model.company.country}"/></li>
    </ul>
    <form method="get" action="<c:url value='/delete'/>">
        <input type="number" hidden name="id" value="${model.id}" />
        <input type="submit" value="Удалить"/>
    </form>

    <form method="get" action="<c:url value='/update'/>">
        <input type="number" hidden name="id" value="${model.id}" />
        <input type="submit" value="Редактированть"/>
    </form>
    <hr>
</c:forEach>

</body>
</html>
