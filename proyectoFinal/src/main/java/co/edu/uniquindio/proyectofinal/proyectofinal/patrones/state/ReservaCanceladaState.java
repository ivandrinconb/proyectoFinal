package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

public class ReservaCanceladaState implements EstadoReservaState{

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
}
