package fidecompro;

import java.util.ArrayList;

public class Inventario {

    private ArrayList<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Producto buscarProducto(int idProducto) {

        for (Producto producto : productos) {

            if (producto.getIdProducto() == idProducto) {
                return producto;
            }
        }

        return null;
    }

    public int consultarStock(int idProducto) {

        Producto producto = buscarProducto(idProducto);

        if (producto != null) {
            return producto.getStock();
        }

        return -1;
    }

    public void ingresarStock(int idProducto, int cantidad) {

        Producto producto = buscarProducto(idProducto);

        if (producto != null) {
            producto.ajustarStock(cantidad);
        }
    }

    public void descontarStock(int idProducto, int cantidad) {

        Producto producto = buscarProducto(idProducto);

        if (producto != null) {
            producto.ajustarStock(-cantidad);
        }
    }

    public void mostrarInventario() {

        for (Producto producto : productos) {

            System.out.println(
                    producto.mostrarDetalle()
            );
        }
    }
}