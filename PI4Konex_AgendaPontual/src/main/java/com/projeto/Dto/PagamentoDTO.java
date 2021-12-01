package com.projeto.Dto;

public class PagamentoDTO {

    private int idcons;
    private Float valor;
    private String nome;
    private String formapagamento;
    private String dtpgto;

    public PagamentoDTO(int idcons, Float valor, String nome, String formapagamento, String dtpgto) {
        this.idcons = idcons;
        this.valor = valor;
        this.nome = nome;
        this.formapagamento = formapagamento;
        this.dtpgto = dtpgto;
    }

    public int getIdcons() {
        return idcons;
    }

    public void setIdcons(int idcons) {
        this.idcons = idcons;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    public String getDtpgto() {
        return dtpgto;
    }

    public void setDtpgto(String dtpgto) {
        this.dtpgto = dtpgto;
    }

}
