package com.jdaalba.entity;

import com.jdaalba.constant.Categoria;
import com.jdaalba.constant.Etiqueta;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Plato {

  private Categoria categoria;
  private String nombre;
  private List<String> ingredientes;
  private double precio;
  private Etiqueta etiqueta;
}
