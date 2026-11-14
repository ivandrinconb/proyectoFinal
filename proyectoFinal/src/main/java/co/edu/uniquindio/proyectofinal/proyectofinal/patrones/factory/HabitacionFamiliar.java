package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.factory;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoHabitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.TipoHabitacion;

public class HabitacionFamiliar extends Habitacion {
    public HabitacionFamiliar(String numero,
                              double tarifaBase,
                              EstadoHabitacion estado) {

        super(numero, tarifaBase, estado);
    }

    @Override
    public TipoHabitacion getTipoHabitacion() {
        return TipoHabitacion.FAMILIAR;
    }
}
