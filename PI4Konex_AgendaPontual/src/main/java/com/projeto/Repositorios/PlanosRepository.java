package com.projeto.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.Entidades.Planos;


@Repository
public interface PlanosRepository extends JpaRepository<Planos, Long>{
}
