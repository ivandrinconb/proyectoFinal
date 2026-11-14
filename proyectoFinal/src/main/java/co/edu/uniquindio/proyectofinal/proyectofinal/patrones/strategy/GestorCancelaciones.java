package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.strategy;

public class GestorCancelaciones {
    public PoliticaCancelacion obtenerPolitica(long horasRestantes){

        if(horasRestantes > 72){
            return new Cancelacion100();
        }

        if(horasRestantes >= 24){
            return new Cancelacion80();
        }

        return new Cancelacion0();
    }
}
