package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoReserva;

public interface EstadoReservaState {
    EstadoReservaState confirmar();

    EstadoReservaState cancelar();

    String getNombreEstado();


}
