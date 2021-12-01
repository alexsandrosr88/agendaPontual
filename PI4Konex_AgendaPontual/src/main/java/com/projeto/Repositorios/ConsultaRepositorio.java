package com.projeto.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.projeto.Entidades.Consulta;

@Repository
public interface ConsultaRepositorio extends JpaRepository<Consulta,Integer> {
   Consulta findByIdConsulta(Integer idConsulta);

   @Query(nativeQuery = true, value = "select c.idcons,c.hora,c.dtagendada,c.fk_med_cons,c.fk_paci_cons,"+
   "c.informacoesadic,c.confirmada,c.retorno,c.horachegada,c.horasaida,c.duracao,c.concluida,"+
   "c.naocompareceu,c.cancelada,c.fk_rec_cons,c.fk_pag_cons,c.fk_feed_cons, m.nome, "+
   "e.nome from consulta as c join medico as m on c.fk_med_cons=m.idmed join especialidade "+
   "as e on m.fk_esp_med=e.idesp where c.fk_paci_cons = ?1")
   List<Consulta> consultaAgendamentos(Integer idPaci);
   

   @Query(nativeQuery = true,  value = "select c.idcons, c.dtagendada,c.hora,c.informacoesadic,"+
  "c.confirmada, c.fk_med_cons,c.fk_paci_cons,c.retorno,c.horachegada,c.horasaida,c.duracao,c.concluida,c.naocompareceu,"+
  "c.cancelada,c.fk_rec_cons,c.fk_pag_cons,c.fk_feed_cons, m.nome as Medico, e.nome as Especialidade "+
  "from consulta as c join paciente as p on c.fk_paci_cons=p.idpaci  join medico as m on c.fk_med_cons=m.idmed " +
  "join especialidade as e on m.fk_esp_med=e.idesp join clinica as cli on m.fk_cli_med = cli.idcli "+
  "where cli.idcli = ?1")
   List<Consulta> listagemPainel(Integer idCli); 

   @Query(nativeQuery = true,  value = "select c.idcons, c.dtagendada,c.hora,c.informacoesadic," +
   "c.confirmada, c.fk_med_cons,c.fk_paci_cons,c.retorno,c.horachegada,c.horasaida,c.duracao,c.concluida,c.naocompareceu,c.cancelada,"+
   "c.fk_rec_cons,c.fk_pag_cons,c.fk_feed_cons, m.nome as Medico, e.nome as Especialidade from consulta "+
   "as c join paciente as p on c.fk_paci_cons=p.idpaci  join medico as m on c.fk_med_cons=m.idmed join "+
   "especialidade as e on m.fk_esp_med=e.idesp join clinica as cli on m.fk_cli_med = cli.idcli where "+
   "m.idmed = ?1")
   List<Consulta> listagemPainelMedInd(Integer idMed); 

   @Query(nativeQuery = true, value = "select c.idcons,c.dtagendada,c.hora,c.informacoesadic,  c.confirmada,"+
   "c.retorno, c.fk_med_cons,c.fk_paci_cons,c.horachegada,c.horasaida,c.duracao,c.concluida,c.naocompareceu,c.cancelada,c.fk_rec_cons,"+
   "c.fk_pag_cons,c.fk_feed_cons, m.nome as Medico, e.nome as Especialidade from consulta "+
   "as c join medico as m on c.fk_med_cons=m.idmed join especialidade as e on m.fk_esp_med=e.idesp "+
   "join paciente as p on c.fk_paci_cons=p.idpaci where p.cpf = ?1 and c.idcons = ?2")
   List<Consulta> listaCancela(String cpf, Integer idCons);

   @Query(nativeQuery = true, value = "select c.idcons, c.hora, c.fk_med_cons,c.fk_med_cons,c.fk_paci_cons,c.dtagendada," +
   "c.informacoesadic, c.confirmada,c.retorno,c.horachegada,c.horasaida,c.duracao,c.concluida,"+
   "c.naocompareceu,c.cancelada,c.fk_rec_cons,c.fk_pag_cons,c.fk_feed_cons, m.valor, e.logradouro, "+
   "e.numero, e.complemento, e.cep  from consulta as c join medico as m on c.fk_med_cons=m.idmed "+
   "join clinica as cli on m.fk_cli_med=cli.idcli join endereco as e on cli.fk_end_cli= e.idend "+
   "where c.idcons = ?1")
   List<Consulta> listaResumo(Integer idCons);
   
   @Query(nativeQuery = true, value = "select c.idcons, c.hora, c.fk_med_cons,c.fk_med_cons,c.fk_paci_cons,c.dtagendada," +
   "c.informacoesadic, c.confirmada,c.retorno,c.horachegada,c.horasaida,c.duracao,c.concluida,"+
   "c.naocompareceu,c.cancelada,c.fk_rec_cons,c.fk_pag_cons,c.fk_feed_cons, m.valor, e.logradouro, "+
   "e.numero, e.complemento, e.cep  from consulta as c join medico as m on c.fk_med_cons=m.idmed "+
   "join clinica as cli on m.fk_cli_med=cli.idcli join endereco as e on cli.fk_end_cli= e.idend "+
   "where c.idcons = ?1")
   List<Consulta> pesquisaConsultaPorPaciente(Integer paciente);
   
   @Query(nativeQuery = true, value = "select * "
   		+ "from consulta as c "
		+ "inner join paciente p on fk_paci_cons = idpaci "
   		+ "where c.concluida = 1 and fk_feed_cons is null and fk_paci_cons = :paciente")
   List<Consulta> pesquisaConsultaSemFeed(Integer paciente);
   
   @Query(nativeQuery = true, value = "select * "
   		+ "from consulta c "
   		+ "inner join medico m on c.fk_med_cons = m.idmed "
   		+ "inner join especialidade as e on m.fk_esp_med = e.idesp "
   		+ "inner join paciente p on c.fk_paci_cons = p.idpaci "
   		+ "where c.concluida = 0 and p.idpaci = ?1 order by dtagendada asc, hora asc")
   List<Consulta> consultasMarcadas(Integer paciente);

   @Query(nativeQuery = true, value="select * from consulta where fk_med_cons=?1 and concluida = 0")
   List<Consulta> consultaConsole(Integer idMed);

   @Query(nativeQuery=true, value="select * from consulta where fk_paci_cons = ?1")
   List<Consulta> consoleDetalhado(Integer idPaci);
   
   @Modifying
   @Query(nativeQuery = true, value="update consulta set naocompareceu = 1 where idcons=?1")
   void mudaConsulta(Integer idCons);

   @Modifying
   @Query(nativeQuery = true, value="update consulta set fk_pag_cons= ?1 where idcons=?2")
   void atualizaIdPagNaIdCons(Integer idPag, Integer idCons);
}
