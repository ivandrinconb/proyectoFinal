# HotelUQ — Sistema de Gestión Hotelera

**Autores:** Ivan Dario Rincon Betancourt y Estefania Cubides Restrepo  
**Asignatura:** Programación II — Universidad del Quindío  
**Tecnologías:** Java, JavaFX, Maven  

---

## 1. Descripción General del Proyecto

**HotelUQ** es una aplicación de escritorio desarrollada en **Java** y **JavaFX** que modela y administra la operación integral de un hotel. Su arquitectura separa el **modelo del dominio**, los **patrones de diseño** (creacionales, estructurales y de comportamiento) y la **capa de controladores/vistas**, garantizando mantenibilidad, extensibilidad y coherencia en las reglas de negocio.

Entre las funcionalidades clave del sistema se encuentran:
* Administración centralizada de clientes, administradores, habitaciones, reservas, promociones y servicios adicionales a través de `HotelUQ`.
* Gestión de sesión y coordinación del sistema mediante `SistemaController`.
* Creación de reservas con validación de fechas y disponibilidad de habitaciones, cálculo de tarifa base por noches, servicios adicionales y paquetes, y aplicación de descuentos promocionales (`ReservaHabitacionController`).
* Consulta y cancelación de reservas (`MisReservasController`) integradas con el ciclo de vida del estado de cada reserva.
* Confirmación y pago de reservas (`PagoReservaController`) con descuento sobre `TarjetaVirtual`, registro de movimientos, acumulación de puntos en `CuentaPuntos` y notificación a observadores.

---

## 2. Arquitectura y Modelo del Dominio

El núcleo del dominio está compuesto por las siguientes entidades y enumeraciones:

* **`HotelUQ` (`<<Singleton>>`)**: Mantiene la instancia única del sistema y concentra las colecciones de `clientes`, `administradores`, `habitaciones`, `reservas`, `promociones` y `servicios`.
* **`SistemaController` (`<<Singleton>>`)**: Expone `getInstancia()`, referencia a `HotelUQ` (`-hotelUQ: HotelUQ`) y gestiona el cliente en sesión (`-clienteSesion: Cliente`).
* **Jerarquía de Usuarios (`Usuario` y `Administrador`)**: `Usuario` define los atributos base (`id`, `nombre`, `correo`, `password`), y `Administrador` hereda de él.
* **`Cliente`**: Representa al huésped y asocia su `TarjetaVirtual` (`-tarjetaVirtual: TarjetaVirtual`) y su `CuentaPuntos` (`-cuentaPuntos: CuentaPuntos`).
* **Gestión Financiera y de Fidelización**:
  * `TarjetaVirtual`: Maneja `numero`, `saldo` y `activa`, con operaciones `recargarSaldo(monto)`, `pagar(valor)` y `reembolsar(valor)`, registrando `MovimientoTarjeta` con `TipoMovimientoTarjeta` (`RECARGA`, `PAGO`, `REEMBOLSO`).
  * `CuentaPuntos`: Gestiona `agregarPuntos(puntos)`, `calcularPuntosDisponibles()` y `consumirPuntos(puntos)`, respaldada por `MovimientoPuntos` (`puntos`, `fechaObtencion`, `fechaVencimiento`).
* **Jerarquía de Habitaciones (`Habitacion`)**: Clase abstracta con `numero`, `tarifaBase` y `estado` (`EstadoHabitacion`: `DISPONIBLE`, `OCUPADA`, `MANTENIMIENTO`), `TipoHabitacion` (`ESTANDAR`, `FAMILIAR`, `SUITE`) y métodos `getTipoHabitacion()`, `calcularTarifaNoche()` y `getCapacidadMaxima()`, especializada en `HabitacionEstandar`, `HabitacionSuite` y `HabitacionFamiliar`.
* **`Reserva` y `EstadoReserva`**: Almacena `codigo`, `fechaEntrada`, `fechaSalida`, `valorTotal` y su estado dinámico `estado: EstadoReservaState`. Expone `confirmar()`, `cancelar()`, `finalizar()` y el método puente `getEstadoEnum(): EstadoReserva` (`PENDIENTE`, `CONFIRMADA`, `CANCELADA`) para interoperabilidad con la capa de presentación.
* **`ServicioAdicional` y `Promocion`**: `ServicioAdicional` define `nombre`, `precio` y `disponible`; `Promocion` define `nombre`, `descripcion` y `porcentajeDescuento`.

