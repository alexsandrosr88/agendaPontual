package com.projeto.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.Servicos.CidadeServico;

@Controller
public class CidadeController {
	
	
	@Autowired
	private CidadeServico cidServ;

	@GetMapping("/bairro")
	@ResponseBody
	public String listaBairro(@RequestParam Integer idcid){
		
		String json = null;
		List<Object[]> list = cidServ.buscaBairroPorCidade(idcid);
		try {
			json = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
}
