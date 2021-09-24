package br.com.buscadoctor.android.model.dto;

import java.util.List;

import br.com.buscadoctor.android.model.Consulta;
import br.com.buscadoctor.android.model.Convenio;
import br.com.buscadoctor.android.model.Paciente;

/**
 * @author Guilherme Mendes
 */
public class ConsultaDTO {

    private List<Consulta> consultas;
    private Paciente paciente;
    private Convenio convenio;
    //private ProcedimentoEspecialista procedimentoEspecialista;
    private boolean unicaGuiaSessao;
    private boolean consultaRetorno;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public boolean isUnicaGuiaSessao() {
        return unicaGuiaSessao;
    }

    /*public ProcedimentoEspecialista getProcedimentoEspecialista() {
        return procedimentoEspecialista;
    }*/

    public boolean isConsultaRetorno() {
        return consultaRetorno;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    /*public void setProcedimentoEspecialista(ProcedimentoEspecialista procedimentoEspecialista) {
        this.procedimentoEspecialista = procedimentoEspecialista;
    }*/
}