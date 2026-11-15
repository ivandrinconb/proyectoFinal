package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.composite;

import java.io.Serializable;

public class ServicioSimple implements ServicioComponent {
    private String nombre;
    private double precio;

    public ServicioSimple(String nombre,
                          double precio) {

        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
