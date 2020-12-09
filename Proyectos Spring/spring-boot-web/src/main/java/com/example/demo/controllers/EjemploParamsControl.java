package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class EjemploParamsControl {
	@GetMapping("/string")
	public String index() {
		return "params/index";
	}
	
	@GetMapping("/")
	public String param(@RequestParam(name="texto", required=false , defaultValue = "AAAAA") String texto, Model model) {
		model.addAttribute("resultado","El texto enviado es:" + texto);
		return "params/ver";
	}

}
