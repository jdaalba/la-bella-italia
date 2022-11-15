package com.jdaalba.controller;

import com.jdaalba.service.PlatosService;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AppController {

  private final PlatosService platosService;

  @GetMapping
  public String main(Model model) {
    platosService.buscarTodos().forEach((k, v) -> model.addAttribute(k.name().toLowerCase(), v));
    model.addAttribute("fecha", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
    return "index";
  }

  @GetMapping("/condiciones")
  public String condiciones() {
    return "condiciones";
  }
}
