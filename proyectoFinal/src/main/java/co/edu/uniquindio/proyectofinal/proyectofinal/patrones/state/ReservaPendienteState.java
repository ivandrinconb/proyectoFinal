package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoReserva;

public class ReservaPendienteState implements EstadoReservaState{
    @Override
    public EstadoReservaState confirmar() {
        return new ReservaConfirmadaState();
    }

    @Override
    public EstadoReservaState cancelar() {
        return new ReservaCanceladaState();
    }

    @Override
    public String getNombreEstado() {
        return "PENDIENTE";
    }
}
