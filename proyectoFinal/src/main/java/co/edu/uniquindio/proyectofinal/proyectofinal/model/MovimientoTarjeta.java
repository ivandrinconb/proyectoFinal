package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.time.LocalDate;

public class MovimientoTarjeta {
    private LocalDate fecha;
    private double valor;
    private TipoMovimientoTarjeta tipo;

    public MovimientoTarjeta(LocalDate fecha,
                             double valor,
                             TipoMovimientoTarjeta tipo) {

        this.fecha = fecha;
        this.valor = valor;
        this.tipo = tipo;
    }
}
