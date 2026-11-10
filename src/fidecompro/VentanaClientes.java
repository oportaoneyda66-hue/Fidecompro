package fidecompro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaClientes extends JFrame {

    private JTextField txtNombre;
    private JTextField txtIdentificacion;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtCorreo;

    private JTextArea areaClientes;

    private JButton btnGuardar;
    private JButton btnConsultar;
    private JButton btnCerrar;

    public VentanaClientes() {

        setTitle("FIDECompro - Gestion de Clientes");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(
                new GridLayout(5, 2, 10, 10)
        );

        txtNombre = new JTextField();
        txtIdentificacion = new JTextField();
        txtDireccion = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();

        panelDatos.add(new JLabel("Nombre:"));
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Identificacion:"));
        panelDatos.add(txtIdentificacion);

        panelDatos.add(new JLabel("Direccion:"));
        panelDatos.add(txtDireccion);

        panelDatos.add(new JLabel("Telefono:"));
        panelDatos.add(txtTelefono);

        panelDatos.add(new JLabel("Correo:"));
        panelDatos.add(txtCorreo);

        areaClientes = new JTextArea();
        areaClientes.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(areaClientes);

        JPanel panelBotones = new JPanel();

        btnGuardar =
                new JButton("Registrar Cliente");

        btnConsultar =
                new JButton("Consultar Clientes");

        btnCerrar =
                new JButton("Cerrar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(
                panelDatos,
                BorderLayout.NORTH
        );

        panelPrincipal.add(
                scroll,
                BorderLayout.CENTER
        );

        panelPrincipal.add(
                panelBotones,
                BorderLayout.SOUTH
        );

        add(panelPrincipal);

        btnGuardar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                registrarCliente();
            }
        });

        btnConsultar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mostrarClientes();
            }
        });

        btnCerrar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });
    }

    private void registrarCliente() {

        try {

            String nombre =
                    txtNombre.getText().trim();

            String identificacion =
                    txtIdentificacion.getText().trim();

            String direccion =
                    txtDireccion.getText().trim();

            String telefono =
                    txtTelefono.getText().trim();

            String correo =
                    txtCorreo.getText().trim();

            int nuevoId =
                    SistemaFidecompro
                            .getClientes()
                            .size() + 1;

            Cliente cliente =
                    new Cliente(
                            nuevoId,
                            nombre,
                            identificacion,
                            direccion,
                            telefono,
                            correo
                    );

            SistemaFidecompro
                    .registrarCliente(cliente);

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente registrado correctamente.",
                    "FIDECompro",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCampos();
            mostrarClientes();

        } catch (DatoInvalidoException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Dato invalido",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void mostrarClientes() {

        areaClientes.setText("");

        if (SistemaFidecompro
                .getClientes()
                .isEmpty()) {

            areaClientes.setText(
                    "No hay clientes registrados."
            );

            return;
        }

        for (Cliente cliente :
                SistemaFidecompro.getClientes()) {

            areaClientes.append(
                    cliente.consultar()
                    + "\n"
            );
        }
    }

    private void limpiarCampos() {

        txtNombre.setText("");
        txtIdentificacion.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }
}
