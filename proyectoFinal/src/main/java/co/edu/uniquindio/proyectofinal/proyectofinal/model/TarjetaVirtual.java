package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.util.ArrayList;

public class TarjetaVirtual {
    private String numero;
    private double saldo;
    private boolean activa;

    private ArrayList<MovimientoTarjeta> movimientos;

    public TarjetaVirtual(String numero,
                          double saldo) {

        this.numero = numero;
        this.saldo = saldo;
        this.activa = true;

        movimientos = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public ArrayList<MovimientoTarjeta> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<MovimientoTarjeta> movimientos) {
        this.movimientos = movimientos;
    }

    @Override
    public String toString() {
        return numero;
    }
}
