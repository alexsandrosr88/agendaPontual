package com.projeto.Controladores;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.Servicos.ClinicasServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClinicaController {

    @Autowired
    ClinicasServico cliServ;
    
    @GetMapping("/clinicas")
	@ResponseBody
	public String listaClinicas(@RequestParam Integer idcid) {
		String json = null;
		List<Object[]> list = cliServ.buscaClinicas(idcid);
		try {
			json = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
}
