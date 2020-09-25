package com.bolsadeideas.springboot.app.sprintbootform.controllers;

import com.bolsadeideas.springboot.app.sprintbootform.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("usuario") //si id no está en el formulario no se envia, hay que guardarlo en una sesion
public class FormController {
    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre("John");
        usuario.setApellido("Doe");
        usuario.setIdentificador("564.564.645.654-K");
        model.addAttribute("titulo", "Formulario usuarios");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) { //Binding result informe validacion , binding result siempre despues del objeto que se valida
        model.addAttribute("titulo", "Resultado form");
        if (result.hasErrors()) {
/*            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
            });
            model.addAttribute("error", errores);*/
            return "form";
        }

        model.addAttribute("usuario", usuario);
        status.setComplete();
        return "resultado";
    }
    /*    @PostMapping("/form")
    public String procesar(Model model, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "email") String email) {
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPassword(password);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Resultado form");
        return "resultado";
    }*/
}