---

## 3. Patrones de Diseño Implementados

### 3.1. Patrones Creacionales

1. **Singleton (`HotelUQ` y `SistemaController`)**
   * **Propósito:** Garantizar una única instancia global del núcleo de datos del hotel y del controlador central del sistema durante todo el ciclo de vida de la aplicación.
   * **Implementación:** Constructor privado, atributo estático `-instancia` y método de acceso `getInstancia()`.

2. **Builder (`ReservaBuilder`)**
   * **Propósito:** Facilitar la construcción paso a paso y legible de instancias de `Reserva` sin depender de constructores extensos.
   * **Implementación:** Métodos encadenables `setCodigo(codigo: String)`, `setCliente(cliente: Cliente)`, `setHabitacion(habitacion: Habitacion)`, `setFechaEntrada(fecha: LocalDate)`, `setFechaSalida(fecha: LocalDate)`, `setValorTotal(valor: double)` y `build(): Reserva`.

3. **Factory Method (`HabitacionFactory`)**
   * **Propósito:** Delegar la instanciación concreta de cada tipo de habitación a fábricas especializadas manteniendo desacoplado el código cliente.
   * **Implementación:** Interfaz/fábrica `HabitacionFactory` con `crearHabitacion(numero, tarifaBase, estado): Habitacion`, implementada por `HabitacionEstandarFactory`, `HabitacionSuiteFactory` y `HabitacionFamiliarFactory`.

---

### 3.2. Patrones Estructurales

1. **Facade (`ReservaFacade`, combinado con Singleton)**
   * **Propósito:** Proveer una interfaz unificada y de alto nivel para las operaciones principales del ciclo de una reserva.
   * **Implementación:** Accesible mediante `getInstancia(): ReservaFacade`, expone `crearReserva(codigo, cliente, habitacion, fechaEntrada, fechaSalida, servicios): Reserva`, `crearReserva(codigo, cliente, habitacion, fechaEntrada, fechaSalida, valorTotal): Reserva`, `confirmarReserva(reserva): void` y `cancelarReserva(reserva): void`.

2. **Composite (`ServicioComponent`, `ServicioSimple`, `PaqueteServicios`)**
   * **Propósito:** Permitir tratar servicios individuales y paquetes compuestos por múltiples servicios bajo una misma abstracción (`getNombre(): String`, `getPrecio(): double`).
   * **Implementación:** `ServicioSimple` representa una prestación unitaria y `PaqueteServicios` mantiene `servicios: ArrayList<ServicioComponent>` con `agregarServicio(s)` y `eliminarServicio(s)`, sumando recursivamente los precios.

3. **Decorator (`ReservaComponent`, `ReservaBase`, `ReservaDecorator`, `ServicioAdicionalDecorator`)**
   * **Propósito:** Agregar responsabilidades y costos adicionales (como servicios extra) a una reserva de forma flexible y acumulativa sin modificar `ReservaBase`.
   * **Implementación:** `ReservaComponent` define `calcularPrecio(): double`; `ReservaBase` establece `-precioBase: double`; `ReservaDecorator` envuelve `#reserva: ReservaComponent`; y `ServicioAdicionalDecorator` asocia `-servicio: ServicioAdicional` para sumar su valor al cálculo total.

4. **Adapter (`ExportadorReporte`, `AdaptadorPDF`, `AdaptadorTXT`, `GeneradorPDF`, `GeneradorTXT`)**
   * **Propósito:** Adaptar las interfaces específicas de generación de archivos (`GeneradorPDF` y `GeneradorTXT`) a una interfaz común de exportación (`ExportadorReporte`).
   * **Implementación:** `ExportadorReporte` expone `exportar(contenido: String): void`, implementado por `AdaptadorPDF` (que delega en `GeneradorPDF.generarPDF(contenido)`) y `AdaptadorTXT` (que delega en `GeneradorTXT.generarTXT(contenido)`).

---

### 3.3. Patrones de Comportamiento

