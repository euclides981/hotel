package com.hotel.model;

public class Hospedes {


    public Integer id;
    public String nome;
    public String sobrenome;
    public String dataNascimento;
    public String nacionalidade;
    public String telefone;
    public int idReserva;

    public Hospedes() {

    }

    public Hospedes(Integer id, String nome, String sobrenome, String dataNascimento, String nacionalidade, String telefone) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
    }

    public Hospedes(String nome, String sobrenome, String nascimento, String nacionalidade, String telefone, int idReserva) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = nascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.idReserva = idReserva;
    }


    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
