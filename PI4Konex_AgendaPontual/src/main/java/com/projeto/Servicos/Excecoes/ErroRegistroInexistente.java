package com.projeto.Servicos.Excecoes;


public class ErroRegistroInexistente extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ErroRegistroInexistente(String msg) {
		super(msg);
	}
}
