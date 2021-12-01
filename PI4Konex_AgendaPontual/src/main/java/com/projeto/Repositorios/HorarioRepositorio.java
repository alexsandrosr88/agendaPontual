package com.projeto.Repositorios;

import com.projeto.Entidades.Horario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepositorio extends JpaRepository<Horario,Integer> {
    
}
