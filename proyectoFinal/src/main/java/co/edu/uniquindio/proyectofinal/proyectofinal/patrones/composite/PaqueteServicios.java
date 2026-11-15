package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.composite;

import java.util.ArrayList;

public class PaqueteServicios implements ServicioComponent{
    private String nombre;

    private ArrayList<ServicioComponent> servicios;

    public PaqueteServicios(String nombre) {

        this.nombre = nombre;
        servicios = new ArrayList<>();
    }

    public void agregarServicio(
            ServicioComponent servicio) {

        servicios.add(servicio);
    }

    public void eliminarServicio(
            ServicioComponent servicio) {

        servicios.remove(servicio);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {

        double total = 0;

        for (ServicioComponent servicio : servicios) {

            total += servicio.getPrecio();

        }

        return total;
    }

    public ArrayList<ServicioComponent> getServicios() {
        return servicios;
    }
}
