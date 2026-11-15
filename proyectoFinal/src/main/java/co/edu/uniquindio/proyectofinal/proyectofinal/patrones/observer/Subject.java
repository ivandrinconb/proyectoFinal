package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.observer;

public interface Subject {
    void agregarObserver(Observer observer);

    void eliminarObserver(Observer observer);

    void notificarObservers(String mensaje);

}
