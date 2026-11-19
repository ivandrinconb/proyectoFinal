package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public class Promocion {
    private String nombre;

    private String descripcion;

    private double porcentajeDescuento;

    public Promocion(String nombre,
                     String descripcion,
                     double porcentajeDescuento) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public String toString() {

        return nombre +
                " - " +
                porcentajeDescuento +
                "%";
    }
}
