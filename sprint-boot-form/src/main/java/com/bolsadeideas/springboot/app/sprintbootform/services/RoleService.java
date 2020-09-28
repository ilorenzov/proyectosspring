package com.bolsadeideas.springboot.app.sprintbootform.services;

import com.bolsadeideas.springboot.app.sprintbootform.models.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> listar();

    public Role obtenerPorId(Integer id);
}
