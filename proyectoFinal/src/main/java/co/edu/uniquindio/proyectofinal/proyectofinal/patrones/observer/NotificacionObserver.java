package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.observer;

public class NotificacionObserver implements Observer {
    @Override
    public void actualizar(String mensaje) {

        System.out.println(
                "NOTIFICACIÓN: " + mensaje
        );

    }
}
