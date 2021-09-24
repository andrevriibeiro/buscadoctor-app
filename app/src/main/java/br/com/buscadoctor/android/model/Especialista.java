package br.com.buscadoctor.android.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * @author Guilherme Mendes <gmendes92@gmail.com>
 * @version 1.0.0
 * @since 1.0.0
 */
public class Especialista implements Parcelable {

    private int id;
    private String nome;
    private String razaosocial;
    private String cnpj;
    private String cpf;
    private String rg;
    private String crm;
    private Double rating;
    private String sexo;
    private String foto;
    private String usuario;
    private String senha;
    private String email;
    private Integer numero;
    private String celular;
    private String telefoneresidencia;
    private String telefonerecado;
    private Date createdAt;
    private Logradouro logradouro;

    public Especialista() {
    }

    public Especialista(Parcel in) {
        readFromParcel(in);
    }

    public Especialista(int id, String nome, String razaosocial, String cnpj, String cpf, String rg,
                        String crm, Double rating, String sexo, String foto, String usuario, String senha,
                        String email, Integer numero, String celular, String telefoneresidencia, String telefonerecado,
                        Date createdAt) {
        this.id = id;
        this.nome = nome;
        this.razaosocial = razaosocial;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.rg = rg;
        this.crm = crm;
        this.rating = rating;
        this.sexo = sexo;
        this.foto = foto;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.numero = numero;
        this.celular = celular;
        this.telefoneresidencia = telefoneresidencia;
        this.telefonerecado = telefonerecado;
        this.createdAt = createdAt;
    }

    public int getId() {
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

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefoneresidencia() {
        return telefoneresidencia;
    }

    public void setTelefoneresidencia(String telefoneresidencia) {
        this.telefoneresidencia = telefoneresidencia;
    }

    public String getTelefonerecado() {
        return telefonerecado;
    }

    public void setTelefonerecado(String telefonerecado) {
        this.telefonerecado = telefonerecado;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(razaosocial);
        dest.writeString(cnpj);
        dest.writeString(cpf);
        dest.writeString(rg);
        dest.writeString(crm);
        dest.writeDouble(rating);
        dest.writeString(sexo);
        dest.writeString(email);
    }

    public static final Creator CREATOR = new Creator<Especialista>() {
        @Override
        public Especialista createFromParcel(Parcel source) {
            return new Especialista(source);
        }

        @Override
        public Especialista[] newArray(int size) {
            return new Especialista[size];
        }
    };

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        razaosocial = in.readString();
        cnpj = in.readString();
        cpf = in.readString();
        rg = in.readString();
        crm = in.readString();
        rating = in.readDouble();
        sexo = in.readString();
        email = in.readString();
    }
}