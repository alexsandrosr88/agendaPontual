package com.projeto.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.Entidades.Bairro;

@Repository
public interface BairroRepositorio extends JpaRepository<Bairro, Integer> {

}
