package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.time.LocalDate;

public class MovimientoPuntos {
    private int puntos;

    private LocalDate fechaObtencion;

    private LocalDate fechaVencimiento;

    public MovimientoPuntos(int puntos,
                            LocalDate fechaObtencion) {

        this.puntos = puntos;

        this.fechaObtencion = fechaObtencion;

        this.fechaVencimiento =
                fechaObtencion.plusYears(1);
    }

    public int getPuntos() {
        return puntos;
    }

    public LocalDate getFechaObtencion() {
        return fechaObtencion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String toString() {

        return puntos +
                " puntos | Obtenidos: " +
                fechaObtencion +
                " | Vencen: " +
                fechaVencimiento;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
