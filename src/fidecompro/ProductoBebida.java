package fidecompro;

public class ProductoBebida extends Producto {

    private boolean refrigerada;

    public ProductoBebida(int idProducto,
                          String nombre,
                          double precio,
                          int stock,
                          boolean refrigerada) {

        super(
                idProducto,
                nombre,
                "Bebida",
                precio,
                stock
        );

        this.refrigerada = refrigerada;
    }

    public boolean isRefrigerada() {
        return refrigerada;
    }

    public void setRefrigerada(boolean refrigerada) {
        this.refrigerada = refrigerada;
    }

    @Override
    public String mostrarDetalle() {

        String estado;

        if (refrigerada) {
            estado = "Si";
        } else {
            estado = "No";
        }

        return "Bebida: " + nombre
                + " | Precio: ₡" + precio
                + " | Stock: " + stock
                + " | Refrigerada: " + estado;
    }
}
