package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.time.LocalDate;
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

        this.movimientos = new ArrayList<>();
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

    public void setMovimientos(
            ArrayList<MovimientoTarjeta> movimientos) {

        this.movimientos = movimientos;
    }

    public void recargarSaldo(double monto) {

        if (monto <= 0) {
            return;
        }

        saldo += monto;

        movimientos.add(
                new MovimientoTarjeta(
                        LocalDate.now(),
                        monto,
                        TipoMovimientoTarjeta.RECARGA
                )
        );
    }

    public boolean pagar(double valor) {

        if (!activa) {
            return false;
        }

        if (valor <= 0) {
            return false;
        }

        if (saldo < valor) {
            return false;
        }

        saldo -= valor;

        movimientos.add(
                new MovimientoTarjeta(
                        LocalDate.now(),
                        valor,
                        TipoMovimientoTarjeta.PAGO
                )
        );

        return true;
    }

    public void reembolsar(double valor) {

        if (valor <= 0) {
            return;
        }

        saldo += valor;

        movimientos.add(
                new MovimientoTarjeta(
                        LocalDate.now(),
                        valor,
                        TipoMovimientoTarjeta.REEMBOLSO
                )
        );
    }

    @Override
    public String toString() {
        return numero;
    }
}
