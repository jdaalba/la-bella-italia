package com.jdaalba.controller;

import com.jdaalba.mapper.ReservaMapper;
import com.jdaalba.request.ReservaRequest;
import com.jdaalba.service.ReservaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reservas")
@Slf4j
public record ReservasController(ReservaService service) {

  @CrossOrigin
  @PostMapping
  @ResponseBody
  public String registrarReserva(@RequestBody ReservaRequest reservaRequest) {
    log.info("Reserva recibida: {}", reservaRequest);
    service.salvar(ReservaMapper.INSTANCE.from(reservaRequest));
    return "redirect:/";
  }

  @GetMapping
  public String getReservasPendientes(Model model) {
    model.addAttribute("reservas", service.buscarPendientesDeConfirmar());
    return "admin/reservas-pendientes.html";
  }

  @PostMapping("/confirmar/{id_reserva}")
  public void confirmar( @PathVariable("id_reserva") String idReserva) {
    log.info("Confirmando reserva {}", idReserva);
  }
}
