package fidecompro;

public class Cliente {

    private int idCliente;
    private String nombre;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String correo;

    public Cliente(int idCliente, String nombre,
                   String identificacion, String direccion,
                   String telefono, String correo) {

        this.idCliente = idCliente;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void actualizar(String nombre,
                           String direccion,
                           String telefono,
                           String correo) {

        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String consultar() {

        return "Cliente: " + nombre
                + " | Identificacion: " + identificacion
                + " | Telefono: " + telefono
                + " | Correo: " + correo;
    }

    @Override
    public String toString() {

        return idCliente + " - "
                + nombre + " - "
                + identificacion;
    }
}