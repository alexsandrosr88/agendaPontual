package com.projeto.Entidades;


import javax.persistence.*;

@Entity
@Table(name = "recepcionista")
public class Recepcionista {
    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrecep")
    private int idRec;

    @Column(name = "nome", nullable = true)
    private String nomeRec;

    @Column(name = "cpf", nullable = true)
    private String cpfRec;

    @Column(name = "sexo", nullable = true)
    private String sexoRec;

    @Column(name = "datanasci", nullable = true, columnDefinition = "DATE")
    private String dataNasc;

    @Column(nullable = true)
    private String celular;

    @ManyToOne
    @JoinColumn(name = "fk_cli_recep")
    private Clinica idCli;
    
    @ManyToOne
    @JoinColumn(name = "fk_usu_recep")
    private Usuario idUsu;

    // Metodo Construtor
    public Recepcionista() {

    }

    // Metodo Construtor com Atributos

    public Recepcionista(int idRec, String nomeRec, String cpfRec, String sexoRec, String dataNasc, String celular,
    Clinica idCli, Usuario idUsu) {
        this.idRec = idRec;
        this.nomeRec = nomeRec;
        this.cpfRec = cpfRec;
        this.sexoRec = sexoRec;
        this.dataNasc = dataNasc;
        this.celular = celular;
        this.idCli = idCli;
        this.idUsu = idUsu;
    }

    // Getters e Setters
    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public String getNomeRec() {
        return nomeRec;
    }

    public void setNomeRec(String nomeRec) {
        this.nomeRec = nomeRec;
    }

    public String getCpfRec() {
        return cpfRec;
    }

    public void setCpfRec(String cpfRec) {
        this.cpfRec = cpfRec;
    }

    public String getSexoRec() {
        return sexoRec;
    }

    public void setSexoRec(String sexoRec) {
        this.sexoRec = sexoRec;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Clinica getIdCli() {
        return idCli;
    }

    public void setIdCli(Clinica idCli) {
        this.idCli = idCli;
    }

    public Usuario getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Usuario idUsu) {
        this.idUsu = idUsu;
    }

}
