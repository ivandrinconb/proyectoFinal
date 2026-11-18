package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoReserva;

public interface EstadoReservaState {
    EstadoReserva confirmar();

    EstadoReserva cancelar();

    String getNombreEstado();


}
