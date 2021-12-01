package com.projeto.Repositorios;

import com.projeto.Entidades.Ajuda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AjudaRepositorio extends JpaRepository<Ajuda,Integer> {
    
}
