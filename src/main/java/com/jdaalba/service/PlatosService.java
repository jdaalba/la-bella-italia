package com.jdaalba.service;

import com.jdaalba.constant.Categoria;
import com.jdaalba.entity.Plato;
import java.util.EnumMap;
import java.util.List;

public interface PlatosService {

  EnumMap<Categoria, List<Plato>> buscarTodos();
}
