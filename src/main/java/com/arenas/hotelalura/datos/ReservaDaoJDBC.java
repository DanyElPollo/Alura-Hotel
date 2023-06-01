package com.arenas.hotelalura.datos;

import static com.arenas.hotelalura.datos.Coneccion.*;
import com.arenas.hotelalura.datos.interfaces.ReservaDAO;
import com.arenas.hotelalura.main.domain.ReservaDTO;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservaDaoJDBC implements ReservaDAO {

    private static final String SQL_SEARCH = "SELECT * FROM reservas WHERE rese_id = ?;";
    private static final String SQL_SELECT = "SELECT * FROM reservas;";
    private static final String SQL_INSERT = "INSERT INTO reservas(rese_fechaEntrada, rese_fechaSalida, rese_valor, rese_medioPago) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE reservas SET rese_fechaEntrada =?, rese_fechaSalida =?, rese_valor =?, rese_medioPago = ? WHERE rese_id = ?;";
    private static final String SQL_DELETE = "DELETE FROM reservas WHERE rese_id = ?;";
    PreparedStatement smtm = null;
    Connection conn = null;
    ResultSet rs = null;

    @Override
    public int insert(Connection conn, ReservaDTO reserva) throws SQLException {
        smtm = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        smtm.setDate(1, Date.valueOf(reserva.getFechaEntrada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        smtm.setDate(2, Date.valueOf(reserva.getFechaSalida().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        smtm.setInt(3, reserva.getValor());
        smtm.setString(4, reserva.getFormaPago());
        smtm.executeUpdate();
        rs = smtm.getGeneratedKeys();
        while (rs.next()) {
            reserva.setId(rs.getInt(1));
        }
        close(smtm);
        return reserva.getId();
    }

    @Override
    public ReservaDTO selectByHuesped(int campo) throws SQLException {
        ReservaDTO reser = null;
        try {
            conn = getConnection();
            smtm = conn.prepareStatement(SQL_SEARCH);
            smtm.setInt(1, campo);
            rs = smtm.executeQuery();
            while (rs.next()) {
                LocalDate fechaEntrada = rs.getTimestamp("rese_fechaEntrada").toLocalDateTime().toLocalDate();
                LocalDate fechaSalida = rs.getTimestamp("rese_fechaSalida").toLocalDateTime().toLocalDate();
                reser = new ReservaDTO(rs.getInt("rese_id"), fechaEntrada, fechaSalida, rs.getInt("rese_valor"), rs.getString("rese_medioPago"));
            }
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            e.printStackTrace();
        } finally {
            close(rs);
            close(smtm);
            close(conn);
        }
        return reser;
    }

    @Override
    public List<ReservaDTO> select() throws SQLException {
        List<ReservaDTO> reservas = new ArrayList<>();
        try {
            conn = getConnection();
            smtm = conn.prepareStatement(SQL_SELECT);
            rs = smtm.executeQuery();
            while (rs.next()) {
                LocalDate fechaEntrada = rs.getTimestamp("rese_fechaEntrada").toLocalDateTime().toLocalDate();
                LocalDate fechaSalida = rs.getTimestamp("rese_fechaSalida").toLocalDateTime().toLocalDate();
                ReservaDTO reser = new ReservaDTO(rs.getInt("rese_id"), fechaEntrada, fechaSalida, rs.getInt("rese_valor"), rs.getString("rese_medioPago"));
                reservas.add(reser);
                System.out.println("reser: " + reser);
            }
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
            e.printStackTrace();
        } finally {
            close(rs);
            close(smtm);
            close(conn);
        }
        return reservas;
    }

    @Override
    public boolean update(ReservaDTO reserva) throws SQLException {
        boolean registro = false;
        try {
            conn = getConnection();
            smtm = conn.prepareStatement(SQL_UPDATE);
            smtm.setDate(1, Date.valueOf(reserva.getFechaEntrada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            smtm.setDate(2, Date.valueOf(reserva.getFechaSalida().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            smtm.setInt(3, reserva.getValor());
            smtm.setString(4, reserva.getFormaPago());
            smtm.setInt(5, reserva.getId());
            registro = (smtm.executeUpdate() > 0);
        } finally {
            smtm.close();
            conn.close();
        }
        return registro;
    }

    @Override
    public boolean delete(int idReserva) throws SQLException {
        boolean registro;
        try ( Connection conn = getConnection();  PreparedStatement smtm = conn.prepareStatement(SQL_DELETE);) {
            smtm.setInt(1, idReserva);
            registro = (smtm.executeUpdate() > 0);
        }
        return registro;
    }

}
