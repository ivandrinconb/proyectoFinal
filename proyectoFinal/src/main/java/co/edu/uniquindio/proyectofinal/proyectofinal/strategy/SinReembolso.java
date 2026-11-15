package co.edu.uniquindio.proyectofinal.proyectofinal.strategy;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;

public class SinReembolso implements PoliticaCancelacionStrategy {
    @Override
    public double calcularReembolso(Reserva reserva) {
        return 0.0;
    }
}
