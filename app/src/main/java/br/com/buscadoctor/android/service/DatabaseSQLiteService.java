package br.com.buscadoctor.android.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.buscadoctor.android.model.Cidade;
import br.com.buscadoctor.android.model.Consultorio;
import br.com.buscadoctor.android.model.Convenio;
import br.com.buscadoctor.android.model.Especialidade;
import br.com.buscadoctor.android.model.Especialista;
import br.com.buscadoctor.android.model.EspecialistaConsultorio;
import br.com.buscadoctor.android.model.Estado;
import br.com.buscadoctor.android.model.Logradouro;
import br.com.buscadoctor.android.model.Usuario;
import br.com.buscadoctor.android.model.UsuarioConvenio;
import br.com.buscadoctor.android.util.Constants;
import br.com.buscadoctor.android.util.Functions;

/**
 * @author Andre
 */
public class DatabaseSQLiteService extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String CONSULTORIOS_TABLE = "Consultorio";
    private static final String ESPECIALISTA_TABLE = "Especialista";
    private static final String USUARIO_TABLE = "Usuario";
    private static final String USUARIO_CONVENIOS_TABLE = "UsuarioConvenios";

    private static final String DATABASE = "PerfilUsuario";

    /**
     * CREATE TABLE FOR CONSULTORIOS
     */
    private static final String SQL_CREATE_TABLE_CONSULTORIOS = "CREATE TABLE " + CONSULTORIOS_TABLE +
            " (" + Constants.ID_COLUMN + " INTEGER PRIMARY KEY, "
            + Constants.NAME_COLUMN + " TEXT UNIQUE NOT NULL, "
            + Constants.PHOTO_COLUMN + " TEXT, "
            + Constants.TELEFONE_COLUMN + " TEXT, "
            + Constants.TIPO_ENDERECO + " TEXT, "
            + Constants.ENDERECO_COLUMN + " TEXT, "
            + Constants.ESTADO_COLUMN + " TEXT, "
            + Constants.CIDADE_COLUMN + " TEXT, "
            + Constants.BAIRRO_COLUMN + " TEXT, "
            + Constants.NUMERO_COLUMN + " TEXT, "
            + Constants.COMPLEMENTO_COLUMN + " TEXT, "
            + Constants.RATING_COLUMN + " REAL, "
            + Constants.TIPO_CONSULTORIO_COLUMN + " TEXT, "
            + Constants.NOME_TIPO_CONSULTORIO_COLUMN + " TEXT"
            + ")";

    /**
     * CREATE TABLE FOR ESPECIALISTA
     */
    private static final String SQL_CREATE_TABLE_ESPECIALISTA = "CREATE TABLE " + ESPECIALISTA_TABLE +
            " (" + Constants.ID_COLUMN + " INTEGER PRIMARY KEY, "
            + Constants.NAME_COLUMN + " TEXT UNIQUE NOT NULL, "
            + Constants.SEXO_COLUMN + " TEXT, "
            + Constants.ESPECIALIDADE_COLUMN + " TEXT, "
            + Constants.PHOTO_COLUMN + " TEXT, "
            + Constants.ID_CONSULTORIO_COLUMN + " INTEGER, "
            + Constants.CONSULTORIO_COLUMN + " TEXT, "
            + Constants.TELEFONE_COLUMN + " TEXT, "
            + Constants.TIPO_ENDERECO + " TEXT, "
            + Constants.ENDERECO_COLUMN + " TEXT, "
            + Constants.ESTADO_COLUMN + " TEXT, "
            + Constants.CIDADE_COLUMN + " TEXT, "
            + Constants.BAIRRO_COLUMN + " TEXT, "
            + Constants.NUMERO_COLUMN + " TEXT, "
            + Constants.COMPLEMENTO_COLUMN + " TEXT, "
            + Constants.RATING_COLUMN + " REAL "
            + ")";

    /**
     * CREATE TABLE FOR USUARIO
     */
    private static final String SQL_CREATE_TABLE_USUARIO = "CREATE TABLE " + USUARIO_TABLE +
            " (" + Constants.ID_COLUMN + " INTEGER PRIMARY KEY, "
            + Constants.NAME_COLUMN + " TEXT UNIQUE NOT NULL, "
            + Constants.CELL_COLUMN + " TEXT, "
            + Constants.BIRTHDAY_COLUMN + " TEXT, "
            + Constants.SEXO_COLUMN + " TEXT, "
            + Constants.CPF_COLUMN + " TEXT, "
            + Constants.EMAIL_COLUMN + " TEXT, "
            + Constants.TELEFONE_COLUMN + " TEXT, "
            + Constants.USER_COLUMN + " TEXT, "
            + Constants.PASSWORD_COLUMN + " TEXT "
            + ")";


    /**
     * CREATE TABLE FOR USUARIO_CONVENIOS
     */
    private static final String SQL_CREATE_TABLE_USUARIO_CONVENIO = "CREATE TABLE " + USUARIO_CONVENIOS_TABLE +
            " (" + Constants.ID_COLUMN + " INTEGER PRIMARY KEY, "
            + Constants.CONVENIO_ID_COLUMN + " INTEGER, "
            + Constants.CONVENIO_NAME_COLUMN + " TEXT, "
            + Constants.CONVENIO_TIPO_COLUMN + " TEXT, "
            + Constants.CONVENIO_NUMBER_COLUMN + " TEXT"
            + ")";

    /**
     * CONSTRUCTOR DatabaseSQLiteService
     * <p>
     * *
     */
    public DatabaseSQLiteService(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_CONSULTORIOS);
        db.execSQL(SQL_CREATE_TABLE_ESPECIALISTA);
        db.execSQL(SQL_CREATE_TABLE_USUARIO);
        db.execSQL(SQL_CREATE_TABLE_USUARIO_CONVENIO);
    }

    /**
     * UPDATE TABLE ESPECIALISTA, CONSULTORIO, USUARIO e USUARIO_CONVENIO
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONSULTORIOS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ESPECIALISTA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USUARIO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USUARIO_CONVENIOS_TABLE);
        onCreate(db);
    }

    /**
     * este metodo é responsavel por inserir o consultorio favoritado no Banco Interno
     */
    public void insertConsultorio(Consultorio consultorio) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.ID_COLUMN, consultorio.getId());
        values.put(Constants.NAME_COLUMN, consultorio.getNome());
        values.put(Constants.PHOTO_COLUMN, consultorio.getBanner());
        values.put(Constants.TELEFONE_COLUMN, consultorio.getTelefone());
        values.put(Constants.TIPO_ENDERECO, consultorio.getLogradouro().getTipo());
        values.put(Constants.ENDERECO_COLUMN, consultorio.getLogradouro().getNome());
        values.put(Constants.ESTADO_COLUMN, consultorio.getLogradouro().getCidade().getEstado().getAcronimo());
        values.put(Constants.CIDADE_COLUMN, consultorio.getLogradouro().getCidade().getNome());
        values.put(Constants.BAIRRO_COLUMN, consultorio.getLogradouro().getBairro());
        values.put(Constants.NUMERO_COLUMN, consultorio.getNumero());
        values.put(Constants.COMPLEMENTO_COLUMN, consultorio.getComplemento());
        values.put(Constants.RATING_COLUMN, consultorio.getRating());
        values.put(Constants.TIPO_CONSULTORIO_COLUMN, consultorio.getTipo().getId());
        values.put(Constants.NOME_TIPO_CONSULTORIO_COLUMN, consultorio.getTipo().getNome());

        db.insert(CONSULTORIOS_TABLE, null, values);
    }

    /**
     * este metodo é responsavel por inserir o especialista favoritado no Banco Interno
     */
    public void insertEspecialista(EspecialistaConsultorio especialistaConsultorio) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.ID_COLUMN, especialistaConsultorio.getEspecialista().getId());
        values.put(Constants.NAME_COLUMN, especialistaConsultorio.getEspecialista().getNome());
        values.put(Constants.SEXO_COLUMN, especialistaConsultorio.getEspecialista().getSexo());
        values.put(Constants.ID_CONSULTORIO_COLUMN, especialistaConsultorio.getConsultorio().getId());
        values.put(Constants.CONSULTORIO_COLUMN, especialistaConsultorio.getConsultorio().getNome());
        values.put(Constants.TELEFONE_COLUMN, especialistaConsultorio.getConsultorio().getTelefone());
        values.put(Constants.TIPO_ENDERECO, especialistaConsultorio.getConsultorio().getLogradouro().getTipo());
        values.put(Constants.ENDERECO_COLUMN, especialistaConsultorio.getConsultorio().getLogradouro().getNome());
        values.put(Constants.ESTADO_COLUMN, especialistaConsultorio.getConsultorio().getLogradouro().getCidade().getEstado().getAcronimo());
        values.put(Constants.CIDADE_COLUMN, especialistaConsultorio.getConsultorio().getLogradouro().getCidade().getNome());
        values.put(Constants.BAIRRO_COLUMN, especialistaConsultorio.getConsultorio().getLogradouro().getBairro());
        values.put(Constants.NUMERO_COLUMN, especialistaConsultorio.getConsultorio().getNumero());
        values.put(Constants.COMPLEMENTO_COLUMN, especialistaConsultorio.getConsultorio().getComplemento());
        values.put(Constants.RATING_COLUMN, especialistaConsultorio.getEspecialista().getRating());

        db.insert(ESPECIALISTA_TABLE, null, values);
    }


    /**
     * este metodo é responsavel por inserir os dados do usuario logado no Banco Interno
     */
    public void insertUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.ID_COLUMN, usuario.getId());
        values.put(Constants.NAME_COLUMN, usuario.getNome());
        values.put(Constants.CELL_COLUMN, usuario.getCelular());
        values.put(Constants.SEXO_COLUMN, usuario.getSexo());
        values.put(Constants.CPF_COLUMN, usuario.getCpf());
        values.put(Constants.EMAIL_COLUMN, usuario.getEmail());
        values.put(Constants.TELEFONE_COLUMN, usuario.getTelefoneresidencia());
        values.put(Constants.USER_COLUMN, usuario.getUser());

        String dataFormatada = Functions.getDateFormat(usuario.getBirthday());
        values.put(Constants.BIRTHDAY_COLUMN, dataFormatada);

        db.insert(USUARIO_TABLE, null, values);

    }

    /**
     * este metodo é responsavel por inserir convenios do usuário ao Banco Interno
     */
    public void insertUsuarioConvenios(List<UsuarioConvenio> usuarioConvenios) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (UsuarioConvenio usuarioConvenio : usuarioConvenios) {

            ContentValues values = new ContentValues();
            values.put(Constants.ID_COLUMN, usuarioConvenio.getId());
            values.put(Constants.CONVENIO_ID_COLUMN, usuarioConvenio.getConvenio().getId());
            values.put(Constants.CONVENIO_NAME_COLUMN, usuarioConvenio.getConvenio().getNome());
            values.put(Constants.CONVENIO_NUMBER_COLUMN, usuarioConvenio.getNumero());
            values.put(Constants.CONVENIO_TIPO_COLUMN, usuarioConvenio.getTipo());

            db.insert(USUARIO_CONVENIOS_TABLE, null, values);
        }
    }

    /**
     * este metodo é responsavel por inserir convenio do usuario ao Banco Interno
     */
    public void insertUsuarioConvenio(UsuarioConvenio usuarioConvenio) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(Constants.ID_COLUMN, usuarioConvenio.getId());
        values.put(Constants.CONVENIO_ID_COLUMN, usuarioConvenio.getConvenio().getId());
        values.put(Constants.CONVENIO_NAME_COLUMN, usuarioConvenio.getConvenio().getNome());
        values.put(Constants.CONVENIO_NUMBER_COLUMN, usuarioConvenio.getNumero());
        values.put(Constants.CONVENIO_TIPO_COLUMN, usuarioConvenio.getTipo());

        db.insert(USUARIO_CONVENIOS_TABLE, null, values);
    }


    /**
     * este metodo é responsavel por atualizar os dados do usuario no Banco Interno
     */
    public void updateUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.ID_COLUMN, usuario.getId());
        values.put(Constants.NAME_COLUMN, usuario.getNome());
        values.put(Constants.CELL_COLUMN, usuario.getCelular());
        values.put(Constants.SEXO_COLUMN, usuario.getSexo());
        values.put(Constants.CPF_COLUMN, usuario.getCpf());
        values.put(Constants.EMAIL_COLUMN, usuario.getEmail());
        values.put(Constants.TELEFONE_COLUMN, usuario.getTelefoneresidencia());
        values.put(Constants.USER_COLUMN, usuario.getUser());

        String dataFormatada = Functions.getDateFormat(usuario.getBirthday());
        values.put(Constants.BIRTHDAY_COLUMN, dataFormatada);

        db.update(USUARIO_TABLE, values, Constants.ID_COLUMN + "=" + usuario.getId(), null);
    }

    /**
     * este metodo é responsavel por atualizar os dados do usuario no Banco Interno
     */
    public void updateUsuarioConvenio(UsuarioConvenio usuarioConvenio) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.ID_COLUMN, usuarioConvenio.getId());
        values.put(Constants.CONVENIO_NUMBER_COLUMN, usuarioConvenio.getNumero());
        values.put(Constants.CONVENIO_TIPO_COLUMN, usuarioConvenio.getTipo());

        db.update(USUARIO_CONVENIOS_TABLE, values, Constants.ID_COLUMN + "=" + usuarioConvenio.getId(), null);
    }

    /**
     * este metodo é responsavel por listar todos os CONSULTORIO Favoritados
     */
    public List<Consultorio> getAllConsultorios() {
        List<Consultorio> consultorios = new ArrayList<Consultorio>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CONSULTORIOS_TABLE + ";", null);

        while (c.moveToNext()) {
            Consultorio consultorio = new Consultorio();

            Estado estado = new Estado();
            estado.setAcronimo(c.getString(c.getColumnIndex(Constants.ESTADO_COLUMN)));

            Cidade cidade = new Cidade();
            cidade.setNome(c.getString(c.getColumnIndex(Constants.CIDADE_COLUMN)));
            cidade.setEstado(estado);

            Logradouro logradouro = new Logradouro();
            logradouro.setTipo(c.getString(c.getColumnIndex(Constants.TIPO_ENDERECO)));
            logradouro.setNome(c.getString(c.getColumnIndex(Constants.ENDERECO_COLUMN)));
            logradouro.setBairro(c.getString(c.getColumnIndex(Constants.BAIRRO_COLUMN)));
            logradouro.setCidade(cidade);

            /*ConsultorioTipo consultorioTipo = new ConsultorioTipo();
            consultorioTipo.setId(c.getInt(c.getColumnIndex(Constants.TIPO_CONSULTORIO_COLUMN)));
            consultorioTipo.setNome(c.getString(c.getColumnIndex(Constants.NOME_TIPO_CONSULTORIO_COLUMN)));*/

            consultorio.setId(c.getInt(c.getColumnIndex(Constants.ID_COLUMN)));
            consultorio.setNome(c.getString(c.getColumnIndex(Constants.NAME_COLUMN)));
            consultorio.setTelefone(c.getString(c.getColumnIndex(Constants.TELEFONE_COLUMN)));
            consultorio.setLogradouro(logradouro);
            consultorio.setNumero(c.getInt(c.getColumnIndex(Constants.NUMERO_COLUMN)));
            consultorio.setComplemento(c.getString(c.getColumnIndex(Constants.COMPLEMENTO_COLUMN)));
            consultorio.setRating(c.getDouble(c.getColumnIndex(Constants.RATING_COLUMN)));
            //consultorio.setTipo(consultorioTipo);

            consultorios.add(consultorio);
        }
        c.close();
        return consultorios;
    }

    /**
     * este metodo é responsavel por retornar os  dados do usuario logado
     */
    public Usuario getUsuario() {
        Usuario usuario = new Usuario();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + USUARIO_TABLE + ";", null);

        while (c.moveToNext()) {
            usuario.setId(c.getInt(c.getColumnIndex(Constants.ID_COLUMN)));
            usuario.setNome(c.getString(c.getColumnIndex(Constants.NAME_COLUMN)));
            usuario.setCelular(c.getString(c.getColumnIndex(Constants.CELL_COLUMN)));
            usuario.setSexo(c.getString(c.getColumnIndex(Constants.SEXO_COLUMN)));
            usuario.setCpf(c.getString(c.getColumnIndex(Constants.CPF_COLUMN)));
            usuario.setEmail(c.getString(c.getColumnIndex(Constants.EMAIL_COLUMN)));
            usuario.setTelefoneresidencia(c.getString(c.getColumnIndex(Constants.TELEFONE_COLUMN)));
            usuario.setUser(c.getString(c.getColumnIndex(Constants.USER_COLUMN)));
            Date date = Functions.getDate(c.getString(c.getColumnIndex(Constants.BIRTHDAY_COLUMN)));
            usuario.setBirthday(date);
        }

        return usuario;
    }

    /**
     * este metodo é responsavel por listar todos os especialistas Favoritados
     */
    public List<EspecialistaConsultorio> getAllEspecialista() {
        List<EspecialistaConsultorio> especialistaConsultorios = new ArrayList<EspecialistaConsultorio>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + ESPECIALISTA_TABLE + ";", null);

        while (c.moveToNext()) {
            EspecialistaConsultorio especialistaConsultorio = new EspecialistaConsultorio();

            Estado estado = new Estado();
            estado.setAcronimo(c.getString(c.getColumnIndex(Constants.ESTADO_COLUMN)));

            Cidade cidade = new Cidade();
            cidade.setNome(c.getString(c.getColumnIndex(Constants.CIDADE_COLUMN)));
            cidade.setEstado(estado);

            Logradouro logradouro = new Logradouro();
            logradouro.setTipo(c.getString(c.getColumnIndex(Constants.TIPO_ENDERECO)));
            logradouro.setNome(c.getString(c.getColumnIndex(Constants.ENDERECO_COLUMN)));
            logradouro.setBairro(c.getString(c.getColumnIndex(Constants.BAIRRO_COLUMN)));
            logradouro.setCidade(cidade);

            Consultorio consultorio = new Consultorio();
            consultorio.setId(c.getInt(c.getColumnIndex(Constants.ID_CONSULTORIO_COLUMN)));
            consultorio.setNome(c.getString(c.getColumnIndex(Constants.CONSULTORIO_COLUMN)));
            consultorio.setTelefone(c.getString(c.getColumnIndex(Constants.TELEFONE_COLUMN)));
            consultorio.setComplemento(c.getString(c.getColumnIndex(Constants.COMPLEMENTO_COLUMN)));
            consultorio.setNumero(c.getInt(c.getColumnIndex(Constants.NUMERO_COLUMN)));
            consultorio.setLogradouro(logradouro);

            Especialidade especialidade = new Especialidade();
            especialidade.setEspecialidade(c.getString(c.getColumnIndex(Constants.ESPECIALIDADE_COLUMN)));

            Especialista especialista = new Especialista();
            especialista.setId(c.getInt(c.getColumnIndex(Constants.ID_COLUMN)));
            especialista.setNome(c.getString(c.getColumnIndex(Constants.NAME_COLUMN)));
            especialista.setSexo(c.getString(c.getColumnIndex(Constants.SEXO_COLUMN)));
            //especialista.setEspecialidade(especialidade);
            //especialista.setPhoto(c.getInt(c.getColumnIndex(Constants.PHOTO_COLUMN)));
            especialista.setRating(c.getDouble(c.getColumnIndex(Constants.RATING_COLUMN)));

            especialistaConsultorio.setEspecialista(especialista);
            especialistaConsultorio.setConsultorio(consultorio);

            especialistaConsultorios.add(especialistaConsultorio);
        }
        c.close();
        return especialistaConsultorios;
    }

    /**
     * este metodo é responsavel por listar todos os convenios do usuario logado
     */
    public List<UsuarioConvenio> getAllUsuarioconvenios() {
        List<UsuarioConvenio> usuarioConvenioList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + USUARIO_CONVENIOS_TABLE + ";", null);

        while (c.moveToNext()) {
            UsuarioConvenio usuarioConvenio = new UsuarioConvenio();
            usuarioConvenio.setId(c.getInt(c.getColumnIndex(Constants.ID_COLUMN)));
            usuarioConvenio.setNumero(c.getString(c.getColumnIndex(Constants.CONVENIO_NUMBER_COLUMN)));

            Convenio convenio = new Convenio();
            convenio.setId(c.getInt(c.getColumnIndex(Constants.CONVENIO_ID_COLUMN)));
            convenio.setNome(c.getString(c.getColumnIndex(Constants.CONVENIO_NAME_COLUMN)));

            usuarioConvenio.setConvenio(convenio);

            usuarioConvenioList.add(usuarioConvenio);
        }
        c.close();
        return usuarioConvenioList;
    }

    /**
     * este metodo é responsavel por deletar um consultorio favoritado da base SQL
     */
    public void deleteConsultorio(int idConsultorio) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CONSULTORIOS_TABLE, "id= " + idConsultorio, null);
    }

    /**
     * Este metodo é responsavel por deletar um especialista favoritado da base SQL
     */
    public void deleteEspecialista(int idEspecialista) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ESPECIALISTA_TABLE, "id= " + idEspecialista, null);
    }

    /**
     * este metodo é responsavel por deletar um convenio do usuario da base SQL
     */
    public void deleteUsuarioConvenio(int idUsuarioConvenio) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USUARIO_CONVENIOS_TABLE, "id= " + idUsuarioConvenio, null);
    }

    /**
     * Este metodo é responsavel por verificar se um consultorio ja esta favoritado
     */
    public boolean verificaConsultorioFavoritado(int idConsutorio) {
        boolean response = false;
        List<Consultorio> consultorios = new ArrayList<Consultorio>();

        consultorios = getAllConsultorios();

        for (int i = 0; i < consultorios.size(); i++) {
            if (consultorios.get(i).getId() == idConsutorio) {
                response = true;
            }
        }

        return response;
    }

    /**
     * Este metodo é responsavel por verificar se um especialista ja esta favoritado
     */
    public boolean verificaEspecialistaFavoritado(int idEspecialista) {
        boolean response = false;
        List<EspecialistaConsultorio> especialistas = new ArrayList<EspecialistaConsultorio>();

        especialistas = getAllEspecialista();

        for (int i = 0; i < especialistas.size(); i++) {
            if (especialistas.get(i).getEspecialista().getId() == idEspecialista) {
                response = true;
            }
        }

        return response;
    }
}