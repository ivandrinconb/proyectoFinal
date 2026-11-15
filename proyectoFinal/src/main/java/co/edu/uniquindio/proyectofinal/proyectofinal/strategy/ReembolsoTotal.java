package co.edu.uniquindio.proyectofinal.proyectofinal.strategy;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;

public class ReembolsoTotal implements PoliticaCancelacionStrategy {
    @Override
    public double calcularReembolso(Reserva reserva) {
        return reserva.getValorTotal();
    }
}