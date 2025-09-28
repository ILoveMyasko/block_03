package org.main.block_03

import groovy.sql.Sql

class DBUtil {

    static Sql getConnection() {
        // Считываем хост БД из переменной окружения DB_HOST. Если ее нет, используем "localhost".
        def dbHost = System.getenv("DB_HOST") ?: "localhost"

        def dbName = System.getenv("DB_NAME") ?: "mywebappdb"

        def dbUser = System.getenv("DB_USER") ?: "postgres"

        def dbPassword = System.getenv("DB_PASS") ?: "postgres"

        def jdbcUrl = "jdbc:postgresql://${dbHost}:5432/${dbName}"

        def dbDriver = "org.postgresql.Driver"
        println "Connecting to database: ${jdbcUrl}"

        return Sql.newInstance(jdbcUrl, dbUser, dbPassword, dbDriver)
    }
}