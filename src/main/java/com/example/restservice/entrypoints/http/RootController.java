package com.example.restservice.entrypoints.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

   // @RequestMapping(name = "/", method = RequestMethod.GET)
  //  public String swaggerUi() {
   //     return "redirect:/swagger-ui.html";
   // }
}
