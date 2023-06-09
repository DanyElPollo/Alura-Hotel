package com.arenas.hotelalura.main.controller;

import com.arenas.hotelalura.datos.interfaces.Editable;
import com.arenas.hotelalura.datos.HuespedDaoJDBC;
import com.arenas.hotelalura.datos.TransaccionDaoJDBC;
import com.arenas.hotelalura.datos.interfaces.HuespedDAO;
import com.arenas.hotelalura.datos.interfaces.TransaccionDAO;
import com.arenas.hotelalura.main.domain.HuespedDTO;
import com.arenas.hotelalura.main.domain.ReservaDTO;
import com.arenas.hotelalura.main.views.Exito;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HuespedController implements Editable{

    private HuespedDAO hdao;
    private HuespedDTO hd;
    private static ReservaDTO reserva;
    private boolean exito;

    public void guardarDatos(String nombre, String apellido, long numero, LocalDate fechaNacimiento, String nacionalidad) {
        hd = new HuespedDTO(nombre, apellido, numero, fechaNacimiento, nacionalidad, reserva);
        TransaccionDAO td = new TransaccionDaoJDBC();
        try {
            Exito ex = new Exito(td.transaccion(hd));
            ex.setLocationRelativeTo(null);
            ex.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public DefaultTableModel select(DefaultTableModel m) throws SQLException {
        hdao = new HuespedDaoJDBC();
        for (HuespedDTO h : hdao.select()) {
            Object[] rowDatos = {h.getId(), h.getNombre(), h.getApellido(), h.getFechaNacimiento(), h.getNacionalidad(), h.getTelefono(), h.getReserva().getId()};
            m.addRow(rowDatos);
        }
        return m;
    }

    public boolean eliminar(int id) {
        hdao = new HuespedDaoJDBC();
        boolean elim = false;
        try {
            elim = hdao.delete(id);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un problema: " + ex.getMessage());
        }
        return elim;
    }

    @Override
    public boolean editar(Object[] datosHuesped) {
        int id = (int) datosHuesped[0];
        String nombre = (String) datosHuesped[1];
        String apellido = (String) datosHuesped[2];
        long telefono = (long) datosHuesped[5];
        LocalDate fechaNacimiento = (LocalDate) datosHuesped[3];
        String nacionalidad = (String) datosHuesped[4];

        HuespedDTO huesped = new HuespedDTO(id, nombre, apellido, telefono, fechaNacimiento, nacionalidad);

        HuespedDaoJDBC huespedDao = new HuespedDaoJDBC();
        

        try {
            exito = huespedDao.update(huesped);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un problema al editar el huésped: " + ex.getMessage());
            exito = false;
        }

        return exito;
    }

    public void setReserva(ReservaDTO res) {
        this.reserva = res;
    }
}
