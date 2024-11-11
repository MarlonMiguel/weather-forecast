package com.example.trabalho.model;

public class Cidade {

    private String nome;
    private String id;
    private String siglaEst;

    public Cidade(String nome, String siglaEst) {
        this.nome = nome;
        this.siglaEst = siglaEst;
    }

    public Cidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiglaEst() {
        return siglaEst;
    }

    public void setSiglaEst(String siglaEst) {
        this.siglaEst = siglaEst;
    }
}
