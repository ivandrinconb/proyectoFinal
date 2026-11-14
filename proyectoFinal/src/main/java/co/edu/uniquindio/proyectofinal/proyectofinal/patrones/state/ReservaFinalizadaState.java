package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

public class ReservaFinalizadaState implements EstadoReservaState{
    @Override
    public String getNombreEstado() {
        return "FINALIZADA";
    }
}
