package br.com.buscadoctor.android.service.listener;

import java.util.List;

import br.com.buscadoctor.android.model.Especialidade;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public interface EspecialidadeServiceListener {

    void onSuccess(List<Especialidade> especialidades);

    void onFail(Throwable t);
}