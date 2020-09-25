package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/") //cuando vamos a la raiz http://localhost:8080/ nos redirige automaticamente a /app/index/
    public String home() {
/*        return "redirect:/app/index";*/
       /* return "redirect:https://www.google.es";*/
        return "forward:/app/index"; // es como un this load view, no redirige, asi que no cambia la url
    }
}
