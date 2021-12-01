package com.projeto.Servicos;

import java.util.List;

import com.projeto.Dto.PagCredDTO;
import com.projeto.Dto.PagDebDTO;
import com.projeto.Dto.PagDinDTO;
import com.projeto.Dto.PagTotalDTO;
import com.projeto.Dto.PagamentoDTO;
import com.projeto.Entidades.Pagamento;
import com.projeto.Repositorios.PagamentoRepositorio;
import com.projeto.Repositorios.PagamentoRepositorio2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServico {

    @Autowired
    private PagamentoRepositorio repoPag;
    @Autowired
    private PagamentoRepositorio2 repoJpaPag;

    // Metodos Pagamento
    public void criaPag(Pagamento pag) {
        repoJpaPag.save(pag);
    }
    
    public List<PagamentoDTO> listaPagPorMed(Integer idMed){
        return repoPag.listaPagPorMed(idMed);
    }

    public PagTotalDTO qtdTotal(Integer idMed){
        return repoPag.qtdTotal(idMed);

    }

    public PagDinDTO qtdDin(Integer idMed){
        return repoPag.qtdPagDin(idMed);
    }

    public PagCredDTO qtdCre(Integer idMed){
        return repoPag.qtdPagCred(idMed);
    }

    public PagDebDTO qtdDeb(Integer idMed){
        return repoPag.qtdPagDeb(idMed);
    }
}
