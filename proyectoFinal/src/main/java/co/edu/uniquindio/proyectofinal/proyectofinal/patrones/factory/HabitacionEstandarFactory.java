package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.factory;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoHabitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;

public class HabitacionEstandarFactory implements HabitacionFactory {
    @Override
    public Habitacion crearHabitacion(String numero,
                                      double tarifaBase) {

        return new HabitacionEstandar(
                numero,
                tarifaBase,
                EstadoHabitacion.DISPONIBLE
        );
    }
}
