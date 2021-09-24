package br.com.buscadoctor.android.model;

import java.util.Date;

/**
 * Esta classe representa o Usuario, contem suas caracteristicas e acoes.
 * <p>Utilizar somente nas operacoes especificas.
 *
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String user;
    private String senha;
    private String sexo;
    private String celular;
    private Date birthday;
    private Boolean acesso;
    private Integer numero;
    private String complemento;
    private String rg;
    private String cpf;
    private String telefoneresidencia;
    private String telefonerecado;
    private String profissao;
    private String estadocivil;
    private String naturalidade;
    private String cor;
    private Configuracao configuracao;
    private Logradouro logradouro;
    private Date createdAt;

    public Usuario() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getAcesso() {
        return acesso;
    }

    public void setAcesso(Boolean acesso) {
        this.acesso = acesso;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}