package com.projeto.Repositorios;

import java.util.List;

import com.projeto.Dto.PagCredDTO;
import com.projeto.Dto.PagDebDTO;
import com.projeto.Dto.PagDinDTO;
import com.projeto.Dto.PagTotalDTO;
import com.projeto.Dto.PagamentoDTO;


public interface PagamentoRepositorio {

    List<PagamentoDTO> listaPagPorMed(Integer idMed);

    PagTotalDTO qtdTotal(Integer idMed);

    PagDinDTO qtdPagDin(Integer idMed);

    PagDebDTO qtdPagDeb(Integer idMed);

    PagCredDTO qtdPagCred(Integer idMed);
}
