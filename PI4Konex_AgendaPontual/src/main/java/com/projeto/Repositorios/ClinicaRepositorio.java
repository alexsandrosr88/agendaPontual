package com.projeto.Repositorios;

import java.util.List;

import com.projeto.Entidades.Clinica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicaRepositorio extends JpaRepository<Clinica,Integer> {

    @Query(nativeQuery = true, value="select c.nome_cli from bairro as b join cidade as cid on b.fk_cid_bai=cid.id_cid join endereco as e on b.id_bai=e.fk_bai_end join clinica as c on e.id_end=c.fk_end_cli where cid.id_cid= ?1")
    List<Clinica> buscaCliOpCid(Integer idCid);
    
	@Query(nativeQuery = true,value="select c.idcli, c.nomecli from clinica c "
			+ "inner join endereco e on c.fk_end_cli = e.idend "
			+ "inner join bairro b on e.fk_bai_end = b.idbai "
			+ "inner join cidade cid on b.fk_cid_bai = cid.idcid "
			+ "where cid.idcid = :id")
	List<Object []> buscaClinicas(Integer id);	
	
	@Query(nativeQuery = true,value="select * from clinica c "
				+ "where c.idcli = :id")
	Clinica buscaClinicaPorId(Integer id);	
}
