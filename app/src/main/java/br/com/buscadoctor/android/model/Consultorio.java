package br.com.buscadoctor.android.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * @author Guilherme Mendes
 * @version 1.0
 * @since 1.0.0
 */
public class Consultorio implements Parcelable {

    private int id;
    private String nome;
    private String telefone;
    private Integer numero;
    private String complemento;
    private Double rating;
    private String banner;
    private Boolean marketing;
    private Integer limiteEspecialistas;
    private String email;
    private Integer prioridade;
    private String cnpj;
    private String cnes;
    private String razaosocial;
    private Date createdAt;
    private Logradouro logradouro;
    private Tipo tipo;

    public Consultorio() {
    }

    public Consultorio(Parcel in) {
        readFromParcel(in);
    }

    public Consultorio(int id, String nome, String telefone, Integer numero, String complemento,
                       Double rating, String banner, Boolean marketing, Integer limiteEspecialistas,
                       String email, Integer prioridade, String cnpj, String cnes, String razaosocial,
                       Date createdAt, Logradouro logradouro, Tipo tipo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.numero = numero;
        this.complemento = complemento;
        this.rating = rating;
        this.banner = banner;
        this.marketing = marketing;
        this.limiteEspecialistas = limiteEspecialistas;
        this.email = email;
        this.prioridade = prioridade;
        this.cnpj = cnpj;
        this.cnes = cnes;
        this.razaosocial = razaosocial;
        this.createdAt = createdAt;
        this.logradouro = logradouro;
        this.tipo = tipo;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Boolean getMarketing() {
        return marketing;
    }

    public void setMarketing(Boolean marketing) {
        this.marketing = marketing;
    }

    public Integer getLimiteEspecialistas() {
        return limiteEspecialistas;
    }

    public void setLimiteEspecialistas(Integer limiteEspecialistas) {
        this.limiteEspecialistas = limiteEspecialistas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(telefone);
        dest.writeInt(numero);
        dest.writeString(complemento);
        dest.writeDouble(rating);
        dest.writeString(banner);
        dest.writeString(email);
        dest.writeValue(logradouro);
        dest.writeValue(tipo);
    }

    public static final Creator CREATOR = new Creator<Consultorio>() {
        @Override
        public Consultorio createFromParcel(Parcel source) {
            return new Consultorio(source);
        }

        @Override
        public Consultorio[] newArray(int size) {
            return new Consultorio[size];
        }
    };

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        telefone = in.readString();
        numero = in.readInt();
        complemento = in.readString();
        rating = in.readDouble();
        banner = in.readString();
        email = in.readString();
        logradouro = (Logradouro) in.readValue(Logradouro.class.getClassLoader());
        tipo = (Tipo) in.readValue(Tipo.class.getClassLoader());
    }
}