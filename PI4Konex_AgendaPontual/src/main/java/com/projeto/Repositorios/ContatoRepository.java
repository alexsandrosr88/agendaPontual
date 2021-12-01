package com.projeto.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.Entidades.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{
}
