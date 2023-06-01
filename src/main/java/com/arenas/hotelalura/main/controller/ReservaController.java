package com.arenas.hotelalura.main.controller;

import com.arenas.hotelalura.datos.interfaces.Editable;
import com.arenas.hotelalura.datos.ReservaDaoJDBC;
import com.arenas.hotelalura.datos.interfaces.ReservaDAO;
import com.arenas.hotelalura.main.domain.ReservaDTO;
import com.arenas.hotelalura.main.views.RegistroHuesped;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReservaController implements Editable {

    ReservaDAO reserva = new ReservaDaoJDBC();
    private HuespedController hd = new HuespedController();
    private boolean exito;

    public void seguirAHuesped(LocalDate fechaEntrada, LocalDate fechaSalida, String valor, String formaPago) {
        ReservaDTO res = new ReservaDTO(fechaEntrada, fechaSalida, Integer.parseInt(valor), formaPago);
        hd.setReserva(res);
        RegistroHuesped registro = new RegistroHuesped();
        registro.setVisible(true);
    }

    public DefaultTableModel reservas(DefaultTableModel m) throws SQLException {
        for (ReservaDTO r : reserva.select()) {
            System.out.println("r = " + r);
            Object[] rowDatos = {r.getId(), r.getFechaEntrada(), r.getFechaSalida(), r.getFormaPago(), r.getValor()};
            m.addRow(rowDatos);
        }
        return m;
    }

    @Override
    public boolean editar(Object[] s) {
        for(int i = 0; i< s.length ; i++){
            System.out.println(s[i]);
        }
        
        int id = Integer.parseInt(s[0].toString());
        LocalDate check_in = LocalDate.parse(s[1].toString());
        LocalDate check_out = LocalDate.parse(s[2].toString());
        String pago = (String) s[3];
        int Total = Integer.parseInt(s[4].toString());
        
        ReservaDTO rd = new ReservaDTO(id, check_out, check_in, Total, pago);
        try {
            exito = reserva.update(rd);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al momento de guardar.");
            exito = false;
        }
        return exito;
    }

    public void eliminarRegistro(int id) throws SQLException {
        reserva.delete(id);
    }
    
    public Object[] buscarReserva(List<Integer> codigo) {
        Object[] rowDatos = null;
        try {
            for (int id : codigo) {
                ReservaDTO res = reserva.selectByHuesped(id);
                Object[] row = {res.getId(), res.getFechaEntrada(), res.getFechaSalida(), res.getFormaPago(), res.getValor()};
                rowDatos = row;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al filtrar: " + ex.getMessage());
        }
        return rowDatos;
    }
}
