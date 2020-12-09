package com.example.springbootdatajpa.app.models.dao;

import com.example.springbootdatajpa.app.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario,Long> {
    public Usuario findByUsername(String username); //a traves del nombre del metodo(Query method name) se ejecutará la consulta JPQL "select u from Usuario u where u.username=?1
}

