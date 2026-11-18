package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.time.LocalDate;

public class MovimientoTarjeta {
    private LocalDate fecha;
    private double valor;
    private TipoMovimientoTarjeta tipo;

    public MovimientoTarjeta(
            LocalDate fecha,
            double valor,
            TipoMovimientoTarjeta tipo) {

        this.fecha = fecha;
        this.valor = valor;
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getValor() {
        return valor;
    }

    public TipoMovimientoTarjeta getTipo() {
        return tipo;
    }

    @Override
    public String toString() {

        return fecha +
                " - " +
                tipo +
                " - $" +
                valor;
    }

}
