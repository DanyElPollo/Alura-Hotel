package com.arenas.hotelalura.main.views;

import com.arenas.hotelalura.datos.interfaces.Editable;
import com.arenas.hotelalura.main.controller.HuespedController;
import com.arenas.hotelalura.main.controller.ReservaController;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;
    private int id = 0;
    private int tablaSeleccionada = 0;
    private ReservaController r = new ReservaController();
    private HuespedController h = new HuespedController();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() {

        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));

        modeloHuesped = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Deshabilitar edición en la columna 1 (índice 0)
                return column != 6;
            }
        };
        modeloHuesped.addColumn("Id");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");

        llenarFilas(modeloHuesped, "huespeds");

        tbHuespedes.setModel(modeloHuesped);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Deshabilitar edición en la columna 1 (índice 0)
                return column != 0;
            }
        };
        modelo.addColumn("Id Reserva");
        modelo.addColumn("Check In");
        modelo.addColumn("Check Out");
        modelo.addColumn("Forma de pago");
        modelo.addColumn("Total");

        llenarFilas(modelo, "reservas");

        tbReservas.setModel(modelo);

        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);

        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);

        panel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Obtener el índice del panel seleccionado
                tablaSeleccionada = panel.getSelectedIndex();
            }
        });

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!txtBuscar.getText().isEmpty()) {
                    String textoBusqueda = txtBuscar.getText();
                    resetearFilas(modelo);

                    // Crear un TableRowSorter para la JTable
                    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloHuesped);
                    tbHuespedes.setRowSorter(sorter);

                    try {
                        // Establecer el filtro para el TableRowSorter basado en el texto de búsqueda
                        RowFilter<DefaultTableModel, Object> filtro = RowFilter.regexFilter(textoBusqueda, 2, 6); // Filtrar por columna 1 (nombre) y columna 2 (apellido)
                        sorter.setRowFilter(filtro);

                        int rowCount = tbHuespedes.getRowCount();
                        ArrayList<Integer> digitosArray = new ArrayList<>();
                        for (int i = 0; i < rowCount; i++) {
                            digitosArray.add((Integer) tbHuespedes.getValueAt(i, 6)); // Obtener el valor del índice 6                
                            modelo.addRow(r.buscarReserva(digitosArray));
                        }
                    } catch (PatternSyntaxException ex) {
                        JOptionPane.showMessageDialog(null, "Error en el patrón de búsqueda.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Campo de búsqueda vacío.");
                }
            }
        });

        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);

        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tablaSeleccionada == 1) {
                    id = tbReservas.getSelectedRow();
                    if (id >= 0) {
                        if (JOptionPane.showConfirmDialog(null, "Quieres Eliminar el registro con numero: " + tbReservas.getValueAt(id, 0)) == 0) {
                            try {
                                r.eliminarRegistro((int) tbReservas.getValueAt(id, 0));
                                JOptionPane.showMessageDialog(null, "Eliminado con exito");
                                resetearFilas(modelo);
                                llenarFilas(modelo, "reservas");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Error al elimiar registro: " + ex.getMessage());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Eliminacion cancelada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No has seleccionado ningun registro");
                    }
                } else {
                    id = tbHuespedes.getSelectedRow();
                    if (id >= 0) {
                        if (JOptionPane.showConfirmDialog(null, "Quieres Eliminar el registro con numero: " + tbHuespedes.getValueAt(id, 0)) == 0) {
                            if (h.eliminar((int) tbHuespedes.getValueAt(id, 0))) {
                                JOptionPane.showMessageDialog(null, "Eliminado con exito");
                                resetearFilas(modeloHuesped);
                                llenarFilas(modeloHuesped, "huespeds");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Eliminacion cancelada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No has seleccionado ningun registro");
                    }
                }

            }
        });

        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editarFilas(tablaSeleccionada);
            }
        });
    }

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private DefaultTableModel llenarFilas(DefaultTableModel tableModel, String nombre) {
        try {
            if (null == nombre) {
                return null;
            } else {
                return switch (nombre) {
                    case "reservas" ->
                        r.reservas(tableModel);
                    case "huespeds" ->
                        h.select(tableModel);
                    default ->
                        null;
                };
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage());
            return null;
        }

    }

    private DefaultTableModel resetearFilas(DefaultTableModel tableModel) {
        do {
            tableModel.removeRow(0);
        } while (tableModel.getRowCount() != 0);
        return tableModel;
    }

    private int editarFilas(int idTabla) {
        JTable table = new JTable();
        Editable nuevoController = null;
        switch (idTabla) {
            case 0 -> {
                table = tbHuespedes;
                nuevoController = new HuespedController();
            }
            case 1 -> {
                table = tbReservas;
                nuevoController = new ReservaController();
            }
        }

        id = table.getSelectedRow();
        if (JOptionPane.showConfirmDialog(null, "Quieres editar el registro con numero: " + table.getValueAt(id, 0)) == 0) {
            int columnCount = table.getColumnCount();
            Object[] filaSeleccionada = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                filaSeleccionada[i] = table.getValueAt(id, i);
            }
            if (nuevoController.editar(filaSeleccionada)) {
                JOptionPane.showMessageDialog(null, "Actualización completada.");
            } else {
                JOptionPane.showMessageDialog(null, "Actualizacion Rechazada");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Actualización cancelada.");
        }

        return idTabla;

    }

}
