package com.example.springbooterror.error.services;

import com.example.springbooterror.error.models.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private List<Usuario> lista;

    public UsuarioServiceImpl() {
        this.lista = new ArrayList<>();
        this.lista.add(new Usuario(1, "Andres", "Guzman"));
        this.lista.add(new Usuario(2, "Pepa", "A"));
        this.lista.add(new Usuario(3, "Lalo", "V"));
        this.lista.add(new Usuario(4, "Luci", "C"));
        this.lista.add(new Usuario(5, "Paco", "D"));
        this.lista.add(new Usuario(6, "Juan", "Z"));
    }

    @Override
    public List<Usuario> listar() {
        return this.lista;
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        Usuario resultado = null;
        for (Usuario u : this.lista) {
            if (u.getId().equals(id)) {
                resultado = u;
                break;
            }
        }
        return resultado;
    }

    @Override
    public Optional<Usuario> obtenerPorIdOptional(Integer id) {
        Usuario usuario = this.obtenerPorId(id);
        return Optional.ofNullable(usuario);
    }

}
