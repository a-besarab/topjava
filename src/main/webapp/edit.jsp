<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Edit/Add meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Редактирование/Добавление еды</h2>
<%Meal meal = (Meal) request.getAttribute("meal");%>
<form method="post" action="meals" enctype="application/x-www-form-urlencoded">
    <table>
        <tr>
            <td>ID еды :</td>
            <td><input type="text" readonly="readonly" name="id" value="<%=meal.getId()%>" required/></td>
        </tr>
        <tr>
            <td>Дата/Время :</td>
            <td><input type="datetime-local" name="dateTime" value="<%=meal.getDateTime()%>" required/></td>
        </tr>
        <tr>
            <td>Описание :</td>
            <td><input type="text" name="description" value="<%=meal.getDescription()%>" required/></td>
        </tr>
        <tr>
            <td>Калории :</td>
            <td><input type="number" name="calories" value="<%=meal.getCalories()%>" required/></td>
        </tr>
    </table>
    <input type="submit" value="Применить"/>
    <input onclick="window.history.go(-1); return false;" type="button" value="Отменить"/>
</form>
</body>
</html>