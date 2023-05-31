package com.arenas.hotelalura.datos;

import static com.arenas.hotelalura.datos.Coneccion.getConnection;
import com.arenas.hotelalura.datos.interfaces.*;
import com.arenas.hotelalura.main.domain.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class TransaccionDaoJDBC implements TransaccionDAO {

    @Override
    public int transaccion(HuespedDTO hd) throws SQLException {
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);

            ReservaDAO reservaDAO = new ReservaDaoJDBC();
            int idReserva = reservaDAO.insert(conn, hd.getReserva());
            hd.getReserva().setId(idReserva);

            // Guardar el huesped en la base de datos
            HuespedDAO huespedDAO = new HuespedDaoJDBC();
            huespedDAO.insert(conn, hd);

            conn.commit();
            return idReserva;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    return 0;// Realizar rollback en caso de excepción
                } catch (SQLException rollbackEx) {
                    System.out.println("SQLException: " + rollbackEx.getMessage());
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    System.out.println("SQLException: " + closeEx.getMessage());
                }
            }
        }
    }

    public static int lineNumber() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int lineNumber = stackTrace[1].getLineNumber();
        return lineNumber;
    }

    public static int mostrarJO(Object o) {
        JOptionPane.showMessageDialog(null, o);
        return 1;
    }

}
