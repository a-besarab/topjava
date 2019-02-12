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
<a href="meals?action=add">Add some food</a>
<table>
    <tr>
        <td>Дата/Время</td>
        <td>Описание</td>
        <td>Калории</td>
        <td>Редактировать</td>
        <td>Удалить</td>
    </tr>
    <c:forEach var="item" items="${meal}">
        <jsp:useBean id="item" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr class="${item.excess ? 'red' : 'green'}">
            <td>${item.dateTime.toLocalDate()} ${item.dateTime.toLocalTime()}</td>
            <td>${item.description}</td>
            <td>${item.calories}</td>
            <td><a href="meals?mealId=${item.id}&action=edit">edit</a></td>
            <td><a href="meals?mealId=${item.id}&action=delete">delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
