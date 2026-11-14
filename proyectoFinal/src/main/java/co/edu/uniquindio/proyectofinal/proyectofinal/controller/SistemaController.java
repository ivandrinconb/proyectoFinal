package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.HotelUQ;

public class SistemaController {

    private static SistemaController instancia;

    private HotelUQ hotelUQ;

    private SistemaController() {
        hotelUQ = new HotelUQ();
    }

    public static SistemaController getInstancia() {

        if (instancia == null) {
            instancia = new SistemaController();
        }

        return instancia;
    }

    public HotelUQ getHotelUQ() {
        return hotelUQ;
    }

    public void setHotelUQ(HotelUQ hotelUQ) {
        this.hotelUQ = hotelUQ;
    }
}
