package sprintbootdi.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sprintbootdi.app.models.services.IServicio;

@Controller
public class IndexController {
    //con esto no hace falta instanciar
    @Autowired
/*    @Qualifier("miServicioComplejo")*/ //para inyectar por nombre
    private IServicio servicio;
    @GetMapping({"/index", "/", "", "/home"})

    public String index(Model model) {
        model.addAttribute("objeto", servicio.operacion());
        return "index";
    }

}
