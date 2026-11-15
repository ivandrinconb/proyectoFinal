package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.facade;

import co.edu.uniquindio.proyectofinal.proyectofinal.controller.SistemaController;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.builder.ReservaBuilder;

import java.time.LocalDate;

public class ReservaFacade {

    private static ReservaFacade instancia;

    private ReservaFacade() {}

    public static ReservaFacade getInstancia() {
        if (instancia == null) {
            instancia = new ReservaFacade();
        }
        return instancia;
    }

    // Método centralizado que utiliza el Patrón Builder para crear la reserva
    public Reserva crearReserva(String codigo, Cliente cliente, Habitacion habitacion,
                                LocalDate fechaEntrada, LocalDate fechaSalida, double valorTotal) {

        // Construcción limpia usando el Builder
        Reserva reserva = new ReservaBuilder()
                .setCodigo(codigo)
                .setCliente(cliente)
                .setHabitacion(habitacion)
                .setFechaEntrada(fechaEntrada)
                .setFechaSalida(fechaSalida)
                .setValorTotal(valorTotal)
                .build();

        // Registrarla de una vez en el modelo global del hotel (Singleton SistemaController)
        SistemaController.getInstancia().getHotelUQ().getReservas().add(reserva);

        return reserva;
    }

    // Delegar la cancelación usando el Patrón State de la reserva
    public void cancelarReserva(Reserva reserva) {
        if (reserva != null) {
            reserva.cancelar();
        }
    }

    // Delegar la confirmación usando el Patrón State
    public void confirmarReserva(Reserva reserva) {
        if (reserva != null) {
            reserva.confirmar();
        }
    }
}