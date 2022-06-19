package com.mpi.alienresearch.controllers;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GuiController {

  @GetMapping("/login")
  public ModelAndView login() {
    return new ModelAndView("login");
  }

  @GetMapping("/user")
  public ModelAndView index() {
    return new ModelAndView("user_profile");
  }

}
