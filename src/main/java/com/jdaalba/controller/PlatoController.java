package com.jdaalba.controller;

import com.jdaalba.constant.Categoria;
import com.jdaalba.constant.Etiqueta;
import com.jdaalba.entity.Plato;
import com.jdaalba.service.PlatosService;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/platos")
@Slf4j
public record PlatoController(PlatosService service) {

  @PostMapping
  public String registrarPlato(@ModelAttribute Plato plato, Model model) {
    log.info("Creando plato: {}", plato);
    service.salvar(plato);
    return "redirect:/platos";
  }

  @GetMapping("/nuevo")
  public String getPaginaNuevo(Model model) {
    model.addAttribute("plato", new Plato());
    model.addAttribute("categorias", Categoria.values());
    model.addAttribute("etiquetas", Etiqueta.values());
    return "admin/platos.html";
  }

  @GetMapping
  public String getListado(Model model) {
    model.addAttribute("categorias", Categoria.values());
    service.buscarTodos().forEach((c, ps) -> model.addAttribute(c.name(), ps));
    return "admin/lista-platos";
  }

  @GetMapping("/modificar/{id}")
  public String modficar(Model model, @PathVariable("id") String id) {
    log.info("Modificando plato con id {}", id);
    // todo: implementar un 404 correcto
    model.addAttribute("plato", service.buscar(id).orElseThrow());
    model.addAttribute("categorias", Categoria.values());
    model.addAttribute("etiquetas", Etiqueta.values());
    return "admin/platos";
  }
}
