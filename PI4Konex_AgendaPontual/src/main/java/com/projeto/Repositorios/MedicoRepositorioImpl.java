package com.projeto.Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.projeto.Dto.HorariosQuaDTO;
import com.projeto.Dto.HorariosQuiDTO;
import com.projeto.Dto.HorariosSabDTO;
import com.projeto.Dto.HorariosSegDTO;
import com.projeto.Dto.HorariosSexDTO;
import com.projeto.Dto.HorariosTerDTO;
import com.projeto.Dto.MedicoResumoDTO;
import com.projeto.Dto.MelhoresFeedbacksDTO;
import com.projeto.Dto.QuantidadeAtendimentosDTO;
import com.projeto.Entidades.Clinica;
import com.projeto.Entidades.Endereco;
import com.projeto.Entidades.Especialidade;
import com.projeto.Entidades.Medico;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class MedicoRepositorioImpl implements MedicoRepositorio{
	
	@Autowired
    private HikariDataSource dataSource;
    
    @Override
    public List<Medico> buscaMedCompleta(Integer cidade, Integer bairro, String espec, String sexMas, String sexFem,
			Float valorMin, Float valorMax, Integer minExp, Integer maxExp) {
    	String condicao = "where ";
    	String orderDesc = " order by m.pontos desc";
    	
    	String sql = "select m.idmed, m.pontos,m.foto, m.nome, m.sobremim "
				+ "from clinica c inner join medico m on m.fk_cli_med = c.idcli "
				+ "inner join especialidade esp on m.fk_esp_med = esp.idesp "
				+ "inner join endereco e on c.fk_end_cli = e.idend "
				+ "inner join bairro b on e.fk_bai_end = b.idbai "
				+ "inner join cidade cid on b.fk_cid_bai = cid.idcid ";
		
    	if (cidade != null) {
			sql += condicao + "cid.idcid = "+ cidade;
			condicao = " and ";
		}
		if (bairro != null) {
			sql += condicao + "b.idbai = "+ bairro;
		}
		if (espec != null && !(espec.isEmpty())) {
			sql += condicao + "esp.nome = "+ "\""+espec+"\"";
			condicao = " and ";
		}
		if (sexFem != null && sexMas != null) {
			sql += condicao + "m.sexo = "+ "\""+sexFem+"\"";
			condicao = " or ";
		}
		if (sexFem != null && condicao != " or ") {
			sql += condicao + "m.sexo = "+ "\""+sexFem+"\"";
			condicao = " and ";
		}
		if (sexMas != null) {
			sql += condicao + "m.sexo = "+ "\""+sexMas+"\"";
			condicao = " and ";
		}
		if (valorMin != null) {
			sql += condicao+ "m.valor "+"between " + valorMin;
			condicao = " and ";
		}
		if (valorMax != null) {
			sql += condicao+ valorMax;
		}
		if (minExp != null) {
			sql += condicao+ "TIMESTAMPDIFF(YEAR, dataformatura, CURDATE()) between " + minExp;
			condicao = " and ";
		}
		if (maxExp != null) {
			sql += condicao + maxExp;
		}
		
		sql += orderDesc;
		
        List<Medico> medicos = new ArrayList<>();
			
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
			

				while (rs.next()) {
					Medico med = new Medico(
							rs.getInt("m.idmed"),
							rs.getBytes("m.foto"),
							rs.getInt("m.pontos"),
							rs.getString("m.nome"),
							rs.getString("m.sobremim"));
					medicos.add(med);
				}
				stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medicos;
    }

	@Override
	public List<Medico> listaMedPorCli(Integer id) {

		String sql = "select * from medico as m join clinica as c "
				+ "on m.fk_cli_med=c.idcli where c.idcli= ?";

		List<Medico> medicos = new ArrayList<>();

		try {
			
			Connection conn = dataSource.getConnection();	

			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();
		

				while (rs.next()) {
					Medico med = new Medico();
					med.setIdMed(rs.getInt("idmed"));
					med.setFoto(rs.getBytes("foto"));
					med.setPontos(rs.getInt("pontos"));
					med.setNomeMed(rs.getString("nome"));
					med.setDataNasc(rs.getDate("datanasci").toLocalDate());
					med.setSexoMed(rs.getNString("sexo"));
					med.setCpfMed(rs.getString("cpf"));
					med.setRgMed(rs.getString("rg"));
					med.setCrm(rs.getString("rg"));
					med.setCelular(rs.getString("celular"));
					med.setValorMed(rs.getFloat("valor"));
					med.setCelular(rs.getString("celular"));
					med.setDataFormatura(rs.getDate("dataformatura").toLocalDate());
					med.setSobreMed(rs.getString("sobremim"));
					med.setBioMed(rs.getString("biografia"));
					med.setSala(rs.getString("sala"));
					Endereco end = new Endereco();
					end.setIdEnd(rs.getInt("fk_end_med"));
					med.setEndereco(end);
					Clinica cli = new Clinica();
					cli.setIdCli(rs.getInt("fk_cli_med"));
					med.setClinica(cli);
					Especialidade esp = new Especialidade();
					esp.setIdEsp(rs.getInt("fk_esp_med"));
					med.setEspecialidade(esp);
					medicos.add(med);
				}
				rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medicos;
	}


	@Override
	public List<Medico> findAll() {
		String sql = "select * from medico";
		return null;
	}
	

    @Override
	public List<Medico> filtraMedCli(Integer idEsp, Integer idCid, Integer idCli,
	String sexMas, String sexFem,Float valorMin, Float valorMax){
		String cond = "where ";
    	String ordena = " order by m.pontos desc";

		String linhaSql="select m.idmed, m.pontos,m.foto, m.nome, m.sobremim "
		+ "from clinica c inner join medico m on m.fk_cli_med = c.idcli "
		+ "inner join especialidade esp on m.fk_esp_med = esp.idesp "
		+ "inner join endereco e on c.fk_end_cli = e.idend "
		+ "inner join bairro b on e.fk_bai_end = b.idbai "
		+ "inner join cidade cid on b.fk_cid_bai = cid.idcid ";

		List<Medico> medFiltrado = new ArrayList<>();


		try {
			Connection conn = dataSource.getConnection();

			if (idCid != null) {
				linhaSql += cond + "cid.idcid = "+ idCid;
				cond = " and ";
			}
			if (idCli != null) {
				linhaSql += cond + "c.idcli = "+ idCli;
			}
			if (idEsp != null) {
				linhaSql += cond + "esp.idesp = "+idEsp;
				cond = " and ";
			}
			if (sexFem != null && sexMas != null) {
				linhaSql += cond + "m.sexo = "+ "\""+sexFem+"\"";
				cond = " or ";
			}
			if (sexFem != null && cond != " or ") {
				linhaSql += cond + "m.sexo = "+ "\""+sexFem+"\"";
				cond = " and ";
			}
			if (sexMas != null) {
				linhaSql += cond + "m.sexo = "+ "\""+sexMas+"\"";
				cond = " and ";
			}
			if (valorMin != null) {
				linhaSql += cond+ "m.valor "+"between " + valorMin;
				cond = " and ";
			}
			if (valorMax != null) {
				linhaSql += cond+ valorMax;
			}

			linhaSql += ordena;

			PreparedStatement st = conn.prepareStatement(linhaSql);
			ResultSet rs = st.executeQuery();


				while (rs.next()) {
					Medico med = new Medico(
							rs.getInt("m.idmed"),
							rs.getBytes("m.foto"),
							rs.getInt("m.pontos"),
							rs.getString("m.nome"),
							rs.getString("m.sobremim"));
					medFiltrado.add(med);
				}
				rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medFiltrado;


	}

	
	@Override
	public MedicoResumoDTO medicoResumo(Integer idMed) {

		Locale local = new Locale("pt","BR");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy",local);
		
		String sql = "select m.idmed, m.foto, m.nome, m.pontos, es.nome, m.biografia, m.crm crm, "
				+ "m.dataformatura emissao, concat(en.logradouro, ', ',en.numero, ' - ', ci.nome) logradouro, "
				+ "concat( 'CEP: ', en.cep, ' - Compl.: ', en.complemento, ' - Sala: ', m.sala) complemento, cl.fone, m.valor "
				+ "from medico m "
				+ "inner join especialidade es on m.fk_esp_med = es.idesp "
				+ "inner join clinica cl on m.fk_cli_med = cl.idcli "
				+ "inner join endereco en on cl.fk_end_cli = en.idend "
				+ "inner join bairro b on en.fk_bai_end = b.idbai "
				+ "inner join cidade ci on b.fk_cid_bai = ci.idcid "
				+ "where m.idmed = ";
		
		if(idMed != null) {
			sql += idMed;
		}
		MedicoResumoDTO medico = new MedicoResumoDTO();
		
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			
			if (rs.next()) {
				medico.setId(rs.getInt("m.idmed"));
				medico.setFoto(rs.getBytes("m.foto"));
				medico.setNome(rs.getString("m.nome"));
				medico.setPontos(rs.getInt("m.pontos"));
				medico.setEspecialidade(rs.getString("es.nome"));
				medico.setBiografia(rs.getString("m.biografia"));
				medico.setCrm("CRM: "+rs.getString("m.crm"));
				medico.setDataEmissao("Data de Emissão: " +rs.getDate("emissao").toLocalDate().format(formatter));
				medico.setLogradouro(rs.getString("logradouro"));
				medico.setComplemento(rs.getString("complemento"));
				medico.setTelefone(rs.getString("cl.fone"));
				medico.setValor("Valor da consulta: R$ " +rs.getString("m.valor"));
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medico;
	}

	@Override
	public QuantidadeAtendimentosDTO qteAtendimentos(Integer idMed) {

		String sql = "select concat(count(concluida), ' atendimentos') as total from consulta c "
				+ "inner join medico m on c.fk_med_cons = m.idmed "
				+ "where concluida = 1 and m.idmed = ";
		
		if(idMed != null) {
			sql += idMed;
		}
		QuantidadeAtendimentosDTO qte = new QuantidadeAtendimentosDTO();
		
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			
			if (rs.next()) {
				qte.setQuantidade(rs.getString("total"));
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qte;
	}
							
	@Override
	public List<HorariosSegDTO> buscaSeg(Integer idMed) {
		String sql = "select horario from agenda_medica a "
				+ "inner join horario h on fk_hor_agen = idhor "
				+ "where fk_dia_agen = 2 and fk_med_agen = ";

		if (idMed != null) {
			sql += idMed;
		}

		List<HorariosSegDTO> horarios = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				HorariosSegDTO seg = new HorariosSegDTO(rs.getTime("horario").toLocalTime());
				horarios.add(seg);
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarios;
	}

	@Override
	public List<HorariosTerDTO> buscaTer(Integer idMed) {
		String sql = "select horario from agenda_medica a "
				+ "inner join horario h on fk_hor_agen = idhor "
				+ "where fk_dia_agen = 3 and fk_med_agen = ";

		if (idMed != null) {
			sql += idMed;
		}

		List<HorariosTerDTO> horarios = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				HorariosTerDTO ter = new HorariosTerDTO(rs.getTime("horario").toLocalTime());
				horarios.add(ter);
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarios;
	}

	@Override
	public List<HorariosQuaDTO> buscaQua(Integer idMed) {
		String sql = "select horario from agenda_medica a "
				+ "inner join horario h on fk_hor_agen = idhor "
				+ "where fk_dia_agen = 4 and fk_med_agen = ";

		if (idMed != null) {
			sql += idMed;
		}

		List<HorariosQuaDTO> horarios = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				HorariosQuaDTO qua = new HorariosQuaDTO(rs.getTime("horario").toLocalTime());
				horarios.add(qua);
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarios;
	}

	@Override
	public List<HorariosQuiDTO> buscaQui(Integer idMed) {
		String sql = "select horario from agenda_medica a "
				+ "inner join horario h on fk_hor_agen = idhor "
				+ "where fk_dia_agen = 5 and fk_med_agen = ";

		if (idMed != null) {
			sql += idMed;
		}

		List<HorariosQuiDTO> horarios = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				HorariosQuiDTO qui = new HorariosQuiDTO(rs.getTime("horario").toLocalTime());
				horarios.add(qui);
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarios;
	}

	@Override
	public List<HorariosSexDTO> buscaSex(Integer idMed) {
		String sql = "select horario from agenda_medica a "
				+ "inner join horario h on fk_hor_agen = idhor "
				+ "where fk_dia_agen = 6 and fk_med_agen = ";

		if (idMed != null) {
			sql += idMed;
		}

		List<HorariosSexDTO> horarios = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				HorariosSexDTO sex = new HorariosSexDTO(rs.getTime("horario").toLocalTime());
				horarios.add(sex);
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarios;
	}

	@Override
	public List<HorariosSabDTO> buscaSab(Integer idMed) {
		String sql = "select horario from agenda_medica a "
				+ "inner join horario h on fk_hor_agen = idhor "
				+ "where fk_dia_agen = 7 and fk_med_agen = ";

		if (idMed != null) {
			sql += idMed;
		}

		List<HorariosSabDTO> horarios = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				HorariosSabDTO sab = new HorariosSabDTO(rs.getTime("horario").toLocalTime());
				horarios.add(sab);
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarios;
	}

	@Override
	public Medico infoMed(Integer idMed) {
		String sql = "select * from medico where idmed=";

		if (idMed != null) {
			sql += idMed;
		}
		Medico med = new Medico();

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {

				med.setIdMed(rs.getInt("idmed"));
				med.setFoto(rs.getBytes("foto"));
				med.setPontos(rs.getInt("pontos"));
				med.setNomeMed(rs.getString("nome"));
				med.setDataNasc(rs.getDate("datanasci").toLocalDate());
				med.setSexoMed(rs.getNString("sexo"));
				med.setCpfMed(rs.getString("cpf"));
				med.setRgMed(rs.getString("rg"));
				med.setCrm(rs.getString("crm"));
				med.setCelular(rs.getString("celular"));
				med.setValorMed(rs.getFloat("valor"));
				med.setCelular(rs.getString("celular"));
				med.setDataFormatura(rs.getDate("dataformatura").toLocalDate());
				med.setSobreMed(rs.getString("sobremim"));
				med.setBioMed(rs.getString("biografia"));
				med.setSala(rs.getString("sala"));
				Endereco end = new Endereco();
				end.setIdEnd(rs.getInt("fk_end_med"));
				med.setEndereco(end);
				Clinica cli = new Clinica();
				cli.setIdCli(rs.getInt("fk_cli_med"));
				med.setClinica(cli);
				Especialidade esp = new Especialidade();
				esp.setIdEsp(rs.getInt("fk_esp_med"));
				med.setEspecialidade(esp);
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return med;
	}


	
	
}