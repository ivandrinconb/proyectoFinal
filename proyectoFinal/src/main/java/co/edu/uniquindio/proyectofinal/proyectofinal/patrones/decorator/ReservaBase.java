package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.decorator;

public class ReservaBase implements ReservaComponent{
    private double precioBase;

    public ReservaBase(double precioBase) {
        this.precioBase = precioBase;
    }

    @Override
    public double calcularPrecio() {
        return precioBase;
    }
}
