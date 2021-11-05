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
<h1> <%= "Hello World!" %> </h1>
<br/>




<%
    List<Model> modelList = (List<Model>)request.getAttribute("modelList");
    for (Iterator<Model> it = modelList.iterator(); it.hasNext();)
    {
        Model model = it.next();
%>

<tr>
    <form>
        <td><%=model.getId() %> <input type="hidden" value="<%=model.getId() %>" name="id"></td>
        <td><input type="text" value="<%=model.getName() %>" name="first_name"/></td>

        <td><button type="submit">Update</button></td>
    </form>

    <form action="delete_student" method="post">
        <input type="hidden" value="<%=model.getId() %>" name="id">
        <td><button type="submit">Delete</button></td>
    </form>
</tr>
<%  } %>
<h2>Список Моделей</h2><br />


</body>
</html>
