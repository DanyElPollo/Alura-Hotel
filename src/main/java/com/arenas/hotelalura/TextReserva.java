package com.arenas.hotelalura;

import com.arenas.hotelalura.datos.ReservaDaoJDBC;
import com.arenas.hotelalura.datos.interfaces.ReservaDAO;
import com.arenas.hotelalura.main.controller.ReservaController;
import com.arenas.hotelalura.main.domain.ReservaDTO;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TextReserva {

    public static void main(String[] args) throws SQLException {
        ReservaDAO reser = new ReservaDaoJDBC();
//        insertarReserva(reser);
        int numeros[]={2,3};
//        buscar(numeros);
//        actualizar(reser);
//        eliminar(reser);
//        mostrar(reser);
    }
    
//    public static void buscar(int numeros[]) {
//    ReservaController r = new ReservaController();
//    DefaultTableModel modelo = r.buscarReserva(numeros);
//    for (int i = 0; i < modelo.getRowCount(); i++) {
//        for (int j = 0; j < modelo.getColumnCount(); j++) {
//            System.out.print(modelo.getValueAt(i, j) + "\t");
//        }
//        System.out.println();
//    }
//}


//    public static void insertarReserva(ReservaDAO reser) throws SQLException {
//        ReservaDTO reserva = new ReservaDTO(LocalDate.now(),LocalDate.now().plusDays(1), 569999, "Tarjeta Debito");
//        reser.insert(reserva);
//    }

    public static void mostrar(ReservaDAO reser) throws SQLException {
        List<ReservaDTO> reservas = reser.select();
        reservas.forEach(usuario -> {
            System.out.println(usuario);
        });
    }

    public static void actualizar(ReservaDAO reser) throws SQLException {
        ReservaDTO reserva = new ReservaDTO(2, LocalDate.now(),LocalDate.now().plusDays(1), 599999, "Efectivo");
        if (reser.update(reserva)) {
            JOptionPane.showMessageDialog(null, "Actualizado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
        }
//        mostrar(user);
    }

    public static void eliminar(ReservaDAO reser) throws SQLException {
        if (reser.delete(1)) {
            JOptionPane.showMessageDialog(null, "Eliminado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
        }

    }

}
