package com.projeto.Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.projeto.Dto.AvaliacoesNegativasDTO;
import com.projeto.Dto.AvaliacoesPositivasDTO;
import com.projeto.Dto.FeedbackCliMedDTO;
import com.projeto.Dto.MelhoresFeedbacksDTO;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class FeedbackImpl implements FeedbackRepositorio{
	
	@Autowired
    private HikariDataSource dataSource;
	
	@Override
	public List<MelhoresFeedbacksDTO> buscaFeedbackPorMedico(Integer idMed) {
    	
		String sql = "select p.foto, f.comentario, p.nome from consulta c "
    			+ "inner join medico m on c.fk_med_cons = m.idmed "
    			+ "inner join paciente p on fk_paci_cons = idpaci "
    			+ "inner join especialidade es on fk_esp_med = es.idesp "
    			+ "inner join endereco en on fk_end_med = en.idend "
    			+ "inner join feedback f on fk_feed_cons = f.idfeed "
    			+ "where m.idmed = ";
    	
    	if(idMed != null) {
    		sql += idMed;
    	}
	
        List<MelhoresFeedbacksDTO> feeds = new ArrayList<>();
		
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
			
        	
				while (rs.next()) {
					MelhoresFeedbacksDTO feed = new MelhoresFeedbacksDTO(
							rs.getBytes("p.foto"),
							rs.getString("f.comentario"),
							rs.getString("p.nome"));
					feeds.add(feed);
				}
				stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feeds;
    	
	}

	@Override
	public AvaliacoesPositivasDTO buscaAvaPositiva(Integer idMed) {
    	
		String sql = "select count(f.avaliacao) as positiva from consulta c "
				+ "inner join medico m on c.fk_med_cons = m.idmed "
				+ "inner join feedback f on c.fk_feed_cons = f.idfeed "
				+ "where f.avaliacao >= 8 and paramedico = 1 and m.idmed = ";
    	
    	if(idMed != null) {
    		sql += idMed;
    	}
    	
    	AvaliacoesPositivasDTO positiva = new AvaliacoesPositivasDTO();
		
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
				
				if (rs.next()) {
					positiva.setPositiva(rs.getInt("positiva"));
				}
				stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return positiva;
	}

	@Override
	public AvaliacoesNegativasDTO buscaAvaNegativa(Integer idMed) {
		
		String sql = "select count(f.avaliacao) as negativa from consulta c "
				+ "inner join medico m on c.fk_med_cons = m.idmed "
				+ "inner join feedback f on c.fk_feed_cons = f.idfeed "
				+ "where f.avaliacao <= 5 and paramedico = 1 and m.idmed = ";
    	
    	if(idMed != null) {
    		sql += idMed;
    	}
    	
    	AvaliacoesNegativasDTO negativa = new AvaliacoesNegativasDTO();
		
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
				
				if (rs.next()) {
					negativa.setNegativa(rs.getInt("negativa"));
				}
				stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return negativa;
	}

	@Override
	public List<FeedbackCliMedDTO> listaFeedback(Integer idMed) {
    	
		String sql = "select f.idfeed, p.foto, f.comentario, p.nome,f.avaliacao, c.dtagendada, c.hora from consulta c "
    			+ "inner join medico m on c.fk_med_cons = m.idmed "
    			+ "inner join paciente p on fk_paci_cons = idpaci "
    			+ "inner join especialidade es on fk_esp_med = es.idesp "
    			+ "inner join endereco en on fk_end_med = en.idend "
    			+ "inner join feedback f on fk_feed_cons = f.idfeed "
    			+ "where m.idmed = ";
    	
    	if(idMed != null) {
    		sql += idMed;
    	}
	
        List<FeedbackCliMedDTO> feeds = new ArrayList<>();
		
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
			
        	
				while (rs.next()) {
					FeedbackCliMedDTO feed = new FeedbackCliMedDTO(
						    rs.getInt("f.idfeed"),
							rs.getBytes("p.foto"),
							rs.getString("f.comentario"),
							rs.getInt("f.avaliacao"),
							rs.getString("p.nome"),
							rs.getDate("c.dtagendada").toLocalDate(),
							rs.getTime("c.hora").toLocalTime());
					feeds.add(feed);
				}
				stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feeds;
    	
	}

	@Override
	public List<FeedbackCliMedDTO> listaFeedporId(Integer idFeed) {
		String sql = "select f.idfeed, p.foto, f.comentario, p.nome, m.nome, f.avaliacao, c.dtagendada, c.hora from consulta c "
    			+ "inner join medico m on c.fk_med_cons = m.idmed "
    			+ "inner join paciente p on fk_paci_cons = idpaci "
    			+ "inner join especialidade es on fk_esp_med = es.idesp "
    			+ "inner join endereco en on fk_end_med = en.idend "
    			+ "inner join feedback f on fk_feed_cons = f.idfeed "
    			+ "where f.idfeed = ";
    	
    	if(idFeed != null) {
    		sql += idFeed;
    	}
	
        List<FeedbackCliMedDTO> feeds = new ArrayList<>();
		
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
			
        	
				while (rs.next()) {
					FeedbackCliMedDTO feed = new FeedbackCliMedDTO(
						    rs.getInt("f.idfeed"),
							rs.getBytes("p.foto"),
							rs.getString("f.comentario"),
							rs.getInt("f.avaliacao"),
							rs.getString("p.nome"),
							rs.getString("m.nome"),
							rs.getDate("c.dtagendada").toLocalDate(),
							rs.getTime("c.hora").toLocalTime());
					feeds.add(feed);
				}
				stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feeds;
	}
}
