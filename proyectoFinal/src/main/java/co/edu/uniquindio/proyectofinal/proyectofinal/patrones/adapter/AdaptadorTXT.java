package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.adapter;

public class AdaptadorTXT implements ExportadorReporte{
    private GeneradorTXT generadorTXT;

    public AdaptadorTXT() {
        generadorTXT = new GeneradorTXT();
    }

    @Override
    public void exportar(String contenido) {
        generadorTXT.generarTXT(contenido);
    }
}
