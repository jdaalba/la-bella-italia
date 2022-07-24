package com.jdaalba.controller;

import com.jdaalba.entity.Plato;
import com.jdaalba.service.PlatosService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AppController {

  private final PlatosService platosService;

  @GetMapping("/greeting")
  public String greeting(
      @RequestParam(name = "name", required = false, defaultValue = "World") String name,
      Model model
  ) {
    model.addAttribute("name", name);
    return "greeting";
  }

  @GetMapping
  public String main(Model model) {
    final List<Plato> platos = platosService.buscarTodos();
    final List<Plato> pizzas = new ArrayList<>();
    final List<Plato> ensaladas = new ArrayList<>();
    final List<Plato> aperitivos = new ArrayList<>();
    for (final Plato p : platos) {
      switch (p.getCategoria()) {
        case PIZZAS:
          pizzas.add(p);
          break;
        case ENSALADAS:
          ensaladas.add(p);
          break;
        case APERITIVOS:
          aperitivos.add(p);
          break;
      }
    }
    model.addAttribute("pizzas", pizzas);
    model.addAttribute("ensaladas", ensaladas);
    model.addAttribute("aperitivos", aperitivos);
    return "index";
  }
}
