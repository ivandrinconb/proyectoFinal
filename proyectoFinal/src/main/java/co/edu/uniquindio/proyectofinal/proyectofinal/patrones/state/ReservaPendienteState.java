package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;

public class ReservaPendienteState implements EstadoReservaState {

    @Override
    public void confirmar(Reserva reserva) {
        reserva.setEstado(new ReservaConfirmadaState());
        System.out.println("La reserva ha sido confirmada exitosamente.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new ReservaCanceladaState());
        System.out.println("La reserva pendiente ha sido cancelada.");
    }

    @Override
    public void finalizar(Reserva reserva) {
        throw new IllegalStateException("No se puede finalizar una reserva que aún está pendiente.");
    }

    @Override
    public String getNombreEstado() {
        return "PENDIENTE";
    }
}