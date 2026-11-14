package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

public class ReservaCanceladaState implements EstadoReservaState{

    @Override
    public String getNombreEstado() {
        return "CANCELADA";
    }
}
