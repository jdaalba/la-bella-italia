package com.jdaalba.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReservaRequest {

  private String nombre;
  private int numeroComensales;
  private LocalDateTime momentoReserva;
  private String peticiones;
}
