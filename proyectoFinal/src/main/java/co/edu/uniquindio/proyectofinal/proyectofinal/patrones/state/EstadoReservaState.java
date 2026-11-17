package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

public interface EstadoReservaState {
    EstadoReservaState confirmar();

    EstadoReservaState cancelar();

    String getNombreEstado();

}
