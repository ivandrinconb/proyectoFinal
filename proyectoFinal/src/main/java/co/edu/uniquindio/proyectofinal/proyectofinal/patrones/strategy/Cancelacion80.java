package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.strategy;

public class Cancelacion80 implements PoliticaCancelacion{
    @Override
    public double calcularReembolso(double valorReserva) {
        return valorReserva * 0.8;
    }
}
