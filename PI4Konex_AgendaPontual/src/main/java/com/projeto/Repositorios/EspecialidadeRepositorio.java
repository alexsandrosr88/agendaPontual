package com.projeto.Repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projeto.Entidades.Especialidade;

@Repository
public interface EspecialidadeRepositorio extends JpaRepository<Especialidade, Integer> {
	
	@Query(nativeQuery = true, value ="select * from especialidade e where lower(e.nome)"
			+ " like lower(concat(:termo, '%'))")
	List<Especialidade> buscaEsp(@Param("termo") String termo);
}
