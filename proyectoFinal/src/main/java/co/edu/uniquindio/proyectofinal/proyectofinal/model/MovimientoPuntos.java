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
}
