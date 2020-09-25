package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {
    @Value("${texto.indexcontroller.index.titulo}")
    private String textoIndex;
    @Value("${texto.indexcontroller.perfil.titulo}")
    private String textoPerfil;
    @Value("${texto.indexcontroller.listar.titulo}")
    private String textoListar;


    public String index(Model model) {
        model.addAttribute("titulo", textoIndex);
        return "index";
    }

    @GetMapping("/perfil")
    public String perfil(Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre("Nacho");
        usuario.setApellido("Lorenzo");
        usuario.setEmail("aa@aa.aa");
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", textoPerfil + usuario.getNombre()); // titulo es el
        // nombre que
        // tendra la
        // variable en
        // la vista

        return "perfil"; // nombre vista
    }

    @GetMapping("/listar")
    public String listar(Model model) {
//		List<Usuario> usuarios=new ArrayList<>();
//		usuarios.add(new Usuario("Andres","Guzman","correoandres@cc.cc"));
//		usuarios.add(new Usuario("B","D","A@cc.cc"));
//		usuarios.add(new Usuario("C","E","B@cc.cc"));
        List<Usuario> usuarios = Arrays.asList(new Usuario("Andres", "Guzman", "correoandres@cc.cc"),
                new Usuario("B", "D", "A@cc.cc"), new Usuario("B", "D", "A@cc.cc"), new Usuario("D", "D", "A@cc.cc"));
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", textoListar);

        return "listar"; // nombre vista
    }

	/*
	@ModelAttribute("usuarios") // con esto puedes pasar usuarios a los otros controladores , si hacemos esto quitamos todo del metodo listar excepto el addatribute titutlo
	public List<Usuario> poblarUsuario() {
		List<Usuario> usuarios = Arrays.asList(new Usuario("Andres", "Guzman", "correoandres@cc.cc"),
				new Usuario("B", "D", "A@cc.cc"), new Usuario("B", "D", "A@cc.cc"), new Usuario("D", "D", "A@cc.cc"));
		return usuarios;
	}*/

    /*
     * forma 2 public String index2(ModelMap model) {
     * model.addAttribute("titulo","Hola spring framework!"); return "index"; }
     */

    /*
     * forma3 public String index3(Map<String,Object> map) {
     * map.put("titulo","Hola spring framework con map!"); return "index"; }
     */

    /*
     * public ModelAndView index4(ModelAndView mv) {
     * mv.addObject("titulo","Hola spring framework!"); mv.setViewName("index");
     * pasar la vista return mv; }
     */
}
