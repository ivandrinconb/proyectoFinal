package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;

public interface EstadoReservaState {
    EstadoReservaState confirmar();

    EstadoReservaState cancelar();

    String getNombreEstado();
    void confirmar(Reserva reserva);
    void cancelar(Reserva reserva);
    void finalizar(Reserva reserva);
}