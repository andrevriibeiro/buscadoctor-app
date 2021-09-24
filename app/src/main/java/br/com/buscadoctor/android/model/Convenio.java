package br.com.buscadoctor.android.model;

/**
 * Created by andreribeiro on 04/07/17.
 */

public class Convenio {

    private int id;
    private String nome;
    private String razaoSocial;
    private String codANS;
    private String cnpj;

    public Convenio() {
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNome() {
        return nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCodANS() {
        return codANS;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
