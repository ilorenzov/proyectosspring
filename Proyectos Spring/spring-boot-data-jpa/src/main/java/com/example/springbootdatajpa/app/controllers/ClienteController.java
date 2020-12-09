package com.example.springbootdatajpa.app.controllers;

import com.example.springbootdatajpa.app.models.entity.Cliente;
import com.example.springbootdatajpa.app.models.service.IClienteService;
import com.example.springbootdatajpa.app.models.service.IUploadFileService;
import com.example.springbootdatajpa.app.util.paginator.PageRender;
import com.example.springbootdatajpa.app.view.xml.ClienteList;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
    protected final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private IClienteService clienteService;
    @Autowired
    private IUploadFileService uploadFileService;
    @Autowired
    private MessageSource messageSource;

    @Secured("ROLE_USER")
    @GetMapping(value = "/uploads/{filename:.+}")// para que no borre la extension
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
        Resource recurso = null;
        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"").body(recurso);
    }

    @Secured("ROLE_USER")
//    @Secured({"ROLE_USER","ROLE_ADMIN"}) mas de un rol
    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Cliente cliente = clienteService.fetchByIdWithFacturas(id); //findOne(id);
        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Detalle cliente" + cliente.getNombre());
        return "ver";
    }
    @RequestMapping(value = "/listar-rest") // el metodo por defecto es GET, se puede omitir ahi
    public @ResponseBody List<Cliente> listarREST() {
        return clienteService.findAll();
    }
/*    @RequestMapping(value = "/listar-rest") xml + JSON http://localhost:8080/listar-rest por defecto saldrá xml http://localhost:8080/listar-rest?format=json
    public @ResponseBody ClienteList listarREST() {
        return new ClienteList(clienteService.findAll());
    }*/

    @RequestMapping(value = {"/listar", "/"}, method = RequestMethod.GET) // el metodo por defecto es GET, se puede omitir ahi
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model , Authentication authentication , HttpServletRequest request , Locale locale) {
        if (authentication != null) {
            logger.info("Hola usuario autenticado, tu username es:" + authentication.getName()); //CONSOLA
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) { //CONSOLA
            logger.info("Utilizando la forma estática SecurityContextHolder.getContext().getAuthentication:  Hola usuario autenticado, tu username es:" + auth.getName());
        }
        if (hasRole("ROLE_ADMIN")) {
            logger.info("Hola" + auth.getName() + "tienes acceso");
        } else {
            logger.info("Hola " + auth.getName() +  " no tienes acceso" );
        }
        SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
        if (securityContext.isUserInRole("ADMIN")) {
            logger.info("Utilizando forma SecurityContextHolderAwareRequestWrapper:  Hola usuario autenticado, tu username es:" + auth.getName());
        } else {
            logger.info("Hola " + auth.getName() +  " no tienes acceso" );
        }

        if (request.isUserInRole("ROLE_ADMIN")) {
            logger.info("Utilizando forma HTTPServletRequest:  Hola usuario autenticado, tu username es:" + auth.getName());
        } else {
            logger.info("Hola " + auth.getName() +  " no tienes acceso" );
        }
        Pageable pageRequest = PageRequest.of(page, 4); //
        Page<Cliente> clientes = clienteService.findAll(pageRequest);
        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
        model.addAttribute("titulo", messageSource.getMessage("text.cliente.titulo", null, locale));
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
        return "listar";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de CLiente");
        return "form";
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Cliente cliente = null;
        if (id > 0) {
            cliente = clienteService.findOne(id);
            if (cliente == null) {
                flash.addFlashAttribute("error", "EL ID del cliente no existe en la base de datos");
                return "redirect:/listar";
            }

        } else {
            flash.addFlashAttribute("error", "EL ID del cliente no puede ser cero!");
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar de CLiente");
        return "form";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult resultado, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) /* si no ponemos valid no valida lo que hemos puesto en el pojo, el orden de argumentos en el metodo IMPORTA ,
    tiene que ir al lado de lo que se está validandoi*/ {
        if (resultado.hasErrors()) { //si hay errores en el formulario
            //el cliente se pasa de forma automatica porque se llama igual que en la linea 31 31-37
            model.addAttribute("titulo", "\"Formulario de CLiente\"");
            return "form";
        }
        if (!foto.isEmpty()) {
            if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null && cliente.getFoto().length() > 0) {
                uploadFileService.delete(cliente.getFoto());
            }
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            flash.addFlashAttribute("info", "has subido correctamente '" + foto.getOriginalFilename() + "'");
            cliente.setFoto(uniqueFilename);
        }
        String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con exito!" : "Cliente creado con exito";
        clienteService.save(cliente);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listar";

    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Cliente cliente = clienteService.findOne(id);
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado  con exito");

            if (uploadFileService.delete(cliente.getFoto())) {
                flash.addFlashAttribute("info", "Foto" + cliente.getFoto() + "eliminada con exito");
            }

        }
        return "redirect:/listar";
    }

    private boolean hasRole(String rol) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return false;
        }
        Authentication auth = context.getAuthentication();
        if (auth == null) {
            return false;
        }
    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        return authorities.contains(new SimpleGrantedAuthority(rol));
        /*    for (GrantedAuthority authority : authorities) {
            if (rol.equals(authority.getAuthority())) {
                logger.info("Hola usuario" + auth.getName() +  "tu rol es:" + authority.getAuthority());
                return true;
            }
        }
        return false;*/
    }
}

