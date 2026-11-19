package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;

public class ReservaConfirmadaState implements EstadoReservaState {

    @Override
    public void confirmar(Reserva reserva) {
        System.out.println("La reserva ya se encuentra confirmada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new ReservaCanceladaState());
        System.out.println("La reserva confirmada ha sido cancelada.");
    }

    @Override
    public void finalizar(Reserva reserva) {
        reserva.setEstado(new ReservaFinalizadaState());
        System.out.println("La reserva ha sido finalizada.");
    }

    @Override
    public String getNombreEstado() {
        return "CONFIRMADA";
    }
}
