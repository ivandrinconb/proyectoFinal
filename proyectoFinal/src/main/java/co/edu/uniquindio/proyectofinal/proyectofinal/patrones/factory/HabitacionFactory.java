package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.factory;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;

public interface HabitacionFactory {
    Habitacion crearHabitacion(
            String numero,
            double tarifaBase
    );
}
