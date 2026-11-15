package co.edu.uniquindio.proyectofinal.proyectofinal.strategy;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;

public class ReembolsoParcial implements PoliticaCancelacionStrategy {
    @Override
    public double calcularReembolso(Reserva reserva) {
        return reserva.getValorTotal() * 0.80;
    }
}