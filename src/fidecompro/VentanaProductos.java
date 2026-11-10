package fidecompro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaProductos extends JFrame {

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTextField txtDatoExtra;

    private JComboBox<String> cmbTipo;

    private JTextArea areaProductos;

    private JButton btnGuardar;
    private JButton btnConsultar;
    private JButton btnCerrar;

    public VentanaProductos() {

        setTitle("FIDECompro - Gestion de Productos");
        setSize(700, 520);
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
        txtPrecio = new JTextField();
        txtStock = new JTextField();
        txtDatoExtra = new JTextField();

        cmbTipo = new JComboBox<>(
                new String[]{
                    "Alimento",
                    "Bebida"
                }
        );

        panelDatos.add(new JLabel("Nombre:"));
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Tipo:"));
        panelDatos.add(cmbTipo);

        panelDatos.add(new JLabel("Precio:"));
        panelDatos.add(txtPrecio);

        panelDatos.add(new JLabel("Stock inicial:"));
        panelDatos.add(txtStock);

        panelDatos.add(new JLabel("Vencimiento / Refrigerada:"));
        panelDatos.add(txtDatoExtra);

        areaProductos = new JTextArea();
        areaProductos.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(areaProductos);

        JPanel panelBotones = new JPanel();

        btnGuardar =
                new JButton("Registrar Producto");

        btnConsultar =
                new JButton("Consultar Productos");

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

                registrarProducto();
            }
        });

        btnConsultar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mostrarProductos();
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

    private void registrarProducto() {

        try {

            String nombre =
                    txtNombre.getText().trim();

            String tipo =
                    cmbTipo
                            .getSelectedItem()
                            .toString();

            double precio =
                    Double.parseDouble(
                            txtPrecio.getText().trim()
                    );

            int stock =
                    Integer.parseInt(
                            txtStock.getText().trim()
                    );

            String datoExtra =
                    txtDatoExtra.getText().trim();

            int nuevoId =
                    SistemaFidecompro
                            .getInventario()
                            .getProductos()
                            .size() + 1;

            Producto producto;

            if (tipo.equals("Alimento")) {

                producto =
                        new ProductoAlimento(
                                nuevoId,
                                nombre,
                                precio,
                                stock,
                                datoExtra
                        );

            } else {

                boolean refrigerada =
                        datoExtra.equalsIgnoreCase("si");

                producto =
                        new ProductoBebida(
                                nuevoId,
                                nombre,
                                precio,
                                stock,
                                refrigerada
                        );
            }

            SistemaFidecompro
                    .registrarProducto(producto);

            JOptionPane.showMessageDialog(
                    this,
                    "Producto registrado correctamente.",
                    "FIDECompro",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCampos();
            mostrarProductos();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Precio y stock deben ser valores numericos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (DatoInvalidoException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Dato invalido",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void mostrarProductos() {

        areaProductos.setText("");

        if (SistemaFidecompro
                .getInventario()
                .getProductos()
                .isEmpty()) {

            areaProductos.setText(
                    "No hay productos registrados."
            );

            return;
        }

        for (Producto producto :
                SistemaFidecompro
                        .getInventario()
                        .getProductos()) {

            areaProductos.append(
                    producto.mostrarDetalle()
                    + "\n"
            );
        }
    }

    private void limpiarCampos() {

        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtDatoExtra.setText("");
    }
}