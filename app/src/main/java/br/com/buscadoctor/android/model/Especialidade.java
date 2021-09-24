package br.com.buscadoctor.android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class Especialidade implements Parcelable {

    private int id;
    private String especialidade;
    private String CBOS;

    public Especialidade() {
    }

    public Especialidade(Parcel in) {
        readFromParcel(in);
    }

    public Especialidade(int id, String especialidade, String CBOS) {
        this.id = id;
        this.especialidade = especialidade;
        this.CBOS = CBOS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCBOS() {
        return CBOS;
    }

    public void setCBOS(String CBOS) {
        this.CBOS = CBOS;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(especialidade);
        dest.writeString(CBOS);
    }

    public static final Creator CREATOR = new Creator<Especialidade>() {
        @Override
        public Especialidade createFromParcel(Parcel source) {
            return new Especialidade(source);
        }

        @Override
        public Especialidade[] newArray(int size) {return new Especialidade[size];}
    };

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        especialidade = in.readString();
        CBOS = in.readString();
    }
}