package com.bolsadeideas.springboot.app.sprintbootform.services;

import com.bolsadeideas.springboot.app.sprintbootform.models.domain.Pais;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class PaisServiceImpl implements PaisService {
    private List<Pais> lista;
    public PaisServiceImpl() {
        this.lista= Arrays.asList(
                new Pais(1, "ES", "España"),
                new Pais(2, "CL", "Chile"),
                new Pais(3, "ARG", "Argentina"),
                new Pais(4, "COL", "Colombia"));
    }

    @Override
    public List<Pais> listar() {
        return lista;
    }

    @Override
    public Pais obtenerPorId(Integer id) {
        Pais resultado = null;
        for (Pais pais : this.lista) {
            if (id==pais.getId()) {
                resultado = pais;
                break;

            }
        }
        return resultado;
    }
}
