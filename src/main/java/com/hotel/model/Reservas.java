package com.hotel.model;

import java.util.Date;

public class Reservas {
    public int id;
    private int dias;
    private int valorTotal;
    private String entrada;
    private String saida;
    private String suite;
    private String formPag;

    public Reservas(String entrada, String saida, int dias, int valorTotal, String suite, String formPag) {

        this.entrada = entrada;
        this.saida = saida;
        this.dias = dias;
        this.valorTotal = valorTotal;
        this.suite = suite;
        this.formPag = formPag;
    }

    public Reservas() {

    }

    public Reservas(int id, String entrada, String saida, int dias, int valor, String suite, String formPag) {
        this.id = id;
        this.entrada = entrada;
        this.saida = saida;
        this.dias = dias;
        this.valorTotal = valor;
        this.suite = suite;
        this.formPag = formPag;

    }

    public Integer getId() {
        return this.id;
    }

    public Integer getDias() {
        return dias;
    }

    public Integer getValorTotal() {
        return valorTotal;
    }

    public String getEntrada() {
        return entrada;
    }

    public String getSaida() {
        return saida;
    }

    public String getSuite() {
        return suite;
    }

    public String getFormPag() {
        return formPag;
    }


    //SETTERS:

    public void setId(int id) {
        this.id = id;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public void setFormPag(String formPag) {
        this.formPag = formPag;
    }
}
