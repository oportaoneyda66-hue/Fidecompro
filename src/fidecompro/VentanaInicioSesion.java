package fidecompro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaInicioSesion extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;

    public VentanaInicioSesion() {

        setTitle("FIDECompro - Inicio de Sesion");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 30, 30, 30
                )
        );

        JLabel lblTitulo =
                new JLabel("FIDECompro");

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        JLabel lblUsuario =
                new JLabel("Usuario:");

        txtUsuario =
                new JTextField();

        JLabel lblContrasena =
                new JLabel("Contrasena:");

        txtContrasena =
                new JPasswordField();

        btnIngresar =
                new JButton("Ingresar");

        panel.add(lblTitulo);
        panel.add(new JLabel(""));

        panel.add(lblUsuario);
        panel.add(txtUsuario);

        panel.add(lblContrasena);
        panel.add(txtContrasena);

        panel.add(new JLabel(""));
        panel.add(btnIngresar);

        add(panel);

        btnIngresar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(
                    ActionEvent e) {

                iniciarSesion();
            }
        });
    }

    private void iniciarSesion() {

        String usuario =
                txtUsuario.getText().trim();

        String contrasena =
                new String(
                        txtContrasena.getPassword()
                );

        if (usuario.isEmpty()
                || contrasena.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar usuario y contrasena.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Usuario usuarioEncontrado =
                SistemaFidecompro.autenticarUsuario(
                        usuario,
                        contrasena
                );

        if (usuarioEncontrado != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bienvenido "
                    + usuarioEncontrado
                            .getNombreUsuario(),
                    "Acceso correcto",
                    JOptionPane.INFORMATION_MESSAGE
            );

            VentanaPrincipal ventana =
                    new VentanaPrincipal(
                            usuarioEncontrado
                    );

            ventana.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario o contrasena incorrectos.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}