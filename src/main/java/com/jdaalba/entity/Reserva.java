package com.jdaalba.entity;

import com.jdaalba.converters.EncryptConverter;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.convert.ValueConverter;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("reservas")
public class Reserva {

  @Id
  private String id;

  private LocalDateTime fechaRecepcion = LocalDateTime.now();

  @NotBlank
  @ValueConverter(EncryptConverter.class)
  private String mail;

  @NotBlank
  @ValueConverter(EncryptConverter.class)
  private String nombre;

  @ValueConverter(EncryptConverter.class)
  private String telefono;

  @Positive
  private int numeroComensales;

  @NotNull
  private LocalDateTime momentoReserva;

  private String peticiones;

  private boolean confirmada;
}
