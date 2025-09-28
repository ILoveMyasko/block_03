package org.main.block_03

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import groovy.json.JsonOutput

@WebServlet("/addUser")
class AddUserServlet extends HttpServlet {
    @Override
    void doPost(HttpServletRequest req, HttpServletResponse resp) {
        def sql = null
        try {
            def name = req.getParameter("name")
            def email = req.getParameter("email")

            if (!name || name.trim().isEmpty() || !email || email.trim().isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST)
                resp.setContentType("application/json")
                resp.setCharacterEncoding("UTF-8")
                resp.writer << JsonOutput.toJson([success: false, error: "Параметры 'name' и 'email' обязательны"])
                return
            }

            sql = DBUtil.getConnection() //from my custom class

            sql.execute("INSERT INTO users (name, email) VALUES (${name}, ${email})")

            resp.setContentType("application/json")
            resp.setCharacterEncoding("UTF-8")
            resp.setStatus(HttpServletResponse.SC_CREATED) // Статус 201 Created
            resp.writer << JsonOutput.toJson([success: true, message: "Пользователь '${name}' успешно добавлен"])

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            resp.setContentType("application/json")
            resp.setCharacterEncoding("UTF-8")
            resp.writer << JsonOutput.toJson([success: false, error: e.message])
            e.printStackTrace()
        } finally {
            sql?.close()
        }
    }
}