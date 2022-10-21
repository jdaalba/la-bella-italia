package com.jdaalba.service.impl;

import static org.apache.logging.log4j.util.Strings.isBlank;

import com.jdaalba.constant.Categoria;
import com.jdaalba.entity.Plato;
import com.jdaalba.repository.PlatoRepository;
import com.jdaalba.service.PlatosService;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlatosServiceImpl implements PlatosService {

  private final PlatoRepository platoRepository;

  @Override
  public void salvar(Plato plato) {
    if (isBlank(plato.getId())) {
      plato.setId(UUID.randomUUID().toString());
    }
    platoRepository.save(plato);
  }

  @Override
  public Optional<Plato> buscar(String id) {
    return platoRepository.findById(id);
  }

  @Override
  public EnumMap<Categoria, List<Plato>> buscarTodos() {
    return platoRepository.findAll()
        .stream()
        .collect(
            () -> {
              final EnumMap<Categoria, List<Plato>> map = new EnumMap<>(Categoria.class);
              Stream.of(Categoria.values()).forEach(k -> map.put(k, new LinkedList<>()));
              return map;
            },
            (m, p) -> m.get(p.getCategoria()).add(p),
            (m1, m2) -> m1.forEach((k, v) -> m2.get(k).addAll(v))
        );
  }
}
