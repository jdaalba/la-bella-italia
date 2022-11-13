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

  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Void> borrarPlato(@PathVariable("id") String id) {
    log.info("Borrando {}", id);
    service.borrar(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
