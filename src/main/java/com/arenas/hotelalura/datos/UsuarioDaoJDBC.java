package com.arenas.hotelalura.datos;

import com.arenas.hotelalura.datos.interfaces.UsuarioDAO;
import com.arenas.hotelalura.main.domain.UsuarioDTO;
import java.sql.*;
import java.util.*;
import static com.arenas.hotelalura.datos.Coneccion.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDaoJDBC implements UsuarioDAO {

    private static final String SQL_SELECT = "SELECT user_dni, user_nombre, user_apellido, user_telefono, user_correo, user_contrasena FROM usuarios;";
    private static final String SQL_LOGIN = "SELECT * FROM usuarios WHERE user_correo = ? AND user_contrasena = ?;";
    private static final String SQL_INSERT = "INSERT INTO usuarios(user_dni, user_nombre, user_apellido, user_telefono, user_correo, user_contrasena) VALUES(?,?,?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE user_dni = ?;";
    private static final String SQL_UPDATE = "UPDATE usuarios SET user_nombre = ?, user_apellido = ?, user_telefono = ?,user_correo = ?, user_contrasena = ? WHERE user_dni = ?;";
    private static Connection conn = null;
    private static PreparedStatement smtm = null;
    private static ResultSet rs = null;
    private static UsuarioDTO usuario = null;

    @Override
    public UsuarioDTO login(String email, String pass) throws SQLException {
        conn = getConnection();
        if (conn != null) {
            smtm = conn.prepareStatement(SQL_LOGIN);
            smtm.setString(1, email);
            smtm.setString(2, pass);
//                smtm.setString(2, hashSHA256(pass));
            rs = smtm.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioDTO(rs.getInt(1), rs.getString(2), rs.getString(3), Long.parseLong(rs.getString(4)), rs.getString(5), rs.getString(6));
            }
        }
        close(smtm);
        close(conn);

        return usuario;
    }

    @Override
    public List<UsuarioDTO> select() {
        List<UsuarioDTO> usuarios = new ArrayList<>();

        try {
            conn = getConnection();
            if (conn != null) {
                smtm = conn.prepareStatement(SQL_SELECT);
                rs = smtm.executeQuery();
                while (rs.next()) {
                    usuario = new UsuarioDTO(rs.getInt(1), rs.getString(2), rs.getString(3), Long.parseLong(rs.getString(4)), rs.getString(5), rs.getString(6));
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException ex) {
            System.out.println("ex = " + ex.getMessage());
        } finally {
            close(rs);
            close(smtm);
            close(conn);
        }
        return usuarios;
    }

    @Override
    public int insert(UsuarioDTO user) {
        int registro = 0;
        try {
            conn = getConnection();
            smtm = conn.prepareStatement(SQL_INSERT);
            smtm.setInt(1, user.getId());
            smtm.setString(2, user.getNombre());
            smtm.setString(3, user.getApellido());
            smtm.setLong(4, user.getTelefono());
            smtm.setString(5, user.getEmail());
            smtm.setString(6, user.getContra());
//            smtm.setString(6, hashSHA256(user.getContra()));
            registro = smtm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
            System.out.println("eSQL = " + e.getSQLState());
        } finally {
            close(smtm);
            close(conn);
        }
        return registro;
    }

    @Override
    public int update(UsuarioDTO user) {
        int registro = 0;
        try {
            conn = getConnection();
            smtm = conn.prepareStatement(SQL_UPDATE);
            smtm.setString(1, user.getNombre());
            smtm.setString(2, user.getApellido());
            smtm.setLong(3, user.getTelefono());
            smtm.setString(4, user.getEmail());
            smtm.setString(5, user.getContra());
            smtm.setInt(6, user.getId());
            registro = smtm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ex = " + ex.getMessage());
        } finally {
            close(smtm);
            close(conn);
        }

        return registro;
    }

    @Override
    public int delete(UsuarioDTO user) {
        int registro = 0;
        try {
            conn = getConnection();
            smtm = conn.prepareStatement(SQL_DELETE);
            smtm.setInt(1, user.getId());
            registro = smtm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ex = " + ex.getMessage());
            System.out.println("exSql = " + ex.getSQLState());
        }
        return registro;
    }

    public static String hashSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
