package fidecompro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    private Usuario usuarioActual;

    private JButton btnClientes;
    private JButton btnProductos;
    private JButton btnInventario;
    private JButton btnFacturacion;
    private JButton btnSalir;

    public VentanaPrincipal(Usuario usuario) {

        this.usuarioActual = usuario;

        setTitle("FIDECompro - Panel Principal");
        setSize(520, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 40, 25, 40
                )
        );

        JLabel lblTitulo =
                new JLabel(
                        "FIDECompro",
                        SwingConstants.CENTER
                );

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        JLabel lblBienvenida =
                new JLabel(
                        "Bienvenido "
                        + usuarioActual.getNombreUsuario(),
                        SwingConstants.CENTER
                );

        btnClientes =
                new JButton("Clientes");

        btnProductos =
                new JButton("Productos");

        btnInventario =
                new JButton("Inventario");

        btnFacturacion =
                new JButton("Nueva Factura");

        btnSalir =
                new JButton("Salir");

        panel.add(lblTitulo);
        panel.add(lblBienvenida);
        panel.add(btnClientes);
        panel.add(btnProductos);
        panel.add(btnInventario);
        panel.add(btnFacturacion);
        panel.add(btnSalir);

        add(panel);

        btnClientes.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaClientes ventana =
                        new VentanaClientes();

                ventana.setVisible(true);
            }
        });

        btnProductos.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaProductos ventana =
                        new VentanaProductos();

                ventana.setVisible(true);
            }
        });

        btnInventario.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaInventario ventana =
                        new VentanaInventario();

                ventana.setVisible(true);
            }
        });

        btnFacturacion.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(
                        VentanaPrincipal.this,
                        "Modulo de facturacion pendiente para el siguiente avance.",
                        "FIDECompro",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        btnSalir.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                usuarioActual.cerrarSesion();

                VentanaInicioSesion ventana =
                        new VentanaInicioSesion();

                ventana.setVisible(true);

                dispose();
            }
        });
    }
}