package com.projeto.Repositorios;

import com.projeto.Entidades.DiasSemana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiasRepositorio extends JpaRepository<DiasSemana, Integer> {

    @Query(nativeQuery = true, value = "select * from dias_semana where iddia=?1")
    DiasSemana verificaDia(Integer idDia);

    
}
