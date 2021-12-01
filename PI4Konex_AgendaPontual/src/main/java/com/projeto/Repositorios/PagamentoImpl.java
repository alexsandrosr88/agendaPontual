package com.projeto.Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.Dto.PagCredDTO;
import com.projeto.Dto.PagDebDTO;
import com.projeto.Dto.PagDinDTO;
import com.projeto.Dto.PagTotalDTO;
import com.projeto.Dto.PagamentoDTO;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PagamentoImpl implements PagamentoRepositorio {

    @Autowired
    private HikariDataSource dataSource;

    @Override
    public List<PagamentoDTO> listaPagPorMed(Integer idMed) {
        String linhaSql = "select c.idcons, p.nome,pag.valor,pag.formapagamento,pag.dtpgto from  consulta c "+ 
        "inner join medico m on c.fk_med_cons = m.idmed "+
        "inner join paciente p on c.fk_paci_cons = p.idpaci " + 
        "inner join pagamento pag on c.fk_pag_cons = pag.idpag "+
        "where idmed= ";

        List<PagamentoDTO> pagas = new ArrayList<>();

        if(idMed!=null){
            linhaSql+=idMed;

            

            try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(linhaSql);
            ResultSet rs = stmt.executeQuery()) {
        
        
            while (rs.next()) {
                PagamentoDTO pag = new PagamentoDTO(
                        rs.getInt("c.idcons"),
                        rs.getFloat("pag.valor"),
                        rs.getString("p.nome"),
                        rs.getString("pag.formapagamento"),
                        rs.getString("pag.dtpgto"));
                        pagas.add(pag);
                    }
                    stmt.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                

            }
            return pagas;
        }

    @Override
    public PagTotalDTO qtdTotal(Integer idMed) {
       		String sql = "select count(pag.idpag) as qtdTotal from pagamento pag "
               +"inner join consulta c on pag.idpag=c.fk_pag_cons " 
               +"inner join medico m on m.idmed=c.fk_med_cons "
               +"where m.idmed= ";
               
    	if(idMed != null) {
    		sql += idMed;
    	}
    	
    	PagTotalDTO pag = new PagTotalDTO();
		
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
				
				if (rs.next()) {
					pag.setQtdTotal(rs.getInt("qtdTotal"));
				}
				stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pag;
    }

    @Override
    public PagDinDTO qtdPagDin(Integer idMed) {
        String sql = "select count(pag.idpag) as qtdDinheiro from pagamento pag "
        + "inner join consulta c on pag.idpag=c.fk_pag_cons " 
        + "inner join medico m on m.idmed=c.fk_med_cons "
        + "where pag.formapagamento='Dinheiro' and m.idmed= ";

        if (idMed != null) {
            sql += idMed;
        }

        PagDinDTO pag = new PagDinDTO();

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                pag.setQtdDinheiro(rs.getInt("qtdDinheiro"));
            }
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pag;
    }

    @Override
    public PagDebDTO qtdPagDeb(Integer idMed) {
        String sql = "select count(pag.idpag) as qtdCartaoDebito from pagamento pag "
        +"inner join consulta c on pag.idpag=c.fk_pag_cons "
        +"inner join medico m on m.idmed=c.fk_med_cons "
        +"where pag.formapagamento='Cartão de Débito' and m.idmed= ";

        if (idMed != null) {
            sql += idMed;
        }

        PagDebDTO pag = new PagDebDTO();

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                pag.setQtdDebito(rs.getInt("qtdCartaoDebito"));
            }
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pag;
    }

    @Override
    public PagCredDTO qtdPagCred(Integer idMed) {
        String sql = "select count(pag.idpag) as qtdCartaoCredito from pagamento pag "
        +"inner join consulta c on pag.idpag=c.fk_pag_cons "
        +"inner join medico m on m.idmed=c.fk_med_cons "
        +"where pag.formapagamento='Cartão de Crédito' and m.idmed="
        ;

        if (idMed != null) {
            sql += idMed;
        }

        PagCredDTO pag = new PagCredDTO();

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                pag.setQtdCredito(rs.getInt("qtdCartaoCredito"));
            }
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pag;
    }

}
