package com.projeto.Repositorios;

import com.projeto.Entidades.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {

    //Usuario findByUserLogin(String email);
}
