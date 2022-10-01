package com.jdaalba.dto;

import java.time.LocalTime;

public record ReservaDto(
    String id,
    LocalTime hora,
    String nombre,
    String mail,
    String telefono,
    int numeroComensales,
    String peticiones
    ) {

}
