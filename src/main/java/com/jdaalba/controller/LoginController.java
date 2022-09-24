package com.jdaalba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
@Controller
@Slf4j
public class LoginController {

  @GetMapping
  public String getAdminHome() {
    return "login";
  }
}
