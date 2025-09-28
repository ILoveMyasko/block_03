package org.main.block_03

import groovy.sql.Sql
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

// Можно поменять URL на более понятный, например "/list-users"
@WebServlet("/users")
class GetUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        def sql = null
        try {
            sql = Sql.newInstance("jdbc:postgresql://localhost:5432/" +
                    "mywebappdb",
                    "postgres",
                    "postgres",
                    "org.postgresql.Driver")
            def users = sql.rows("SELECT id, name, email FROM users ORDER BY id")
            req.setAttribute("usersList", users)

            // 2. Передаем управление (forward) на JSP-страницу для отображения
            // Помещаем JSP в /WEB-INF/, чтобы к ней нельзя было обратиться напрямую по URL
            def dispatcher = req.getRequestDispatcher("/WEB-INF/users.jsp")
            dispatcher.forward(req, resp)

        } catch (Exception e) {
            // В случае ошибки можно показать красивую страницу ошибки
            e.printStackTrace()
            resp.getWriter().println("Произошла ошибка при загрузке пользователей: " + e.getMessage())
        } finally {
            sql?.close()
        }
    }
}