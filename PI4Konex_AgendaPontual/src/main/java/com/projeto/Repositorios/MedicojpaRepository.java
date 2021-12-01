package com.projeto.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.Entidades.Medico;

@Repository
public interface MedicojpaRepository extends JpaRepository<Medico,Integer> {

	@Query(nativeQuery = true, value = "select * from medico as m "
			+ "inner join especialidade e on e.idesp = m.fk_esp_med "
			+ "where fk_cli_med= :id order by pontos desc")
	public List<Medico> melhoresMedCli(Integer id);
	
	@Query(nativeQuery = true, value = "select * from medico as m "
			+ "where m.idmed = :id ")
	public Medico buscaMedicoPorId(Integer id);
}
