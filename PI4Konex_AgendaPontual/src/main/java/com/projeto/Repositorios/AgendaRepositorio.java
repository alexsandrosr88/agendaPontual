package com.projeto.Repositorios;


import com.projeto.Entidades.Agenda;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepositorio extends JpaRepository<Agenda,Integer> {
}
