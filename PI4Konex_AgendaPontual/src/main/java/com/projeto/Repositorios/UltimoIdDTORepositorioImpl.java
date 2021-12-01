package com.projeto.Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.projeto.Dto.UltimoIdDTO;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class UltimoIdDTORepositorioImpl implements UltimoIdDTORepositorio {

	@Autowired
    private HikariDataSource dataSource;
	
	@Override
	public UltimoIdDTO buscaUltimo() {
		String sql = "select @@identity as ultimo";

		UltimoIdDTO num = new UltimoIdDTO();

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				num.setNum(rs.getInt("ultimo"));
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
}
