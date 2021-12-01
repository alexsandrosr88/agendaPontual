package com.projeto.Dto;

public class PagDinDTO {

    private Integer qtdDinheiro;
    

    public PagDinDTO() {
    }

    
    public PagDinDTO(Integer qtdDinheiro) {
        this.qtdDinheiro = qtdDinheiro;
    }


    public Integer getQtdDinheiro() {
        return qtdDinheiro;
    }

    public void setQtdDinheiro(Integer qtdDinheiro) {
        this.qtdDinheiro = qtdDinheiro;
    }
    
}
