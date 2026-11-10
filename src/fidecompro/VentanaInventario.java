package fidecompro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaInventario extends JFrame {

    private JTextField txtIdProducto;
    private JTextField txtCantidad;

    private JTextArea areaInventario;

    private JButton btnConsultar;
    private JButton btnIngresar;
    private JButton btnDescontar;
    private JButton btnCerrar;

    public VentanaInventario() {

        setTitle("FIDECompro - Inventario");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(
                new BorderLayout(10, 10)
        );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(
                new GridLayout(2, 2, 10, 10)
        );

        txtIdProducto = new JTextField();
        txtCantidad = new JTextField();

        panelDatos.add(
                new JLabel("ID del producto:")
        );
        panelDatos.add(txtIdProducto);

        panelDatos.add(
                new JLabel("Cantidad:")
        );
        panelDatos.add(txtCantidad);

        areaInventario = new JTextArea();
        areaInventario.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(areaInventario);

        JPanel panelBotones =
                new JPanel();

        btnConsultar =
                new JButton("Consultar Inventario");

        btnIngresar =
                new JButton("Ingresar Stock");

        btnDescontar =
                new JButton("Descontar Stock");

        btnCerrar =
                new JButton("Cerrar");

        panelBotones.add(btnConsultar);
        panelBotones.add(btnIngresar);
        panelBotones.add(btnDescontar);
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

        btnConsultar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mostrarInventario();
            }
        });

        btnIngresar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                ingresarStock();
            }
        });

        btnDescontar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                descontarStock();
            }
        });

        btnCerrar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });

        mostrarInventario();
    }

    private void mostrarInventario() {

        areaInventario.setText("");

        if (SistemaFidecompro
                .getInventario()
                .getProductos()
                .isEmpty()) {

            areaInventario.setText(
                    "No hay productos registrados."
            );

            return;
        }

        for (Producto producto :
                SistemaFidecompro
                        .getInventario()
                        .getProductos()) {

            areaInventario.append(
                    "ID: "
                    + producto.getIdProducto()
                    + " | "
                    + producto.mostrarDetalle()
                    + "\n"
            );
        }
    }

    private void ingresarStock() {

        try {

            int id =
                    Integer.parseInt(
                            txtIdProducto
                                    .getText()
                                    .trim()
                    );

            int cantidad =
                    Integer.parseInt(
                            txtCantidad
                                    .getText()
                                    .trim()
                    );

            if (cantidad <= 0) {

                throw new DatoInvalidoException(
                        "La cantidad debe ser mayor que cero."
                );
            }

            Producto producto =
                    SistemaFidecompro
                            .getInventario()
                            .buscarProducto(id);

            if (producto == null) {

                throw new DatoInvalidoException(
                        "El producto no existe."
                );
            }

            SistemaFidecompro
                    .getInventario()
                    .ingresarStock(
                            id,
                            cantidad
                    );

            JOptionPane.showMessageDialog(
                    this,
                    "Stock actualizado correctamente.",
                    "FIDECompro",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCampos();
            mostrarInventario();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID y cantidad deben ser numeros.",
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

    private void descontarStock() {

        try {

            int id =
                    Integer.parseInt(
                            txtIdProducto
                                    .getText()
                                    .trim()
                    );

            int cantidad =
                    Integer.parseInt(
                            txtCantidad
                                    .getText()
                                    .trim()
                    );

            if (cantidad <= 0) {

                throw new DatoInvalidoException(
                        "La cantidad debe ser mayor que cero."
                );
            }

            Producto producto =
                    SistemaFidecompro
                            .getInventario()
                            .buscarProducto(id);

            if (producto == null) {

                throw new DatoInvalidoException(
                        "El producto no existe."
                );
            }

            if (cantidad > producto.getStock()) {

                throw new StockInsuficienteException(
                        "No hay suficiente stock disponible."
                );
            }

            SistemaFidecompro
                    .getInventario()
                    .descontarStock(
                            id,
                            cantidad
                    );

            JOptionPane.showMessageDialog(
                    this,
                    "Stock descontado correctamente.",
                    "FIDECompro",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCampos();
            mostrarInventario();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID y cantidad deben ser numeros.",
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

        } catch (StockInsuficienteException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Stock insuficiente",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limpiarCampos() {

        txtIdProducto.setText("");
        txtCantidad.setText("");
    }
}