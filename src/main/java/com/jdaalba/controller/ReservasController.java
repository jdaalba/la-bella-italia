package com.jdaalba.controller;

import com.jdaalba.mapper.ReservaMapper;
import com.jdaalba.request.ReservaRequest;
import com.jdaalba.service.ReservaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
@Slf4j
public record ReservasController(ReservaService service) {

  @PostMapping
  @CrossOrigin
  public String registrarReserva(@RequestBody ReservaRequest reservaRequest) {
    log.info("Reserva recibida: {}", reservaRequest);
    service.salvar(ReservaMapper.INSTANCE.from(reservaRequest));
    return "redirect:/";
  }
}
