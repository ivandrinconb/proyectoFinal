package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoReserva;

public class ReservaConfirmadaState implements EstadoReservaState{

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
        return "CONFIRMADA";
    }
}
