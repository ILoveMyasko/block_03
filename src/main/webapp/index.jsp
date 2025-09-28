<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Панель управления пользователями</title>
</head>
<body>

<h1>Добавить нового пользователя</h1>

<form action="${pageContext.request.contextPath}/addUser" method="POST">
    <div>
        <label for="name">Имя:</label><br>
        <input type="text" id="name" name="name" required>
    </div>
    <div>
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required>
    </div>
    <button type="submit">Добавить пользователя</button>
</form>

<hr>

<h1>Получить список всех пользователей</h1>


<a href="${pageContext.request.contextPath}/users" target="_blank">Показать JSON со всеми пользователями</a>

</body>
</html>