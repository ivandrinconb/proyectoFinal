package co.edu.uniquindio.proyectofinal.proyectofinal.strategy;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;

public interface PoliticaCancelacionStrategy {
    double calcularReembolso(Reserva reserva);
}