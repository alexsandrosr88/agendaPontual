package com.projeto.Controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projeto.Dto.AutoCompleteDTO;
import com.projeto.Entidades.Especialidade;
import com.projeto.Servicos.EspecialidadeServico;

@Controller
public class EspecialidadeController {

	@Autowired
	private EspecialidadeServico espServ;
	
	private List<Especialidade> espFiltradas = new ArrayList<>();
	
	@RequestMapping("/especialidade/nome")
	@ResponseBody
	public List<AutoCompleteDTO> cidadesNomeAutoComplete(@RequestParam(value="term", required = false, defaultValue = "") String term) {
		List<AutoCompleteDTO> sugestoes = new ArrayList<>();
		
		
		try {
			if(term.length() >= 3) {
				espFiltradas = espServ.buscaEspNome(term.trim().toLowerCase());
			}
			
			for (Especialidade esp : espFiltradas) {
				if (esp.getNomeEsp().toLowerCase().contains(term.toLowerCase())) {
					AutoCompleteDTO dto = new AutoCompleteDTO(esp.getNomeEsp());
					sugestoes.add(dto);
				}			
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sugestoes;
	}	
}
