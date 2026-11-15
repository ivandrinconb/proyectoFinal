package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.observer;

import java.util.ArrayList;

public class ObservableReserva implements Subject{
    private ArrayList<Observer> observers;

    public ObservableReserva() {

        observers = new ArrayList<>();
    }

    @Override
    public void agregarObserver(Observer observer) {

        observers.add(observer);
    }

    @Override
    public void eliminarObserver(Observer observer) {

        observers.remove(observer);
    }

    @Override
    public void notificarObservers(String mensaje) {

        for (Observer observer : observers) {

            observer.actualizar(mensaje);

        }
    }
}
