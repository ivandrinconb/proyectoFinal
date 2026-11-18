module co.edu.uniquindio.proyectofinal.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens co.edu.uniquindio.proyectofinal.proyectofinal to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal.proyectofinal;
    exports co.edu.uniquindio.proyectofinal.proyectofinal.controller;
    opens co.edu.uniquindio.proyectofinal.proyectofinal.controller to javafx.fxml;
}