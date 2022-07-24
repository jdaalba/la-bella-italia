package com.jdaalba.service.impl;

import com.jdaalba.constant.Categoria;
import com.jdaalba.constant.Etiqueta;
import com.jdaalba.entity.Plato;
import com.jdaalba.service.PlatosService;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlatosServiceImpl implements PlatosService {

  @Override
  public List<Plato> buscarTodos() {
    return Arrays.asList(
        new Plato(Categoria.PIZZAS, "Margherita", Arrays.asList("Fresh tomatoes", "fresh mozzarella", "fresh basil"), 12.5, null),
        new Plato(Categoria.ENSALADAS, "Lasagna", Arrays.asList("Special sauce", "mozzarella", "parmesan", "ground beef"), 13.5, Etiqueta.NUEVO),
        new Plato(Categoria.APERITIVOS, "Sopa del día", Arrays.asList("Preguntar al camarero"), 5.5, Etiqueta.TEMPORADA)
    );
  }
}
