package com.example.springbootdatajpa.app.models.dao;

import com.example.springbootdatajpa.app.models.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductoDao extends CrudRepository<Producto,Long> { //Long por el tipo de dato del id
    @Query("select p from Producto p where p.nombre like %?1% ") //consulta a nivel de objeto
    public List<Producto> findByNombre(String term);
}
