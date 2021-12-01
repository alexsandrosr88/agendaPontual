package com.projeto.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "usuario")
public class Usuario{
    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idusu")
    private Integer idUsu;

    @Column(nullable = false)
    @NotBlank
    @NotNull
    private String email;

    @Column(name="emailconfirma",nullable = true)
    private String confirmaEmail;

    @Column(nullable = false)
    @Length(min=6,max=12)
    private String senha;

    @Column(name="senhaconfirma",nullable = true)
    @Length(min=6,max=12)
    private String confirmaSenha;

    @Column(nullable = true)
    private String perfil;
    

    // Metodo Construtor
    public Usuario() {

    }

    // Metodo Construtor com Atributos
    public Usuario(Integer idUsu, String email, String confirmaEmail, String senha, String confirmaSenha, String perfil) {
        this.idUsu = idUsu;
        this.email = email;
        this.confirmaEmail = confirmaEmail;
        this.senha = senha;
        this.confirmaSenha = confirmaSenha;
        this.perfil = perfil;
    }

    // Getters e Setters
    public Integer getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Integer idUsu) {
        this.idUsu = idUsu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmaEmail() {
        return confirmaEmail;
    }

    public void setConfirmaEmail(String confirmaEmail) {
        this.confirmaEmail = confirmaEmail;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    
}
