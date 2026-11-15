package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.builder;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Usuario;
import java.time.LocalDate;

public class ReservaBuilder {
    private String codigo;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private double valorTotal;

    public ReservaBuilder setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public ReservaBuilder setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public ReservaBuilder setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
        return this;
    }

    public ReservaBuilder setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public ReservaBuilder setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }

    public ReservaBuilder setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public Reserva build() {

        return new Reserva(
                codigo,
                cliente,
                habitacion,
                fechaEntrada,
                fechaSalida,
                valorTotal
        );
    }
}
