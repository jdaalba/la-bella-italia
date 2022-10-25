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
    platosService.buscarTodos()
        .forEach((k, v) -> model.addAttribute(k.name().toLowerCase(), v));

    return "index";
  }
}
