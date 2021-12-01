package com.projeto.Repositorios;

import com.projeto.Entidades.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco,Integer> {
    
}
