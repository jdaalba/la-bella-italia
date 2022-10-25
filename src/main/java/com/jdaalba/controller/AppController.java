package com.jdaalba.controller;

import com.jdaalba.service.PlatosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AppController {

  private final PlatosService platosService;

  @GetMapping
  public String main(Model model) {
    platosService.buscarTodos().forEach((k, v) -> model.addAttribute(k.name().toLowerCase(), v));

    return "index";
  }
}
