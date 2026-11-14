package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

public class ReservaConfirmadaState implements EstadoReservaState{
    @Override
    public String getNombreEstado() {
        return "CONFIRMADA";
    }
}
