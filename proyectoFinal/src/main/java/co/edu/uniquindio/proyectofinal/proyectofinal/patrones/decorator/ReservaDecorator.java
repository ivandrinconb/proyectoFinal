package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.decorator;

public abstract class ReservaDecorator implements ReservaComponent {
    protected ReservaComponent reserva;

    public ReservaDecorator(
            ReservaComponent reserva) {

        this.reserva = reserva;
    }
}
