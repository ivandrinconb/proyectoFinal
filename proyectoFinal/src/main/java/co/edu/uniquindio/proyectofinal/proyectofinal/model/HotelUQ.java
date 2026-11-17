package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.util.ArrayList;

public class HotelUQ {
    private ArrayList<Cliente> clientes;

    private ArrayList<Administrador> administradores;

    private ArrayList<Habitacion> habitaciones;

    private ArrayList<Reserva> reservas;

    private ArrayList<Promocion> promociones;

    private ArrayList<ServicioAdicional> servicios;


    public HotelUQ() {

        clientes = new ArrayList<>();
        administradores = new ArrayList<>();
        administradores.add(
                new Administrador(
                        "A001",
                        "Administrador",
                        "admin@.com",
                        "123"
                )
        );
        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();
        promociones = new ArrayList<>();
        servicios = new ArrayList<>();
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(ArrayList<Administrador> administradores) {
        this.administradores = administradores;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public ArrayList<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(ArrayList<Promocion> promociones) {
        this.promociones = promociones;
    }

    public ArrayList<ServicioAdicional> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<ServicioAdicional> servicios) {
        this.servicios = servicios;
    }

}
