package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.facade;

import co.edu.uniquindio.proyectofinal.proyectofinal.controller.SistemaController;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.ServicioAdicional;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.builder.ReservaBuilder;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.decorator.ReservaBase;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.decorator.ReservaComponent;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.decorator.ServicioAdicionalDecorator;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.List;

public class ReservaFacade {
    public Reserva crearReserva(
            String codigo,
            Cliente cliente,
            Habitacion habitacion,
            LocalDate fechaEntrada,
            LocalDate fechaSalida,
            List<ServicioAdicional> servicios) {

        long noches =
                ChronoUnit.DAYS.between(
                        fechaEntrada,
                        fechaSalida
                );

        double precioBase =
                noches * habitacion.getTarifaBase();

        ReservaComponent reservaDecorada =
                new ReservaBase(precioBase);

        for (ServicioAdicional servicio :
                servicios) {

            reservaDecorada =
                    new ServicioAdicionalDecorator(
                            reservaDecorada,
                            servicio
                    );
        }

        double valorTotal =
                reservaDecorada.calcularPrecio();

        Reserva reserva =
                new ReservaBuilder()
                        .setCodigo(codigo)
                        .setCliente(cliente)
                        .setHabitacion(habitacion)
                        .setFechaEntrada(fechaEntrada)
                        .setFechaSalida(fechaSalida)
                        .setValorTotal(valorTotal)
                        .build();

        reserva.getServicios()
                .addAll(servicios);

        return reserva;
    }

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