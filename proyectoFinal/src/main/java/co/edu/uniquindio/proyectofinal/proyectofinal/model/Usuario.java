package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public abstract class Usuario {
    private String id;
    private String nombre;
    private String correo;
    private String password;

    public Usuario(String id,
                   String nombre,
                   String correo,
                   String password) {

        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
