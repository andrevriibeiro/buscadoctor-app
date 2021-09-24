package br.com.buscadoctor.android.service.listener;

import java.util.List;

import br.com.buscadoctor.android.model.Consultorio;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ConsultorioServiceListener {

    void onSuccess(List<Consultorio> consultorios);

    void onSuccess(Consultorio consultorio);

    void onFail(Throwable t);
}