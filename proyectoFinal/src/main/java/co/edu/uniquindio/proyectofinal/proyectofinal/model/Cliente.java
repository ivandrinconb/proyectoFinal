package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public class Cliente extends  Usuario {
    private TarjetaVirtual tarjetaVirtual;
    private CuentaPuntos cuentaPuntos;

    public Cliente(String id,
                   String nombre,
                   String correo,
                   String password,
                   TarjetaVirtual tarjetaVirtual,
                   CuentaPuntos cuentaPuntos) {

        super(id, nombre, correo, password);
        this.tarjetaVirtual = tarjetaVirtual;
        this.cuentaPuntos = cuentaPuntos;
    }

    public TarjetaVirtual getTarjetaVirtual() {
        return tarjetaVirtual;
    }

    public void setTarjetaVirtual(TarjetaVirtual tarjetaVirtual) {
        this.tarjetaVirtual = tarjetaVirtual;
    }

    public CuentaPuntos getCuentaPuntos() {
        return cuentaPuntos;
    }

    public void setCuentaPuntos(CuentaPuntos cuentaPuntos) {
        this.cuentaPuntos = cuentaPuntos;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
