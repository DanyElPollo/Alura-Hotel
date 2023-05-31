package com.arenas.hotelalura.datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Coneccion {

    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3308/hotel_alura?useTimeZone=true&serverTimeZone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";

    
    
//    pool de conexiones
    public static DataSource getDataSource() {
        BasicDataSource s = new BasicDataSource();
        s.setUrl(JDBC_URL);
        s.setUsername(JDBC_USER);
        s.setPassword(JDBC_PASSWORD);
        // Definir tamaño inicial;
        s.setInitialSize(10);
        return s;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    
//    Esto puede ser reemplazado en los metodos try-with-source para hacer un cierre automaticamente sin tener que crear este metodo
//    lo dejo por aqui por si algun pierde
    public static void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            System.out.println("E: " + e.getMessage());
        }
    }

    /*
    Estos metodos son lo mismo a usar el metodo generico que tiene por parametro la interface AutoCloseable;
     */
//    public static void close(Connection conn) throws SQLException {
//        conn.close();
//    }
//
//    public static void close(ResultSet rs) throws SQLException {
//        rs.close();
//    }
//
//    public static void close(Statement smtm) throws SQLException {
//        smtm.close();
//    }
//
//    public static void close(PreparedStatement smtm) throws SQLException {
//        smtm.close();
//    }
}
