package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.strategy;

public class Cancelacion100 implements PoliticaCancelacion{
    @Override
    public double calcularReembolso(double valorReserva) {
        return valorReserva;
    }
}
