package com.jdaalba.entity;

import com.jdaalba.constant.Categoria;
import com.jdaalba.constant.Etiqueta;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("platos")
@NoArgsConstructor
public class Plato {

  @Id
  private String id;
  private Categoria categoria;
  private String nombre;
  private List<String> ingredientes;
  private double precio;
  private Etiqueta etiqueta;

  public Plato(Categoria categoria, String nombre, List<String> ingredientes, double precio,
      Etiqueta etiqueta) {
    this.categoria = categoria;
    this.nombre = nombre;
    this.ingredientes = ingredientes;
    this.precio = precio;
    this.etiqueta = etiqueta;
  }
}
