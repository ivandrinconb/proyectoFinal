package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.factory;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoHabitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.TipoHabitacion;

public class HabitacionEstandar extends Habitacion {
    public HabitacionEstandar(String numero, double tarifaBase, EstadoHabitacion estado) {
        super(numero, tarifaBase, estado);
    }

    @Override
    public TipoHabitacion getTipoHabitacion() {
        return TipoHabitacion.ESTANDAR;
    }

    @Override
    public double calcularTarifaNoche() {
        return getTarifaBase(); // Tarifa base sin recargo
    }

    @Override
    public int getCapacidadMaxima() {
        return 2;
    }
}