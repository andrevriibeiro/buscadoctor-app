package br.com.buscadoctor.android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class Logradouro implements Parcelable {

    private int id;
    private String nome;
    private String tipo;
    private String bairro;
    private String cep;
    private Cidade cidade;

    public Logradouro() {
    }

    public Logradouro(Parcel in) {
        readFromParcel(in);
    }

    public Logradouro(int id, String nome, String tipo, String bairro, String cep, Cidade cidade) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(tipo);
        dest.writeString(bairro);
        dest.writeString(cep);
        dest.writeValue(cidade);
    }

    public static final Creator CREATOR = new Creator<Logradouro>() {
        @Override
        public Logradouro createFromParcel(Parcel source) {
            return new Logradouro(source);
        }

        @Override
        public Logradouro[] newArray(int size) {
            return new Logradouro[size];
        }
    };

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        tipo = in.readString();
        bairro = in.readString();
        cep = in.readString();
        cidade = (Cidade) in.readValue(Cidade.class.getClassLoader());
    }
}