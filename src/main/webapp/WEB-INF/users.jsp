<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <%-- Подключаем библиотеку JSTL --%>

<!DOCTYPE html>
<html>
<head>
  <title>Список пользователей</title>
  <style>
    body { font-family: sans-serif; margin: 2em; }
    table { border-collapse: collapse; width: 60%; margin-top: 1em; }
    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    th { background-color: #f2f2f2; }
    a { text-decoration: none; color: #007bff; }
  </style>
</head>
<body>

<h1>Список всех пользователей</h1>

<c:choose>
  <%-- Проверяем, не пустой ли список, который передал сервлет --%>
  <c:when test="${not empty usersList}">
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Email</th>
      </tr>
      </thead>
      <tbody>
        <%-- Проходим по каждому элементу в списке usersList --%>
      <c:forEach var="user" items="${usersList}">
        <tr>
          <td><c:out value="${user.id}"/></td>
          <td><c:out value="${user.name}"/></td>
          <td><c:out value="${user.email}"/></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:when>
  <%-- Если список пуст, выводим сообщение --%>
  <c:otherwise>
    <p>В базе данных нет ни одного пользователя.</p>
  </c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/">Вернуться на главную</a></p>

</body>
</html>