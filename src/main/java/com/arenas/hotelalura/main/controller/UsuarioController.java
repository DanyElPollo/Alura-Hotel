package com.arenas.hotelalura.main.controller;

import com.arenas.hotelalura.datos.interfaces.UsuarioDAO;
import com.arenas.hotelalura.datos.UsuarioDaoJDBC;
import com.arenas.hotelalura.main.domain.UsuarioDTO;
import com.arenas.hotelalura.main.views.MenuUsuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuarioController {

    UsuarioDAO userDao = new UsuarioDaoJDBC();

//    los datos estan llegando de manera sastifactoria
    public boolean datos(String usuario, String pass) {
        try {
            if (userDao.login(usuario, pass) == null) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al validar los datos, por favor contactar con Soporte."
                    + "error: " +ex.getMessage());
        }
        return false;
    }

}
