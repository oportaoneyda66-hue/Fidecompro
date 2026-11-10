package fidecompro;

import java.util.ArrayList;

public class SistemaFidecompro {

    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static Inventario inventario = new Inventario();

    static {

        // Usuario inicial para poder ingresar al sistema
        usuarios.add(
                new Usuario(
                        1,
                        "admin",
                        "1234",
                        "Administrador"
                )
        );
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static Inventario getInventario() {
        return inventario;
    }

    public static Usuario autenticarUsuario(
            String nombreUsuario,
            String contrasena) {

        for (Usuario usuario : usuarios) {

            if (usuario.autenticar(
                    nombreUsuario,
                    contrasena)) {

                return usuario;
            }
        }

        return null;
    }

    public static void registrarCliente(Cliente cliente)
        throws DatoInvalidoException {

    if (cliente.getNombre().trim().isEmpty()) {

        throw new DatoInvalidoException(
                "El nombre del cliente no puede estar vacio."
        );
    }

    if (cliente.getIdentificacion().trim().isEmpty()) {

        throw new DatoInvalidoException(
                "La identificacion no puede estar vacia."
        );
    }

    for (Cliente existente : clientes) {

        if (existente.getIdentificacion()
                .equalsIgnoreCase(
                        cliente.getIdentificacion())) {

            throw new DatoInvalidoException(
                    "Ya existe un cliente con esa identificacion."
            );
        }
    }

    clientes.add(cliente);
}

    public static void registrarProducto(Producto producto)
            throws DatoInvalidoException {

        if (producto.getNombre().trim().isEmpty()) {

            throw new DatoInvalidoException(
                    "El nombre del producto no puede estar vacio."
            );
        }

        if (producto.getPrecio() <= 0) {

            throw new DatoInvalidoException(
                    "El precio debe ser mayor que cero."
            );
        }

        if (producto.getStock() < 0) {

            throw new DatoInvalidoException(
                    "El stock no puede ser negativo."
            );
        }

        inventario.agregarProducto(producto);
    }
}