package com.projeto.Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.projeto.Dto.ConsultaSemFeedDTO;
import com.projeto.Dto.ConsultasPacienteDTO;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class ConsultaRepositorio2Impl implements ConsultaRepositorio2 {
	Locale local = new Locale("pt","BR");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy",local);
	
	@Autowired
    private HikariDataSource dataSource;
	
	@Override
	public List<ConsultaSemFeedDTO> pesquisaConsultaSemFeed(Integer paciente) {
		String sql = "select c.idcons, m.nome "
				+ "from consulta as c "
				+ "inner join medico m on c.fk_med_cons = m.idmed "
				+ "where fk_feed_cons is null and concluida = 1 and c.fk_paci_cons = ";
	
    	if (paciente != null) {
			sql += paciente;
		}
		
    	List<ConsultaSemFeedDTO> consultas = new ArrayList<>();
    	
    	try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
    			
				while (rs.next()) {
					ConsultaSemFeedDTO consulta = new ConsultaSemFeedDTO(
							rs.getInt("c.idcons"),
							rs.getString("m.nome"));	
					consultas.add(consulta);
				}
				stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consultas;
	}

	@Override
	public List<ConsultasPacienteDTO> buscaConsultaPorPaciente(Integer paciente) {
		
		String ordena = " order by c.dtagendada, c.hora";
		
		String sql = "select c.idcons,c.dtagendada,c.hora,e.nome,m.idmed,m.foto,m.nome,m.crm,m.sobremim,"
				+ "en.logradouro,en.numero,en.complemento,m.sala,b.nome,en.cep,cl.fone,m.valor "
				+ "from consulta as c "
				+ "inner join paciente as p on fk_paci_cons = idpaci "
				+ "inner join medico as m on fk_med_cons = idmed "
				+ "inner join especialidade as e on fk_esp_med = idesp "
				+ "inner join clinica as cl on fk_cli_med = idcli "
				+ "inner join endereco as en on fk_end_cli = idend "
				+ "inner join bairro as b on fk_bai_end = idbai "
				+ "inner join cidade as cid on fk_cid_bai = idcid "
				+ "where c.concluida = 0 and  idpaci = ";
	
    	if (paciente != null) {
			sql += paciente;
		}
    	
    	sql += ordena;
    	
    	List<ConsultasPacienteDTO> consultas = new ArrayList<>();

    	try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
    			
				while (rs.next()) {
					ConsultasPacienteDTO consulta = new ConsultasPacienteDTO();
						consulta.setIdCons(rs.getInt("c.idcons"));
						consulta.setIdMed(rs.getInt("m.idmed"));
						consulta.setDtAgendada(rs.getDate("c.dtagendada").toLocalDate());
						consulta.setHora(rs.getTime("c.hora").toLocalTime());
						consulta.setEspecialidade(rs.getString("e.nome"));
						consulta.setFoto(rs.getBytes("m.foto"));
						consulta.setNomeMed(rs.getString("m.nome"));
						consulta.setCrm(rs.getString("m.crm"));
						consulta.setSobreMim(rs.getString("m.sobremim"));
						consulta.setLogradouro(rs.getString("en.logradouro"));
						consulta.setNumero(rs.getInt("en.numero"));
						consulta.setComplemento(rs.getString("en.complemento"));
						consulta.setBairro(rs.getString("b.nome"));
						consulta.setSala(rs.getString("m.sala"));
						consulta.setCep(rs.getString("en.cep"));
						consulta.setTelefone(rs.getString("cl.fone"));
						consulta.setValor(rs.getFloat("m.valor"));
								
					consultas.add(consulta);
				}
				stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consultas;
	}

	@Override
	public ConsultasPacienteDTO buscaConsultaPorId(Integer idCons) {
			
		String sql = "select c.idcons,c.dtagendada,c.hora,e.nome,m.idmed,m.foto,m.nome,m.crm,m.sobremim,"
				+ "en.logradouro,en.numero,en.complemento,m.sala,b.nome,en.cep,cl.fone,m.valor "
				+ "from consulta as c "
				+ "inner join paciente as p on fk_paci_cons = idpaci "
				+ "inner join medico as m on fk_med_cons = idmed "
				+ "inner join especialidade as e on fk_esp_med = idesp "
				+ "inner join clinica as cl on fk_cli_med = idcli "
				+ "inner join endereco as en on fk_end_cli = idend "
				+ "inner join bairro as b on fk_bai_end = idbai "
				+ "inner join cidade as cid on fk_cid_bai = idcid "
				+ "where idcons = ";
	
    	if (idCons != null) {
			sql += idCons;
		}
    	ConsultasPacienteDTO consulta = new ConsultasPacienteDTO();

    	try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
    			
				if (rs.next()) {
					
						consulta.setIdCons(rs.getInt("c.idcons"));
						consulta.setIdMed(rs.getInt("m.idmed"));
						consulta.setDtAgendada(rs.getDate("c.dtagendada").toLocalDate());
						consulta.setHora(rs.getTime("c.hora").toLocalTime());
						consulta.setEspecialidade(rs.getString("e.nome"));
						consulta.setFoto(rs.getBytes("m.foto"));
						consulta.setNomeMed(rs.getString("m.nome"));
						consulta.setCrm(rs.getString("m.crm"));
						consulta.setSobreMim(rs.getString("m.sobremim"));
						consulta.setLogradouro(rs.getString("en.logradouro"));
						consulta.setNumero(rs.getInt("en.numero"));
						consulta.setComplemento(rs.getString("en.complemento"));
						consulta.setBairro(rs.getString("b.nome"));
						consulta.setSala(rs.getString("m.sala"));
						consulta.setCep(rs.getString("en.cep"));
						consulta.setTelefone(rs.getString("cl.fone"));
						consulta.setValor(rs.getFloat("m.valor"));
				}
				stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consulta;
	}
}
