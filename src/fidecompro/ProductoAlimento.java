package fidecompro;

public class ProductoAlimento extends Producto {

    private String fechaVencimiento;

    public ProductoAlimento(int idProducto,
                            String nombre,
                            double precio,
                            int stock,
                            String fechaVencimiento) {

        super(
                idProducto,
                nombre,
                "Alimento",
                precio,
                stock
        );

        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String mostrarDetalle() {

        return "Alimento: " + nombre
                + " | Precio: ₡" + precio
                + " | Stock: " + stock
                + " | Vencimiento: "
                + fechaVencimiento;
    }
}