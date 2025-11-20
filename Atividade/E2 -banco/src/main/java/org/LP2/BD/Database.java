package org.LP2.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:alma_olhos_porta.db";

    static {
        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                    CREATE TABLE IF NOT EXISTS porta (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        cor TEXT NOT NULL,
                        estado TEXT NOT NULL,
                        destino TEXT NOT NULL
                    )
                    """);

            st.execute("""
                    CREATE TABLE IF NOT EXISTS olhos (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        cor TEXT NOT NULL,
                        aberto INTEGER NOT NULL,
                        nitidez INTEGER NOT NULL
                    )
                    """);

            st.execute("""
                    CREATE TABLE IF NOT EXISTS alma (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        estado TEXT NOT NULL,
                        temperatura INTEGER NOT NULL,
                        conexao INTEGER NOT NULL
                    )
                    """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
