package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.factory;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoHabitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.TipoHabitacion;

public class HabitacionSuite extends Habitacion {
    public HabitacionSuite(String numero, double tarifaBase, EstadoHabitacion estado) {
        super(numero, tarifaBase, estado);
    }

    @Override
    public TipoHabitacion getTipoHabitacion() {
        return TipoHabitacion.SUITE;
    }

    @Override
    public double calcularTarifaNoche() {
        return getTarifaBase() * 1.35; // 35% de recargo por lujo y exclusividad
    }

    @Override
    public int getCapacidadMaxima() {
        return 2;
    }
}