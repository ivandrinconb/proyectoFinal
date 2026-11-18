package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.facade;

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

}
