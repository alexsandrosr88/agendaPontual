package com.projeto.Servicos;

import java.util.List;

import com.projeto.Entidades.Agenda;
import com.projeto.Repositorios.AgendaRepositorio;
import com.projeto.Repositorios.ConsultaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AgendaServico {

    @Autowired
    private AgendaRepositorio repoAgen;
    @Autowired
    private ConsultaRepositorio repoCons;

    // Metodos CRUD Agenda


    @Transactional(readOnly = false)
    public void criaAtualizaAgen(Agenda agen) {
        repoAgen.save(agen);
    }

    @Transactional(readOnly = true)
    public Agenda pegaAgenPorId(Integer id) {
        return repoAgen.getById(id);
    }


}
