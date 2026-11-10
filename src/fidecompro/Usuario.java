package fidecompro;

public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private String rol;

    public Usuario(int idUsuario, String nombreUsuario,
                   String contrasena, String rol) {

        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean autenticar(String usuario, String contrasena) {

        return this.nombreUsuario.equals(usuario)
                && this.contrasena.equals(contrasena);
    }

    public void cerrarSesion() {

        System.out.println(
                "Sesion cerrada para el usuario: "
                + nombreUsuario
        );
    }

    @Override
    public String toString() {

        return nombreUsuario + " - " + rol;
    }
}