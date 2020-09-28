package com.bolsadeideas.springboot.app.sprintbootform.controllers;

import com.bolsadeideas.springboot.app.sprintbootform.editors.NombreMayusculaEditor;
import com.bolsadeideas.springboot.app.sprintbootform.editors.PaisPropertyEditor;
import com.bolsadeideas.springboot.app.sprintbootform.editors.RolesEditor;
import com.bolsadeideas.springboot.app.sprintbootform.models.domain.Pais;
import com.bolsadeideas.springboot.app.sprintbootform.models.domain.Role;
import com.bolsadeideas.springboot.app.sprintbootform.models.domain.Usuario;
import com.bolsadeideas.springboot.app.sprintbootform.services.PaisService;
import com.bolsadeideas.springboot.app.sprintbootform.services.RoleService;
import com.bolsadeideas.springboot.app.sprintbootform.validation.UsuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes("usuario") //si id no está en el formulario no se envia, hay que guardarlo en una sesion
public class FormController {
    @Autowired
    private UsuarioValidador validador;
    @Autowired
    private PaisService paisService;
    @Autowired
    private PaisPropertyEditor paisEditor;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolesEditor roleEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validador);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));
        /*        binder.registerCustomEditor(String.class, new NombreMayusculaEditor());*/
        binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor()); //solo mayus a nombre
        binder.registerCustomEditor(Pais.class, "pais", paisEditor);
        binder.registerCustomEditor(Role.class, "roles", roleEditor);
    }

    @ModelAttribute("listaPaises")
    public List<Pais> listaPaises() {
        return paisService.listar();
    }

    @ModelAttribute("genero")
    public List<String> genero() {
        return Arrays.asList("Hombre", "Mujer");
    }

    @ModelAttribute("paises")
    public List<String> paises() {
        return Arrays.asList("España", "Chile", "Argentina", "Colombia");
    }

    @ModelAttribute("paisesMap")
    public Map<String, String> paisesMap() {
        Map<String, String> paises = new HashMap<String, String>();
        paises.put("ES", "ESPAÑA");
        paises.put("CL", "CHILE");
        paises.put("ARG", "ARGENTINA");
        paises.put("COL", "COLOMBIA");
        return paises;
    }

    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre("John");
        usuario.setApellido("Doe");
        usuario.setIdentificador("13.456.789-K");
        usuario.setHabilitar(true);
        usuario.setValorSecreto("Algun valor secreto");
        usuario.setPais(new Pais(1, "ES", "España"));
        usuario.setRoles(Arrays.asList(new Role(1, "Administrador", "ROLE_ADMIN")));
        model.addAttribute("titulo", "Formulario usuarios");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model) { //Binding result informe validacion , binding result siempre despues del objeto que se valida
        // validador.validate(usuario, result);
        if (result.hasErrors()) {
/*            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
            });
            model.addAttribute("error", errores);*/
            model.addAttribute("titulo", "Resultado form");
            return "form";
        }
        return "redirect:/ver";
    }

    @GetMapping("/ver")
    public String ver(@SessionAttribute(name = "usuario" , required=false) Usuario usuario, Model model, SessionStatus status) {
        if (usuario == null) {
            return "redirect:/form";
        }
        model.addAttribute("titulo", "Resultado form");
        status.setComplete();
        return "resultado";//en el resultado  si le damos f5 nos envia otra vez al form , si no hacemos esto da error

    }

    @ModelAttribute("listaRoles")
    public List<Role> listaRoles() {
        return this.roleService.listar();
    }

    @ModelAttribute("listaRolesString")
    public List<String> listaRolesString() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        roles.add("ROLE_MODERATOR");
        return roles;
    }

    @ModelAttribute("listaRolesMap")
    public Map<String, String> listaRolesMap() {
        Map<String, String> roles = new HashMap<String, String>();
        roles.put("ROLE_ADMIN", "Administrador");
        roles.put("ROLE_USER", "Usuario");
        roles.put("ROLE_MODERATOR", "Moderador");
        return roles;
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
