package com.jdaalba.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.jdaalba.constant.Categoria;
import com.jdaalba.constant.Etiqueta;
import com.jdaalba.entity.Plato;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class PlatoRepositoryTest {

  @Autowired
  PlatoRepository platoRepository;

  @Test
  void test() {
    final List<Plato> platos = Arrays.asList(
        new Plato(
            Categoria.PIZZAS,
            "Margherita",
            List.of("Fresh tomatoes", "fresh mozzarella", "fresh basil"),
            12.5,
            null
        ),
        new Plato(
            Categoria.ENSALADAS,
            "Lasagna",
            List.of("Special sauce", "mozzarella", "parmesan", "ground beef"),
            13.5,
            Etiqueta.NUEVO
        ),
        new Plato(
            Categoria.APERITIVOS,
            "Sopa del día",
            List.of("Preguntar al camarero"),
            5.5,
            Etiqueta.TEMPORADA
        )
    );

    platoRepository.saveAll(platos);

    assertThat(platoRepository.findAll()).hasSize(3);
  }
}