package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoReserva;

public class ReservaConfirmadaState implements EstadoReservaState{

    @Override
    public EstadoReserva confirmar() {
        return EstadoReserva.CONFIRMADA;
    }

    @Override
    public EstadoReserva cancelar() {
        return EstadoReserva.CANCELADA;
    }

    @Override
    public String getNombreEstado() {
        return "CONFIRMADA";
    }
}
