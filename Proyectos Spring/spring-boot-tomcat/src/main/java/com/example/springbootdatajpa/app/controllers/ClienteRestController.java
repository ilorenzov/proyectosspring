package com.example.springbootdatajpa.app.controllers;

import com.example.springbootdatajpa.app.models.service.IClienteService;
import com.example.springbootdatajpa.app.view.xml.ClienteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")

public class ClienteRestController {
    @Autowired
    private IClienteService clienteService;
    @RequestMapping(value = "/listar") // el metodo por defecto es GET, se puede omitir ahi
    @Secured("ROLE_ADMIN")
    public ClienteList listar() { // ya estamos heredando el responsebody del restcontroler
        return new ClienteList(clienteService.findAll());
    }
}
