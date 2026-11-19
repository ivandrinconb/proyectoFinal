package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CuentaPuntos {
    private ArrayList<MovimientoPuntos> movimientos;

    public CuentaPuntos() {
        movimientos = new ArrayList<>();
    }

    public ArrayList<MovimientoPuntos> getMovimientos() {
        return movimientos;
    }

    public void agregarPuntos(int puntos) {

        movimientos.add(
                new MovimientoPuntos(
                        puntos,
                        LocalDate.now()
                )
        );
    }

    public int calcularPuntosDisponibles() {

        int total = 0;

        for (MovimientoPuntos movimiento :
                movimientos) {

            if (movimiento.getFechaVencimiento()
                    .isAfter(LocalDate.now())) {

                total += movimiento.getPuntos();
            }
        }

        return total;
    }
    public boolean consumirPuntos(int puntos) {

        int disponibles =
                calcularPuntosDisponibles();

        if (puntos > disponibles) {
            return false;
        }

        int restantes = puntos;

        for (MovimientoPuntos movimiento :
                movimientos) {

            if (restantes <= 0) {
                break;
            }

            if (movimiento.getPuntos() <= restantes) {

                restantes -= movimiento.getPuntos();

                movimiento.setPuntos(0);

            } else {

                movimiento.setPuntos(
                        movimiento.getPuntos()
                                - restantes
                );

                restantes = 0;
            }
        }

        return true;
    }
}
