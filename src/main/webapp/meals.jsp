<%@ page import="ru.javawebinar.topjava.util.TestDataMeals" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Моя еда</h2>
<table>
    <tr>
        <td>Дата/Время</td>
        <td>Описание</td>
        <td>Калории</td>
    </tr>
    <c:forEach var="item" items="${meal}">
        <jsp:useBean id="item" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr class="${item.excess ? 'red' : 'green'}">
            <td>${item.dateTime.toLocalDate()} ${item.dateTime.toLocalTime()}</td>
            <td>${item.description}</td>
            <td>${item.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
