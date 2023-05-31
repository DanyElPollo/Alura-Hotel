package com.arenas.hotelalura.datos.interfaces;

import com.arenas.hotelalura.main.domain.UsuarioDTO;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    
    public UsuarioDTO login(String email, String pass) throws SQLException ;

    public List<UsuarioDTO> select() throws SQLException;

    public int insert(UsuarioDTO user) throws SQLException;

    public int update(UsuarioDTO user) throws SQLException;

    public int delete(UsuarioDTO user) throws SQLException;

}
