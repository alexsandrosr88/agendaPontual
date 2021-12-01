package com.projeto.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projeto.Entidades.Doenca;

@Repository
public interface DoencaRepositorio extends JpaRepository<Doenca, Integer> {
	

	@Query(nativeQuery = true, value = "select d.nome,d.iddoe,d.fk_esp_doe from medico m "
			+ "inner join especialidade e on m.fk_esp_med = e.idesp "
			+ "inner join doenca d on d.fk_esp_doe = e.idesp "
			+ "where m.idmed = :idMed")
	List<Doenca> buscaDoencasPorMedico(@Param("idMed") Integer idMed);
}
