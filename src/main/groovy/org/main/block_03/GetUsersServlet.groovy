package org.main.block_03

import groovy.sql.Sql
import groovy.json.JsonOutput
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet("/users")
class GetUsersServlet extends HttpServlet {
    @Override
    void doGet(HttpServletRequest req, HttpServletResponse resp) {
        def sql = null
        try {
            sql = Sql.newInstance("jdbc:postgresql://localhost:5432/mywebappdb",
                    "postgres",
                    "postgres",
                    "org.postgresql.Driver")

            def users = sql.rows("SELECT * FROM users ORDER BY id")

            resp.setContentType("application/json")
            resp.setCharacterEncoding("UTF-8")
            resp.writer << JsonOutput.toJson(users)

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            resp.setContentType("application/json")
            resp.setCharacterEncoding("UTF-8")
            resp.writer << JsonOutput.toJson([error: e.message])
            e.printStackTrace()
        } finally {
            sql?.close()
        }
    }
}