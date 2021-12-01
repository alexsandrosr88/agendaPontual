package com.projeto.Repositorios;

import java.time.LocalDate;

import com.projeto.Entidades.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PacienteRepositorio extends JpaRepository<Paciente,Integer> {

    @Query(nativeQuery = true, value = "select * from paciente as p inner join usuario as u "
    +"on p.fk_usu_paci=u.idusu inner join endereco as e on p.fk_end_paci=e.idend where p.cpf=?1")
    Integer buscaPacienteporCPF(String cpf);
    
//    @Query(nativeQuery = true, value = "select p.idpaci, p.foto, p.nome, p.cpf, p.datanasci, p.rg, p.celular, "
//    		+ "p.sexo, p.primeiraconsulta, p.sintomasgripe, p.termouso, p.fk_end_paci, p.fk_usu_paci "
//    		+ "from paciente as p "
//    		+ "inner join usuario as u on p.fk_usu_paci = u.idusu "
//    		+ "where u.idusu = :idusu")
//    Paciente buscaPacientePorUsuarioId(@Param("idusu") Integer idusu);
    
    
    @Query(nativeQuery = true, value = "select * "
    		+ "from paciente as p "
    		+ "inner join usuario as u on p.fk_usu_paci = u.idusu "
    		+ "where u.idusu = :idusu")
    Paciente buscaPacientePorUsuarioId(@Param("idusu") Integer idusu);

    @Modifying
    @Query(nativeQuery = true,value = "update paciente set nome=?1, cpf=?2, datanasci=?3, sexo=?4 where idpaci=?5 ")
    void atualizaPaciente(String nome, String cpf, LocalDate dataNasc, String sexo, Integer idpaci);
}
