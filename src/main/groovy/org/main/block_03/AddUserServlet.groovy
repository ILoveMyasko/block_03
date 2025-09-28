package org.main.block_03

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import groovy.sql.Sql

@WebServlet("/addUser")
class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        def sql = null
        try {
            def name = req.getParameter("name")
            def email = req.getParameter("email")

            if (!name || name.trim().isEmpty() || !email || email.trim().isEmpty()) {
                // Если данные неверны, кладем ошибку в сессию и возвращаемся
                req.getSession().setAttribute("message", "Ошибка: Имя и email не могут быть пустыми.")
                resp.sendRedirect(req.getContextPath() + "/")
                return
            }

            sql = Sql.newInstance("jdbc:postgresql://localhost:5432/mywebappdb",
                    "postgres",
                    "postgres",
                    "org.postgresql.Driver")
            sql.execute("INSERT INTO users (name, email) VALUES (${name}, ${email})")


            req.getSession().setAttribute("message", "Пользователь '${name}' успешно добавлен!".toString())
            
            resp.sendRedirect(req.getContextPath() + "/")

        } catch (Exception e) {
            req.getSession().setAttribute("message",
                    "Ошибка при добавлении пользователя: " + e.getMessage().toString())
            resp.sendRedirect(req.getContextPath() + "/")
            e.printStackTrace()
        } finally {
            sql?.close()
        }
    }
}