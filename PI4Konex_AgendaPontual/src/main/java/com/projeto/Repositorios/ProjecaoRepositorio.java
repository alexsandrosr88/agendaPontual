package com.projeto.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.projeto.Entidades.ProjecaoSalarial;

@Repository
public interface ProjecaoRepositorio extends JpaRepository<ProjecaoSalarial, Integer> {

    @Query(nativeQuery = true, value = "select * from projsalarial where fk_med_proj=?1")
    List<ProjecaoSalarial> listaProj(Integer id);

}
