package com.jdaalba.controller;

import static java.util.Objects.isNull;

import com.jdaalba.entity.Reserva;
import com.jdaalba.mapper.ReservaDtoMapper;
import com.jdaalba.service.ReservaService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reservas")
@Slf4j
public record ReservasController(ReservaService service) {

  @CrossOrigin
  @PostMapping
  @ResponseBody
  public String registrarReserva(@Valid @RequestBody Reserva reserva) {
    log.info("Reserva recibida: {}", reserva);
    service.salvar(reserva);
    return "redirect:/";
  }

  @GetMapping
  public String getReservasPendientes(
      Model model,
      @RequestParam(name = "pagina", defaultValue = "0") int pagina
  ) {
    final var page = service.buscarPendientesDeConfirmar(pagina);
    model.addAttribute("reservas", page.getContent());
    model.addAttribute("current", pagina);
    model.addAttribute("last", page.getTotalPages());
    return "admin/reservas-pendientes.html";
  }

  @GetMapping("/confirmadas")
  public String buscarReservasConfirmadas(
      Model model,
      @RequestParam(name = "pagina", defaultValue = "0") int pagina,
      @RequestParam(name = "dia", required = false) String fecha
  ) {
    final var dia = isNull(fecha) ? LocalDate.now() : LocalDate.parse(fecha);
    final var page = service.buscar(dia, pagina);
    model.addAttribute("reservas", page.map(ReservaDtoMapper.INSTACE::from).getContent());
    model.addAttribute("current", pagina);
    model.addAttribute("dia", dia);
    model.addAttribute("last", page.getTotalPages());
    model.addAttribute("fecha", LocalDateTime.now());
    return "admin/reservas-confirmadas.html";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Reserva buscarReserva(@PathVariable("id") String id) {
    log.info("Buscando reserva con id '{}'", id);
    return service.buscar(id);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Void> borrarReserva(@PathVariable("id") String id) {
    log.info("Borrando reserva con id '{}'", id);
    service.borrar(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  @ResponseBody
  public void modificar(@PathVariable("id") String id, @Valid @RequestBody Reserva reserva) {
    log.info("Modificando reserva '{}' con id '{}'", reserva, id);
    service.modificar(id, reserva);
  }

  @CrossOrigin
  @PostMapping("/{id_reserva}/confirmar")
  @ResponseBody
  public void confirmar(@PathVariable("id_reserva") String idReserva) {
    log.info("Confirmando reserva {}", idReserva);
    service.confirmar(idReserva);
  }

  @CrossOrigin
  @PostMapping("/{id_reserva}/rechazar")
  @ResponseBody
  public void rechazar(@PathVariable("id_reserva") String idReserva, @RequestBody Rechazo rechazo) {
    log.info("Rechazando reserva {}, {}", idReserva, rechazo);
    service.rechazar(idReserva, rechazo.mensaje());
  }

  public record Rechazo(String mensaje) {

  }
}
