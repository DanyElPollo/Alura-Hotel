package com.arenas.hotelalura;

import com.arenas.hotelalura.datos.ReservaDaoJDBC;
import com.arenas.hotelalura.datos.UsuarioDaoJDBC;
import com.arenas.hotelalura.datos.interfaces.ReservaDAO;
import com.arenas.hotelalura.main.domain.*;
import java.sql.*;
import java.util.Calendar;
import java.util.List;

public class TestUsuario {

    public static void main(String[] args) throws SQLException {
        UsuarioDaoJDBC user = new UsuarioDaoJDBC();

//        insertar(user);
//        login(user);
//        actualizar(user);
//        delete(user);
//        mostrar(user);
    }

    public static void login(UsuarioDaoJDBC user) throws SQLException {
        if (user.login("Elpollo@gmail.com", "Tanque162712") == null) {
            System.out.println("Esta nullo");
        } else {
            System.out.println("No esta nullo");
        }
    }

    public static void mostrar(UsuarioDaoJDBC user) {
        List<UsuarioDTO> users = user.select();
        users.forEach(usuario -> {
            System.out.println(usuario);
        });
    }

    private static void insertar(UsuarioDaoJDBC user) throws SQLException {
        UsuarioDTO usuario = new UsuarioDTO(1003071624, "Daniel", "Arenas", 3108396076L, "Elpollo@gmail.com", "Tanque162712");
        user.insert(usuario);
//        mostrar(user);
    }

    public static void actualizar(UsuarioDaoJDBC user) throws SQLException {
        UsuarioDTO usuario = new UsuarioDTO(1003071621, "Pollo", "Arenas", 3145738001L, "d1003071632@gmail.com", "Perico");
        user.update(usuario);
//        mostrar(user);
    }

    public static void eliminar(UsuarioDaoJDBC user) {
        UsuarioDTO usuario = new UsuarioDTO(1003071621);
        user.delete(usuario);
//        mostrar(user);
    }

    
}
