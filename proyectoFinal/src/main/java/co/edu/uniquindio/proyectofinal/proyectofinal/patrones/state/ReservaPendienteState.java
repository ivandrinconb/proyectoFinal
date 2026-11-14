package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoReserva;

public class ReservaPendienteState implements EstadoReservaState{
    @Override
    public String getNombreEstado() {
        return "PENDIENTE";
    }
}
