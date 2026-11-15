package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;

public interface EstadoReservaState {
    String getNombreEstado();
    void confirmar(Reserva reserva);
    void cancelar(Reserva reserva);
    void finalizar(Reserva reserva);
}