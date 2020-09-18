package com.lavien.hodgepodge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping(value = "/")
  public String welcomePage() {
    return "index.html";
  }

}
