package fidecompro;

public abstract class Producto {

    protected int idProducto;
    protected String nombre;
    protected String tipo;
    protected double precio;
    protected int stock;

    public Producto(int idProducto, String nombre,
                    String tipo, double precio, int stock) {

        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void ajustarStock(int cantidad) {
        this.stock += cantidad;
    }

    public String consultar() {

        return "Producto: " + nombre
                + " | Tipo: " + tipo
                + " | Precio: " + precio
                + " | Stock: " + stock;
    }

    public abstract String mostrarDetalle();

    @Override
    public String toString() {

        return idProducto + " - "
                + nombre + " - "
                + tipo + " - Stock: "
                + stock;
    }
}