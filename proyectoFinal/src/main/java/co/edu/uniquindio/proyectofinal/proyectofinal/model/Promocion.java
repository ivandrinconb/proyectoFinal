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
}
