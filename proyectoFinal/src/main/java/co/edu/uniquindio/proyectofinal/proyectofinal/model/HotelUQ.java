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
        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();
        promociones = new ArrayList<>();
        servicios = new ArrayList<>();
    }
}