1. **State (`EstadoReservaState`, `ReservaPendienteState`, `ReservaConfirmadaState`, `ReservaCanceladaState`, `ReservaFinalizadaState`)**
   * **Propósito:** Encapsular las transiciones y validaciones del ciclo de vida de `Reserva` según su estado actual, evitando condicionales extensos en el modelo o en los controladores.
   * **Implementación:** `Reserva` delega `confirmar()`, `cancelar()` y `finalizar()` en su atributo `estado: EstadoReservaState`. Cada estado concreto (`ReservaPendienteState`, `ReservaConfirmadaState`, `ReservaCanceladaState`, `ReservaFinalizadaState`) implementa `confirmar(reserva)`, `cancelar(reserva)`, `finalizar(reserva)` y `getNombreEstado(): String`.

2. **Strategy (`PoliticaCancelacion`, `GestorCancelaciones`, `Cancelacion0`, `Cancelacion80`, `Cancelacion100`)**
   * **Propósito:** Definir una familia de algoritmos intercambiables para calcular el reembolso de una reserva según las horas de anticipación previas a la cancelación.
   * **Implementación:** `PoliticaCancelacion` declara `calcularReembolso(valorReserva): double`. `GestorCancelaciones.obtenerPolitica(horasRestantes)` selecciona la estrategia correspondiente: `Cancelacion100` (más de 72 h -> 100 %), `Cancelacion80` (entre 24 y 72 h -> 80 %) o `Cancelacion0` (menos de 24 h -> 0 %).

3. **Observer (`Subject`, `ObservableReserva`, `Observer`, `NotificacionObserver`)**
   * **Propósito:** Notificar automáticamente a los observadores suscritos cuando ocurren cambios relevantes en una reserva (por ejemplo, su confirmación).
   * **Implementación:** `Subject` define `agregarObserver(o)`, `eliminarObserver(o)` y `notificarObservers(mensaje)`, implementado por `ObservableReserva`; `Observer` define `actualizar(mensaje)`, implementado por `NotificacionObserver`.

---

## 4. Estructura de Paquetes del Código Fuente

```text
co.edu.uniquindio.proyectofinal.proyectofinal
├── model
│   ├── HotelUQ.java
│   ├── Usuario.java
│   ├── Administrador.java
│   ├── Cliente.java
│   ├── Reserva.java
│   ├── EstadoReserva.java
│   ├── Habitacion.java
│   ├── HabitacionEstandar.java
│   ├── HabitacionSuite.java
│   ├── HabitacionFamiliar.java
│   ├── TipoHabitacion.java
│   ├── EstadoHabitacion.java
│   ├── ServicioAdicional.java
│   ├── Promocion.java
│   ├── TarjetaVirtual.java
│   ├── MovimientoTarjeta.java
│   ├── TipoMovimientoTarjeta.java
│   ├── CuentaPuntos.java
│   └── MovimientoPuntos.java
├── controller
│   ├── SistemaController.java
│   ├── ReservaHabitacionController.java
│   ├── MisReservasController.java
│   ├── PagoReservaController.java
│   └── ... (controladores de vistas FXML)
└── patrones
    ├── builder        (ReservaBuilder)
    ├── factory        (HabitacionFactory y fábricas concretas)
    ├── facade         (ReservaFacade)
    ├── composite      (ServicioComponent, ServicioSimple, PaqueteServicios)
    ├── decorator      (ReservaComponent, ReservaBase, ReservaDecorator, ServicioAdicionalDecorator)
    ├── adapter        (ExportadorReporte, AdaptadorPDF, AdaptadorTXT, GeneradorPDF, GeneradorTXT)
    ├── state          (EstadoReservaState y estados concretos)
    ├── strategy       (PoliticaCancelacion, GestorCancelaciones, Cancelacion0, Cancelacion80, Cancelacion100)
    └── observer       (Subject, ObservableReserva, Observer, NotificacionObserver)
```
---
## 5. Compilación y Ejecución

1. Clonar el repositorio desde GitHub y abrirlo en **IntelliJ IDEA** (reconocido como proyecto **Maven**).
2. Verificar que el proyecto cuente con un JDK compatible y las dependencias/SDK de **JavaFX** configuradas en `pom.xml` y en el entorno de ejecución.
3. Compilar el proyecto (`Build > Build Project` o `mvn clean compile`).
4. Ejecutar la clase principal de JavaFX (`Application`) para iniciar la interfaz gráfica y probar los flujos de creación, consulta, pago y cancelación de reservas.

