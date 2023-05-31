package com.arenas.hotelalura.datos.interfaces;

import com.arenas.hotelalura.main.domain.HuespedDTO;
import java.sql.*;
import java.util.List;

public interface HuespedDAO {

    public List<HuespedDTO> select() throws SQLException;

    public List<HuespedDTO> search(String Apellido) throws SQLException;

    public boolean insert(Connection conn, HuespedDTO huesped) throws SQLException;

    public boolean update(HuespedDTO huesped) throws SQLException;

    public boolean delete(int idHuesped) throws SQLException;

}
