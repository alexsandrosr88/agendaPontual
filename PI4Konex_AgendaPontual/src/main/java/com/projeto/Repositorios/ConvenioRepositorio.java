package com.projeto.Repositorios;

import java.util.List;

import com.projeto.Entidades.Convenio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenioRepositorio extends JpaRepository<Convenio,Integer> {

    @Query(nativeQuery = true, value="select c.idconv,c.nome,c.valor, cm.fk_conv_med, cm.fk_med_conv from convenio_medico as cm join convenio as c on c.idconv=cm.fk_conv_med where cm.fk_med_conv = ?1")
    List<Convenio> convPorMed(Integer idMed);
    
}
