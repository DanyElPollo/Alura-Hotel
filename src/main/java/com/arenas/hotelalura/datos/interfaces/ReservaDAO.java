package com.arenas.hotelalura.datos.interfaces;

import com.arenas.hotelalura.main.domain.ReservaDTO;
import java.sql.*;
import java.util.List;

public interface ReservaDAO {

    public ReservaDTO selectByHuesped(int campo) throws SQLException;

    public List<ReservaDTO> select() throws SQLException;

    public int insert(Connection conn, ReservaDTO reserva) throws SQLException;

    public boolean update(ReservaDTO reserva) throws SQLException;

    public boolean delete(int idReserva) throws SQLException;

}
