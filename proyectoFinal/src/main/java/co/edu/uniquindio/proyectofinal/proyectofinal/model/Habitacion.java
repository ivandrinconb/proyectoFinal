package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public abstract class Habitacion {

    private String numero;
    private double tarifaBase;
    private EstadoHabitacion estado;

    public Habitacion(String numero,
                      double tarifaBase,
                      EstadoHabitacion estado) {

        this.numero = numero;
        this.tarifaBase = tarifaBase;
        this.estado = estado;
    }

    public abstract TipoHabitacion getTipoHabitacion();

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return numero + " - " + getTipoHabitacion();
    }
}
