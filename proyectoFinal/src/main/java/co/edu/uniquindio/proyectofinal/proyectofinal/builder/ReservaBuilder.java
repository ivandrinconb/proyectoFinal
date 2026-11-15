package co.edu.uniquindio.proyectofinal.proyectofinal.builder;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.ServicioAdicional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ReservaBuilder {
    private String codigo;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private ArrayList<ServicioAdicional> servicios;
    private double descuentoPromocion;

    public ReservaBuilder() {
        this.servicios = new ArrayList<>();
        this.descuentoPromocion = 0.0;
    }

    public ReservaBuilder conCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public ReservaBuilder paraCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public ReservaBuilder enHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
        return this;
    }

    public ReservaBuilder conFechas(LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        return this;
    }

    public ReservaBuilder agregarServicio(ServicioAdicional servicio) {
        if (servicio != null && servicio.isDisponible()) {
            this.servicios.add(servicio);
        }
        return this;
    }

    public ReservaBuilder aplicarPorcentajeDescuento(double porcentajeDescuento) {
        this.descuentoPromocion = porcentajeDescuento;
        return this;
    }

    public Reserva build() {
        long noches = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        if (noches <= 0) {
            noches = 1;
        }

        double costoHabitacion = noches * habitacion.getTarifaBase();
        double costoServicios = 0.0;
        for (ServicioAdicional s : servicios) {
            costoServicios += s.getPrecio();
        }

        double subtotal = costoHabitacion + costoServicios;
        double valorTotal = subtotal - (subtotal * (descuentoPromocion / 100.0));

        Reserva reserva = new Reserva(codigo, cliente, habitacion, fechaEntrada, fechaSalida, valorTotal);
        reserva.setServicios(this.servicios);
        return reserva;
    }
}