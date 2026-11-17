package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.factory;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoHabitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;

public class HabitacionEstandarFactory implements HabitacionFactory {

    public Habitacion crearHabitacion(
            String numero,
            double tarifaBase,
            EstadoHabitacion estado) {

        return new HabitacionEstandar(
                numero,
                tarifaBase,
                estado
        );
    }
}
