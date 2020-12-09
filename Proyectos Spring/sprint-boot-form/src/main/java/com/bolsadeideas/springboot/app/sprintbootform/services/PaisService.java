package com.bolsadeideas.springboot.app.sprintbootform.services;

import com.bolsadeideas.springboot.app.sprintbootform.models.domain.Pais;

import java.util.List;

public interface PaisService {
    public List<Pais> listar();

    public Pais obtenerPorId(Integer id);

}
