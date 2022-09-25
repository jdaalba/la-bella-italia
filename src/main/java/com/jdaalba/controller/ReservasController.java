package com.jdaalba.controller;

import com.jdaalba.entity.Reserva;
import com.jdaalba.service.ReservaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  public String registrarReserva(@RequestBody Reserva reserva) {
    log.info("Reserva recibida: {}", reserva);
//    service.salvar(ReservaMapper.INSTANCE.from(reservaRequest));
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
    model.addAttribute("last", page.getTotalPages() - 1);
    return "admin/reservas-pendientes.html";
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
  public void rechazar(@PathVariable("id_reserva") String idReserva) {
    log.info("Confirmando reserva {}", idReserva);
    throw new UnsupportedOperationException("Sin implementar");
  }
}
