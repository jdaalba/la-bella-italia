package com.jdaalba.service;

import com.jdaalba.constant.Categoria;
import com.jdaalba.entity.Plato;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

public interface PlatosService {

  EnumMap<Categoria, List<Plato>> buscarTodos();

  void salvar(Plato plato);

  Optional<Plato> buscar(String id);
}
