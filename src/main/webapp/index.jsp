<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Управление пользователями</title>
    <style>
        body { font-family: sans-serif; margin: 2em; }
        .message { padding: 1em; margin-bottom: 1em; border-radius: 5px; }
        .message.success { background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .message.error { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
    </style>
</head>
<body>

<h1>Панель управления</h1>

<%-- Этот блок будет проверять, есть ли сообщение в сессии --%>
<%
    String message = (String) session.getAttribute("message");
    if (message != null) {
        // Определяем стиль для сообщения
        String messageClass = message.startsWith("Ошибка:") ? "error" : "success";
%>
<div class="message <%= messageClass %>">
    <%= message %>
</div>
<%
        // Важно! Удаляем сообщение из сессии, чтобы оно не появилось снова при обновлении страницы
        session.removeAttribute("message");
    }
%>

<h2>Добавить нового пользователя</h2>
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

<%-- Просто ссылка на сервлет, который покажет список пользователей --%>
<a href="${pageContext.request.contextPath}/users">Посмотреть список всех пользователей</a>

</body>
</html>