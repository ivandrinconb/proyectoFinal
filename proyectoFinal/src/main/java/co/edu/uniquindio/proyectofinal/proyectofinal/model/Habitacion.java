package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public class Habitacion {
    private String numero;
    private TipoHabitacion tipoHabitacion;
    private double tarifaBase;
    private EstadoHabitacion estado;

    public Habitacion(String numero,
                      TipoHabitacion tipoHabitacion,
                      double tarifaBase,
                      EstadoHabitacion estado) {

        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.tarifaBase = tarifaBase;
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
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
        return numero + " - " + tipoHabitacion;
    }
}
