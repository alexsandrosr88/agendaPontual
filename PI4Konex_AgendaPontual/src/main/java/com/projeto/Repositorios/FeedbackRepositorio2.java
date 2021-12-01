package com.projeto.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.Entidades.Feedback;

@Repository
public interface FeedbackRepositorio2 extends JpaRepository<Feedback, Integer>{
	
	@Query(nativeQuery = true, value = "select * from feedback "
			+ "where idfeed = :id")
	Feedback buscaFeedPorId(Integer id);
}
