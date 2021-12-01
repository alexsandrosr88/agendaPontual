package com.projeto.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.Entidades.Cidade;

@Repository
public interface CidadeRepositorio extends JpaRepository<Cidade,Integer> {
    
	@Query(nativeQuery = true,value="select b.idbai,b.nome from cidade c "
			+ "inner join bairro b on b.fk_cid_bai = c.idcid "
			+ "where c.idcid = :id")
	List<Object []> buscaBairroPorCidade(Integer id);	
}
