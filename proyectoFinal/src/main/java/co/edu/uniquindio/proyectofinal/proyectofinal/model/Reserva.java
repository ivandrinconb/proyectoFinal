package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state.EstadoReservaState;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state.ReservaPendienteState;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reserva {
    private String codigo;
    private Cliente cliente;
    private Habitacion habitacion;

    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;

    private ArrayList<ServicioAdicional> servicios;

    private EstadoReservaState estado;

    private double valorTotal;

    public Reserva(String codigo,
                   Cliente cliente,
                   Habitacion habitacion,
                   LocalDate fechaEntrada,
                   LocalDate fechaSalida,
                   double valorTotal) {

        this.codigo = codigo;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorTotal = valorTotal;

        this.estado = new ReservaPendienteState();

        this.servicios = new ArrayList<>();
    }

    // --- MÉTODOS DE COMPORTAMIENTO (PATRÓN STATE) ---
    public void confirmar() {
        estado.confirmar(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }

    public void finalizar() {
        estado.finalizar(this);
    }

    /**
     * MÉTODO PUENTE: Traduce tu Patrón State al enum que usa la vista de Iván
     * para evitar errores de compilación en su controlador.
     */
    public EstadoReserva getEstadoEnum() {
        String nombreClase = estado.getClass().getSimpleName().toLowerCase();

        if (nombreClase.contains("cancelada") || nombreClase.contains("cancelado")) {
            return EstadoReserva.CANCELADA;
        } else if (nombreClase.contains("confirmada") || nombreClase.contains("confirmado")) {
            return EstadoReserva.CONFIRMADA;
        }
        return EstadoReserva.PENDIENTE;
    }

    // --- GETTERS Y SETTERS ---
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public ArrayList<ServicioAdicional> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<ServicioAdicional> servicios) {
        this.servicios = servicios;
    }

    public EstadoReservaState getEstado() {
        return estado;
    }

    public void setEstado(EstadoReservaState estado) {
        this.estado = estado;
    }

    /**
     * Sobrecarga de setEstado para que acepte el enum de Iván
     * y active las transiciones del Patrón State automáticamente.
     */
    public void setEstado(EstadoReserva estadoEnum) {
        if (estadoEnum == EstadoReserva.CANCELADA) {
            cancelar();
        } else if (estadoEnum == EstadoReserva.CONFIRMADA) {
            confirmar();
        } else {
            this.estado = new ReservaPendienteState();
        }
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return codigo +
                " - " +
                habitacion.getNumero() +
                " - " +
                estado;
    }
}