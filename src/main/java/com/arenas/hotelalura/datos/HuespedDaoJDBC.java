package com.arenas.hotelalura.datos;

import static com.arenas.hotelalura.datos.Coneccion.*;
import com.arenas.hotelalura.datos.interfaces.HuespedDAO;
import com.arenas.hotelalura.main.domain.HuespedDTO;
import com.arenas.hotelalura.main.domain.ReservaDTO;
import java.sql.*;
import java.util.*;

public class HuespedDaoJDBC implements HuespedDAO {

    private static final String SQL_SEARCH = "SELECT * FROM huespedes WHERE hues_apellido LIKE ? OR hues_idReserva = ?;";
    private static final String SQL_SELECT = "SELECT * FROM huespedes;";
    private static final String SQL_INSERT = "INSERT INTO huespedes(hues_nombre, hues_apellido, hues_telefono, hues_fechaNacimiento, hues_nacionalidad,hues_idReserva) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE huespedes SET hues_nombre = ?, hues_apellido = ?, hues_telefono = ?, hues_fechaNacimiento = ?, hues_nacionalidad = ? WHERE hues_id = ?;";
    private static final String SQL_DELETE = "DELETE FROM huespedes WHERE hues_id = ?;";
    ResultSet rs = null;

    @Override
    public List<HuespedDTO> search(String campo) throws SQLException {
        List<HuespedDTO> huespedes = new ArrayList<>();
        try ( Connection conn = getConnection();  PreparedStatement smtm = conn.prepareStatement(SQL_SEARCH)) {
            try {
                smtm.setInt(1, 0);
                smtm.setInt(2, Integer.valueOf(campo));
            } catch (NumberFormatException e) {
                smtm.setString(1, "%" + campo + "%");
                smtm.setInt(2, 0);
            }
            rs = smtm.executeQuery();

            ReservaDTO reserva = new ReservaDTO();
            while (rs.next()) {
                reserva.setId(rs.getInt("hues_idReserva"));
                HuespedDTO h = new HuespedDTO(rs.getInt("hues_id"), rs.getString("hues_nombre"), rs.getString("hues_apellido"),
                        rs.getLong("hues_telefono"), rs.getDate("hues_fechaNacimiento").toLocalDate(),
                        rs.getString("hues_nacionalidad"),
                        reserva);
                huespedes.add(h);
            }
        }
        return huespedes;
    }

    @Override
    public List<HuespedDTO> select() throws SQLException {
        List<HuespedDTO> huespedes = new ArrayList<>();
        try ( Connection conn = getConnection();  PreparedStatement smtm = conn.prepareStatement(SQL_SELECT);  ResultSet rs = smtm.executeQuery()) {
            while (rs.next()) {
                ReservaDTO reserva = new ReservaDTO();
                reserva.setId(rs.getInt("hues_idReserva"));

                HuespedDTO h = new HuespedDTO(rs.getInt("hues_id"), rs.getString("hues_nombre"), rs.getString("hues_apellido"),
                        rs.getLong("hues_telefono"), rs.getDate("hues_fechaNacimiento").toLocalDate(),
                        rs.getString("hues_nacionalidad"), reserva);

                huespedes.add(h);
            }
        }
        return huespedes;
    }

    @Override
    public boolean insert(Connection conn, HuespedDTO huesped) throws SQLException {
        boolean registro;
        try ( PreparedStatement smtm = conn.prepareStatement(SQL_INSERT)) {
            smtm.setString(1, huesped.getNombre());
            smtm.setString(2, huesped.getApellido());
            smtm.setLong(3, huesped.getTelefono());
            smtm.setDate(4, java.sql.Date.valueOf(huesped.getFechaNacimiento()));
            smtm.setString(5, huesped.getNacionalidad());
            smtm.setInt(6, huesped.getReserva().getId());
            registro = smtm.executeUpdate() > 0;
        }
        return registro;
    }

    @Override
    public boolean update(HuespedDTO huesped) throws SQLException {
        boolean registro;
        try ( Connection conn = getConnection();  PreparedStatement smtm = conn.prepareStatement(SQL_UPDATE)) {
            smtm.setString(1, huesped.getNombre());
            smtm.setString(2, huesped.getApellido());
            smtm.setLong(3, huesped.getTelefono());
            smtm.setDate(4, java.sql.Date.valueOf(huesped.getFechaNacimiento()));
            smtm.setString(5, huesped.getNacionalidad());
            smtm.setInt(6, huesped.getId());
            registro = smtm.executeUpdate() > 0;
        }
        return registro;
    }

    @Override
    public boolean delete(int idHuesped) throws SQLException {
        boolean registro;
        try ( Connection conn = getConnection();  PreparedStatement smtm = conn.prepareStatement(SQL_DELETE)) {
            smtm.setInt(1, idHuesped);
            registro = smtm.executeUpdate() > 0;
        }
        return registro;
    }

}
