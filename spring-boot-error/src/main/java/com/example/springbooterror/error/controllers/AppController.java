package com.example.springbooterror.error.controllers;

import com.example.springbooterror.error.errors.UsuarioNoEncontradoException;
import com.example.springbooterror.error.models.domain.Usuario;
import com.example.springbooterror.error.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class AppController {
    @Autowired
    private UsuarioService usuarioService;
    @SuppressWarnings("unused")
    @GetMapping("/index")
    public String index() {
       // Integer valor = 100 / 0;
        Integer valor = Integer.parseInt("10x");
        return "index";
    }
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) {
     /*   Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException(id.toString());
        }*/
        Usuario usuario = usuarioService.obtenerPorIdOptional(id).orElseThrow(() -> new UsuarioNoEncontradoException(id.toString())); //alternativa
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Detalle usuario: " . concat(usuario.getNombre()));
        return "ver";
    }
}
