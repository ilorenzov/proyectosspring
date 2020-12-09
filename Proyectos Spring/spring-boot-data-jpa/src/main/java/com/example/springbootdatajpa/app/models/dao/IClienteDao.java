package com.example.springbootdatajpa.app.models.dao;

import com.example.springbootdatajpa.app.models.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface IClienteDao extends PagingAndSortingRepository<Cliente,Long> {
    @Query("select c from Cliente c left join fetch c.facturas f where c.id=?1") //si el cliente no tiene facturas daria error, por eso el left join
    public Cliente fetchByIdWithFacturas(Long id);

}
