package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.decorator;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.ServicioAdicional;

public class ServicioAdicionalDecorator extends ReservaDecorator{
    private ServicioAdicional servicio;

    public ServicioAdicionalDecorator(
            ReservaComponent reserva,
            ServicioAdicional servicio) {

        super(reserva);
        this.servicio = servicio;
    }

    @Override
    public double calcularPrecio() {

        return reserva.calcularPrecio()
                + servicio.getPrecio();
    }
}
