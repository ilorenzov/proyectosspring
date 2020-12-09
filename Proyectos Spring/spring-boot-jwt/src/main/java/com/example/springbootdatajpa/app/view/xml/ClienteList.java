package com.example.springbootdatajpa.app.view.xml;

import com.example.springbootdatajpa.app.models.entity.Cliente;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "clientes") //nombre de la raiz xml
public class ClienteList {
    @XmlElement(name = "cliente")
    public List<Cliente> clientes;

    public ClienteList() {

    }
    public ClienteList(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cliente> getClientes() { //CUIDADO ,  SOLO PONER LOS GET
        return clientes;
    }

}
