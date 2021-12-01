package com.projeto.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Entidades.Ajuda;
import com.projeto.Repositorios.AjudaRepositorio;

@Service
public class AjudaServico {

    @Autowired
    private AjudaRepositorio repoAjuda;


    // Metodos Ajuda
    @Transactional(readOnly = false)
    public void criaAjuda(Ajuda ajuda) {
        repoAjuda.save(ajuda);
    }
}
