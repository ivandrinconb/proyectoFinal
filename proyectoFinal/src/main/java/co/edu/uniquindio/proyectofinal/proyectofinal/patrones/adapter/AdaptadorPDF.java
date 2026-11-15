package co.edu.uniquindio.proyectofinal.proyectofinal.patrones.adapter;

public class AdaptadorPDF implements ExportadorReporte{
    private GeneradorPDF generadorPDF;

    public AdaptadorPDF() {
        generadorPDF = new GeneradorPDF();
    }

    @Override
    public void exportar(String contenido) {
        generadorPDF.generarPDF(contenido);
    }
}
