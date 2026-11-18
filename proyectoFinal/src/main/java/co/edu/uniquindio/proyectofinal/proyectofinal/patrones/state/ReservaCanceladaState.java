package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;

public class ReservaCanceladaState implements EstadoReservaState {
    @Override
    public EstadoReservaState confirmar() {
        return this;
    }

    @Override
    public EstadoReservaState cancelar() {
        return this;
    }

    @Override
    public String getNombreEstado() {
        return "CANCELADA";
    }

    @Override
    public void confirmar(Reserva reserva) {
        throw new IllegalStateException("No se puede confirmar una reserva que ya está cancelada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        System.out.println("La reserva ya está cancelada.");
    }

    @Override
    public void finalizar(Reserva reserva) {
        throw new IllegalStateException("No se puede finalizar una reserva cancelada.");
    }
}