package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;

public class ReservaFinalizadaState implements EstadoReservaState {
    @Override
    public String getNombreEstado() {
        return "FINALIZADA";
    }

    @Override
    public void confirmar(Reserva reserva) {
        throw new IllegalStateException("No se puede modificar una reserva finalizada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        throw new IllegalStateException("No se puede cancelar una reserva que ya finalizó.");
    }

    @Override
    public void finalizar(Reserva reserva) {
        System.out.println("La reserva ya se encuentra finalizada.");
    }
}