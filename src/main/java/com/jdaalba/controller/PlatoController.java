package com.jdaalba.controller;

import com.jdaalba.constant.Categoria;
import com.jdaalba.constant.Etiqueta;
import com.jdaalba.entity.Plato;
import com.jdaalba.service.PlatosService;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/platos")
@Slf4j
public record PlatoController(PlatosService service) {

  @PostMapping
  @ResponseBody
  public ResponseEntity<Void> salvarPlato(@RequestBody Plato plato) {
    log.info("Creando plato: {}", plato);
    service.salvar(plato);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Void> modificarPlato(
      @PathVariable @NotBlank String id, @RequestBody Plato plato
  ) {
    log.info("Modificando plato: {}", plato);
    assert id.equals(plato.getId());
    service.salvar(plato);
    return ResponseEntity.status(HttpStatus.CREATED).build();
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
    log.info("Recuperando listado de platos");
    model.addAttribute("categorias", Categoria.values());
    service.buscarTodos().forEach((c, ps) -> model.addAttribute(c.name(), ps));
    model.addAttribute("etiquetas", Etiqueta.values());
    return "admin/lista-platos";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Plato buscar(@PathVariable("id") String id) {
    log.info("Buscando plato con id {}", id);
    return service.buscar(id).orElseThrow();
  }

  @GetMapping("/borrar/{id}")
  public String getPaginaBorrado(Model model, @PathVariable("id") String id) {
    // todo: implementar un 404 correcto
    model.addAttribute("plato", service.buscar(id).orElseThrow());
    model.addAttribute("accion", "borrar");
    return "admin/confirmar-plato";
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public void borrarPlato(@PathVariable("id") String id) {
    log.info("Borrando {}", id);
    service.borrar(id);
  }
}
