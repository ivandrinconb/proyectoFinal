package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.util.ArrayList;

public class HotelUQ {

    // Instancia única para aplicar el Patrón Singleton
    private static HotelUQ instancia;

    private ArrayList<Cliente> clientes;
    private ArrayList<Administrador> administradores;
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Reserva> reservas;
    private ArrayList<Promocion> promociones;
    private ArrayList<ServicioAdicional> servicios;

    // Constructor privado para proteger el Singleton
    private HotelUQ() {
        clientes = new ArrayList<>();
        administradores = new ArrayList<>();
        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();
        promociones = new ArrayList<>();
        servicios = new ArrayList<>();

        // Inicializamos los datos por defecto para cumplir con la rúbrica
        inicializarDatosBase();
    }

    // Método estático para obtener la única instancia del hotel
    public static HotelUQ getInstancia() {
        if (instancia == null) {
            instancia = new HotelUQ();
        }
        return instancia;
    }

    // Método para precargar datos iniciales y demostrar el funcionamiento
    private void inicializarDatosBase() {
        // Administrador por defecto
        administradores.add(
                new Administrador(
                        "A001",
                        "Administrador Principal",
                        "admin@hoteluq.com",
                        "123"
                )
        );

        // Habitaciones de ejemplo (puedes adaptarlas según tus clases de Factory si ya las tienes creadas)
        // habitaciones.add(new HabitacionEstándar("101", 150000, ...));

        // Servicios adicionales de ejemplo con los 3 atributos correctos: (Nombre, Precio, Disponible)
        servicios.add(new ServicioAdicional("Desayuno Americano", 25000.0, true));
        servicios.add(new ServicioAdicional("Acceso a Zona Spa", 60000.0, true));
        servicios.add(new ServicioAdicional("Parqueadero Cubierto", 20000.0, true));
    }

    // --- Getters y Setters ---

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